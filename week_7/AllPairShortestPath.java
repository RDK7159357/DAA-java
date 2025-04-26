// Java program for Floyd Warshall All Pairs Shortest Path algorithm. 
package week_7;
// import java.io.*; 
// import java.lang.*; 
// import java.util.*; 

class AllPairShortestPath { 
    final static int INF = 99999, V = 4; 
    
    /**
     * Implements the Floyd Warshall algorithm to find shortest paths between all pairs of vertices
     * Time Complexity: O(V^3) where V is the number of vertices
     * Space Complexity: O(V^2)
     * 
     * @param dist The adjacency matrix representing the graph
     */
    void floydWarshall(int dist[][]) 
    { 
        // Create a copy of the input matrix to work with
        int[][] dp = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dp[i][j] = dist[i][j];
            }
        }
        
        // Main Floyd Warshall algorithm
        // Consider each vertex as an intermediate vertex
        for (int k = 0; k < V; k++) {
            // Consider each vertex as a source
            for (int i = 0; i < V; i++) {
                // Consider each vertex as a destination
                for (int j = 0; j < V; j++) {
                    // If vertex k is on the shortest path from i to j,
                    // then update the value of dp[i][j]
                    if (dp[i][k] != INF && dp[k][j] != INF && 
                        dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }
        
        // Check for negative weight cycles
        // If distance of any vertex from itself becomes negative,
        // then there is a negative weight cycle
        for (int i = 0; i < V; i++) {
            if (dp[i][i] < 0) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }
        
        // Print the shortest distance matrix
        printSolution(dp);
    } 
    
    void printSolution(int dist[][]) 
    { 
        System.out.println("The following matrix shows the shortest distances between every pair of vertices:"); 
        
        // Print column headers
        System.out.print("      ");
        for (int j = 0; j < V; j++) {
            System.out.print("| " + j + " ");
        }
        System.out.println("|");
        
        // Print separator line
        System.out.print("------|");
        for (int j = 0; j < V; j++) {
            System.out.print("---|");
        }
        System.out.println();
        
        // Print matrix with row headers
        for (int i = 0; i < V; ++i) { 
            System.out.print("  " + i + "   |");
            for (int j = 0; j < V; ++j) { 
                if (dist[i][j] == INF) 
                    System.out.print(" ∞ |"); 
                else 
                    System.out.print(" " + dist[i][j] + " |"); 
            } 
            System.out.println(); 
        } 
    } 
    
    // Driver's code 
    public static void main(String[] args) 
    { 
        // Create a graph represented by adjacency matrix
        // Initialize the graph with edge weights
        // INF means there is no direct edge between two vertices
        int graph[][] = { 
            { 0, 5, INF, 10 }, 
            { INF, 0, 3, INF }, 
            { INF, INF, 0, 1 }, 
            { INF, INF, INF, 0 } 
        };
        // Example graph representation:
        // 0   5   INF 10
        // INF 0   3   INF 
        // INF INF 0   1
        // INF INF INF 0
        // Format the graph representation for better readability
        System.out.println("Initial Graph Matrix:");
        System.out.println("    0   1   2   3");  // Column headers
        System.out.println("  +---+---+---+---+");
        for (int i = 0; i < V; i++) {
            System.out.print(i + " | "); // Row headers
            for (int j = 0; j < V; j++) {
                if (graph[i][j] == INF)
                    System.out.printf("∞ |");
                else
                    System.out.printf("%d |", graph[i][j]);
            }
            System.out.println("\n  +---+---+---+---+");
        }
        System.out.println();
        // Where:
        // - Each row/column represents a vertex (0 to 3)
        // - Each cell [i][j] represents the direct edge weight from vertex i to j
        // - 0 on diagonal means no self-loops
        // - INF means no direct edge exists between vertices
        // Create an instance of AllPairShortestPath class
        AllPairShortestPath a = new AllPairShortestPath(); 
        
        // Run Floyd Warshall algorithm on the graph
        a.floydWarshall(graph); 
    } 
}