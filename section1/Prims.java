package section1;

import java.util.*;

public class Prims {
    static int prim(int[][] graph) {
        int n = graph.length, cost = 0;
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        for (int count = 0; count < n; count++) {
            int u = -1;
            for (int v = 0; v < n; v++)
                if ( !visited[v] && (u == -1 || dist[v] < dist[u])) u = v;

            visited[u] = true;
            cost += dist[u];

            for (int v = 0; v < n; v++)
                if (graph[u][v] != 0 &&  !visited[v] && graph[u][v] < dist[v])
                    dist[v] = graph[u][v];
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
        System.out.println("MST Cost: " + prim(graph));
    }
}

