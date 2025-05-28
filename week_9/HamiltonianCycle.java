package week_9;

class HamiltonianCycle {
    final int V = 5;
    int[] path;

    // Check if vertex v can be added at position pos in path[]
    boolean isSafe(int v, int[][] graph, int[] path, int pos) {
        // Check adjacency
        if (graph[path[pos - 1]][v] == 0) return false;
        // Check not already in path
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) return false;
        }
        return true;
    }

    // Recursive utility to build the cycle
    boolean hamCycleUtil(int[][] graph, int[] path, int pos) {
        if (pos == V) {
            // Check edge back to start
            return graph[path[pos - 1]][path[0]] == 1;
        }
        for (int v = 1; v < V; v++) {
            if (isSafe(v, graph, path, pos)) {
                path[pos] = v;
                if (hamCycleUtil(graph, path, pos + 1)) return true;
                // backtrack
                path[pos] = -1;
            }
        }
        return false;
    }

    // Main solver
    boolean hamCycle(int[][] graph) {
        path = new int[V];
        for (int i = 0; i < V; i++) path[i] = -1;
        path[0] = 0;  // start at vertex 0
        if (!hamCycleUtil(graph, path, 1)) {
            System.out.println("No Hamiltonian Cycle exists");
            return false;
        }
        printSolution(path);
        return true;
    }

    void printSolution(int[] path) {
        System.out.print("Found cycle: ");
        for (int v : path) System.out.print(v + " ");
        System.out.println(path[0]);
    }

    public static void main(String[] args) {
        HamiltonianCycle hc = new HamiltonianCycle();
        int[][] graph = {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 0, 1},
            {1, 1, 0, 0, 1},
            {0, 1, 1, 1, 0}
        };
        hc.hamCycle(graph);
    }
}

