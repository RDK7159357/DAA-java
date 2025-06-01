package internal;

import java.util.*;

public class TSP {
    static int n, minCost = Integer.MAX_VALUE;
    static int[] bestPath;
    static boolean[] visited;
    
    static int getMinEdge(int[][] adj, int vertex, boolean excludeFirst) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (i != vertex && adj[vertex][i] > 0) {
                if (adj[vertex][i] < min1) {
                    min2 = min1;
                    min1 = adj[vertex][i];
                } else if (adj[vertex][i] < min2) {
                    min2 = adj[vertex][i];
                }
            }
        }
        return excludeFirst ? min2 : min1;
    }
    
    static void solve(int[][] adj, int[] path, int level, int cost, int bound) {
        if (level == n) {
            if (adj[path[n-1]][0] > 0) {
                int totalCost = cost + adj[path[n-1]][0];
                if (totalCost < minCost) {
                    minCost = totalCost;
                    bestPath = Arrays.copyOf(path, n + 1);
                    bestPath[n] = 0;
                }
            }
            return;
        }
        
        for (int i = 1; i < n; i++) {
            if (!visited[i] && adj[path[level-1]][i] > 0) {
                int newCost = cost + adj[path[level-1]][i];
                int newBound = bound - (getMinEdge(adj, path[level-1], level > 1) + 
                                      getMinEdge(adj, i, false)) / 2;
                
                if (newBound + newCost < minCost) {
                    path[level] = i;
                    visited[i] = true;
                    solve(adj, path, level + 1, newCost, newBound);
                    visited[i] = false;
                }
            }
        }
    }
    
    static void solveTSP(int[][] graph) {
        n = graph.length;
        visited = new boolean[n];
        bestPath = new int[n + 1];
        
        int bound = 0;
        for (int i = 0; i < n; i++) {
            bound += getMinEdge(graph, i, false) + getMinEdge(graph, i, true);
        }
        bound = (bound + 1) / 2;
        
        int[] path = new int[n];
        path[0] = 0;
        visited[0] = true;
        solve(graph, path, 1, 0, bound);
    }
    
    public static void main(String[] args) {
        int[][] adj = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        
        solveTSP(adj);
        System.out.println("Min cost: " + minCost);
        System.out.println("Path: " + Arrays.toString(bestPath));
    }
}