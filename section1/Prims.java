package section1;

import java.util.*;

public class Prims {
    static int prim(int[][] graph) {
        int n = graph.length, cost = 0;
        boolean[] selected = new boolean[n];
        int[] key = new int[n];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;

        for (int count = 0; count < n; count++) {
            int u = -1;
            for (int v = 0; v < n; v++)
                if (!selected[v] && (u == -1 || key[v] < key[u])) u = v;

            selected[u] = true;
            cost += key[u];

            for (int v = 0; v < n; v++)
                if (graph[u][v] != 0 && !selected[v] && graph[u][v] < key[v])
                    key[v] = graph[u][v];
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

