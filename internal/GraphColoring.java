package internal;

import java.util.*;

public class GraphColoring {
    static boolean solve(int[][] g, int[] color, int v, int k) { //c: color, k: maxcolors
        if (v == g.length) {
            System.out.println(Arrays.toString(color));
            return true;
        }
        
        for (int c = 1; c <= k; c++) {
            boolean safe = true;
            for (int i = 0; i < g.length && safe; i++) {
                if (g[v][i] == 1 && color[i] == c) safe = false;
            }
            
            if (safe) {
                color[v] = c;
                if (solve(g, color, v + 1, k)) return true;
                color[v] = 0;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[][] graph = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };
        int[] color = new int[graph.length];
        
        if (!solve(graph, color, 0, 3)) {
            System.out.println("No solution");
        }
    }
}