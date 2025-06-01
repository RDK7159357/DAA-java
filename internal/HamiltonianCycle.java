package internal;

public class HamiltonianCycle {
    private final int V = 5;
    private int[] path = new int[V];
    
    // Check if vertex v can be safely added at position pos
    private boolean isSafe(int v, int[][] graph, int pos) {
        // Check if previous vertex is adjacent to current vertex
        if (graph[path[pos - 1]][v] == 0) return false;
        
        // Check if vertex is already in path
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) return false;
        }
        return true;
    }
    
    // Recursive function to find Hamiltonian cycle
    private boolean solve(int[][] graph, int pos) {
        // Base case: all vertices included
        if (pos == V) {
            // Check if last vertex connects back to first
            return graph[path[pos - 1]][path[0]] == 1;
        }
        
        // Try adding vertices 1 to V-1 (vertex 0 is already at position 0)
        for (int v = 1; v < V; v++) {
            if (isSafe(v, graph, pos)) {
                path[pos] = v;
                
                if (solve(graph, pos + 1)) {
                    return true;
                }
                
                path[pos] = -1; // backtrack
            }
        }
        return false;
    }
    
    // Find and print Hamiltonian cycle
    public boolean findCycle(int[][] graph) {
        // Initialize path with -1 and start from vertex 0
        for (int i = 0; i < V; i++) path[i] = -1;
        path[0] = 0;
        
        if (solve(graph, 1)) {
            printCycle();
            return true;
        }
        
        System.out.println("No Hamiltonian Cycle exists");
        return false;
    }
    
    // Print the found cycle
    private void printCycle() {
        System.out.print("Found cycle: ");
        for (int v : path) {
            System.out.print(v + " ");
        }
        System.out.println(path[0]); // Complete the cycle
    }
    
    public static void main(String[] args) {
        HamiltonianCycle solver = new HamiltonianCycle();
        
        int[][] graph = {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 0, 1},
            {1, 1, 0, 0, 1},
            {0, 1, 1, 1, 0}
        };
        
        solver.findCycle(graph);
    }
}