package week_9;

public class MColoringProblem {
    final int V = 4;              // Number of vertices
    int[] color;                  // color assignment array

    // Check if vertex v can be colored with color c
    boolean isSafe(int v, int[][] graph, int[] color, int c) {
        for (int u = 0; u < V; u++) {
            if (graph[v][u] == 1 && color[u] == c) {
                return false;
            }
        }
        return true;
    }

    // Recursive utility to try coloring from vertex v onward
    boolean graphColoringUtil(int[][] graph, int m, int[] color, int v) {
        if (v == V) {
            printSolution(color);
            return true;
        }
        for (int c = 1; c <= m; c++) {
            if (isSafe(v, graph, color, c)) {
                color[v] = c;
                if (graphColoringUtil(graph, m, color, v + 1)) {
                    return true;
                }
                color[v] = 0;  // backtrack
            }
        }
        return false;
    }

    // Sets up and starts the coloring process
    boolean graphColoring(int[][] graph, int m) {
        color = new int[V];
        for (int i = 0; i < V; i++) color[i] = 0;
        if (!graphColoringUtil(graph, m, color, 0)) {
            System.out.println("No solution exists");
            return false;
        }
        return true;
    }

    // Print the color assignment
    void printSolution(int[] color) {
        System.out.print("Solution: ");
        for (int i = 0; i < V; i++) {
            System.out.print(color[i] + " ");
        }
        System.out.println();
    }

    // Driver
    public static void main(String[] args) {
        MColoringProblem solver = new MColoringProblem();

        // Example graph (adjacency matrix)
        int[][] graph = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };
        int m = 3;  // number of colors

        solver.graphColoring(graph, m);
    }
}

