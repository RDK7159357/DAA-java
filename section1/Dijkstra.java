package section1;

import java.util.*;

public class Dijkstra {
    static void dijkstra(int[][] graph, int src) {
        int n = graph.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = -1;
            for (int i = 0; i < n; i++)
                if (!visited[i] && (u == -1 || dist[i] < dist[u])) u = i;

            visited[u] = true;

            for (int v = 0; v < n; v++)
                if (!visited[v] && graph[u][v] > 0 &&
                    dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        for (int i = 0; i < n; i++)
            System.out.println("Distance from " + src + " to " + i + " is " + dist[i]);
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        dijkstra(graph, 0);
    }
}

