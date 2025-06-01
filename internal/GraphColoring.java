package internal;

public class GraphColoring {
    private final int V = 4;
    private int[] color = new int[V];
    
    // Check if vertex v can be colored with color c
    private boolean isSafe(int v, int[][] graph, int c) {
        for (int u = 0; u < V; u++) {
            if (graph[v][u] == 1 && color[u] == c) {
                return false;
            }
        }
        return true;
    }
    
    // Recursive backtracking to color vertices
    private boolean solve(int[][] graph, int m, int v) {
        if (v == V) {
            printSolution();
            return true;
        }
        
        for (int c = 1; c <= m; c++) {
            if (isSafe(v, graph, c)) {
                color[v] = c;
                if (solve(graph, m, v + 1)) {
                    return true;
                }
                color[v] = 0; // backtrack
            }
        }
        return false;
    }
    
    // Main method to solve graph coloring
    public boolean colorGraph(int[][] graph, int m) {
        if (solve(graph, m, 0)) {
            return true;
        }
        System.out.println("No solution exists");
        return false;
    }
    
    // Print the solution
    private void printSolution() {
        System.out.print("Solution: ");
        for (int i = 0; i < V; i++) {
            System.out.print(color[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        GraphColoring solver = new GraphColoring();
        
        int[][] graph = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };
        
        solver.colorGraph(graph, 3);
    }
}