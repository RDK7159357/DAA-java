package internal;

import java.util.*;

public class GraphColoring {
    
    static boolean colorGraph(int[][] graph, int colors) {
        int n = graph.length;
        int[] color = new int[n];
        return solve(graph, colors, 0, color);
    }
    
    static boolean solve(int[][] graph, int maxColors, int vertex, int[] color) {
        if (vertex == graph.length) {
            System.out.println("Solution: " + Arrays.toString(color));
            return true;
        }
        
        for (int c = 1; c <= maxColors; c++) {
            if (isSafe(graph, vertex, c, color)) {
                color[vertex] = c;
                if (solve(graph, maxColors, vertex + 1, color)) {
                    return true;
                }
                color[vertex] = 0; // backtrack
            }
        }
        return false;
    }
    
    static boolean isSafe(int[][] graph, int vertex, int c, int[] color) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[vertex][i] == 1 && color[i] == c) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        int[][] graph = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };
        
        if (!colorGraph(graph, 3)) {
            System.out.println("No solution exists");
        }
    }
}