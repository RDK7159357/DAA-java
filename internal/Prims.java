package internal;
import java.util.*;

public class Prims {
    
    static int prim(int[][] graph) {
        int n = graph.length, cost = 0;
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];
        int[] parent = new int[n]; // Track MST edges
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[0] = 0;
        
        System.out.println("MST Edges:");
        
        for (int count = 0; count < n; count++) {
            // Find minimum distance vertex
            int u = -1;
            for (int v = 0; v < n; v++) {
                if (!visited[v] && (u == -1 || dist[v] < dist[u])) {
                    u = v;
                }
            }
            
            visited[u] = true;
            cost += dist[u];
            
            // Print the edge (except for the first vertex)
            if (parent[u] != -1) {
                System.out.println("Edge: " + parent[u] + " - " + u + " (weight: " + dist[u] + ")");
            }
            
            // Update distances to adjacent vertices
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !visited[v] && graph[u][v] < dist[v]) {
                    dist[v] = graph[u][v];
                    parent[v] = u; // Update parent for MST
                }
            }
        }
        
        return cost;
    }
    
    public static void main(String[] args) {
        int[][] graph = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };
        
        int totalCost = prim(graph);
        System.out.println("\nTotal MST Cost: " + totalCost);
    }
}