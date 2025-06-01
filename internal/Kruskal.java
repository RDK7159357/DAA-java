package internal;

import java.util.*;

public class Kruskal {
    static class Edge implements Comparable<Edge> {
        int u, v, w;
        Edge(int u, int v, int w) { 
            this.u = u; 
            this.v = v; 
            this.w = w; 
        }
        public int compareTo(Edge other) { 
            return Integer.compare(this.w, other.w); 
        }
    }
    
    static int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]); // path compression
        }
        return parent[x];
    }
    
    static int kruskal(int n, List<Edge> edges) {
        Collections.sort(edges);
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        int totalCost = 0;
        int edgesUsed = 0;
        
        for (Edge edge : edges) {
            int rootU = find(parent, edge.u);
            int rootV = find(parent, edge.v);
            
            if (rootU != rootV) {
                totalCost += edge.w;
                parent[rootU] = rootV;
                edgesUsed++;
                
                if (edgesUsed == n - 1) break; // MST complete
            }
        }
        
        return totalCost;
    }
    
    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
            new Edge(0, 1, 10),
            new Edge(0, 2, 6),
            new Edge(0, 3, 5),
            new Edge(1, 3, 15),
            new Edge(2, 3, 4)
        );
        
        System.out.println("MST Cost: " + kruskal(4, edges));
    }
}