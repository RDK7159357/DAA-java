package section1;

import java.util.*;

class Graph {
    private int V;                      // Number of vertices
    private LinkedList<Integer>[] adj;  // Adjacency list

    // Constructor
    @SuppressWarnings("unchecked")
    Graph(int V) {
        this.V = V;
        // Initialize the adjacency list for each vertex
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // Function to add an edge into the graph
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // Breadth First Traversal (BFS) from a given source s
    public void BFS(int s) {
        boolean[] visited = new boolean[V];  // Track visited vertices
        Queue<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        System.out.print("Breadth First Traversal starting from vertex " + s + ": ");
        while (!queue.isEmpty()) {
            int v = queue.poll();
            System.out.print(v + " ");

            // Enqueue all adjacent vertices that have not been visited
            for (int n : adj[v]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        System.out.println();
    }

    // A recursive helper method for DFS
    private void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        // Recur for all the vertices adjacent to this vertex
        for (int n : adj[v]) {
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    // Depth First Traversal (DFS) starting from vertex s
    public void DFS(int s) {
        boolean[] visited = new boolean[V];
        System.out.print("DFS starting from vertex " + s + ": ");
        DFSUtil(s, visited);
        System.out.println();
    }
}

public class GraphTraversalTechniques {
    public static void main(String[] args) {
        // --- BFS Example ---
        // Constructing a graph for BFS:
        // Example: For a graph with vertices 0,1,2,3,4 and edges:
        // 0->1, 0->2, 1->3, 2->3, 3->4
        Graph bfsGraph = new Graph(5);
        bfsGraph.addEdge(0, 1);
        bfsGraph.addEdge(0, 2);
        bfsGraph.addEdge(1, 3);
        bfsGraph.addEdge(2, 3);
        bfsGraph.addEdge(3, 4);

        // Output should be: 0 1 2 3 4 (depending on insertion order)
        bfsGraph.BFS(0);

        // --- DFS Example 1 ---
        // Constructing a graph for DFS:
        // Example: n = 4, e = 6 with edges:
        // 0 -> 1, 0 -> 2, 1 -> 2, 2 -> 0, 2 -> 3, 3 -> 3
        Graph dfsGraph1 = new Graph(4);
        dfsGraph1.addEdge(0, 1);
        dfsGraph1.addEdge(0, 2);
        dfsGraph1.addEdge(1, 2);
        dfsGraph1.addEdge(2, 0);
        dfsGraph1.addEdge(2, 3);
        dfsGraph1.addEdge(3, 3);

        // Starting DFS from vertex 1 should output: 1 2 0 3 (depending on insertion order)
        dfsGraph1.DFS(1);

        // --- DFS Example 2 ---
        // Another DFS example:
        // Example: n = 4, e = 6 with edges:
        // 2 -> 0, 0 -> 2, 1 -> 2, 0 -> 1, 3 -> 3, 1 -> 3
        Graph dfsGraph2 = new Graph(4);
        dfsGraph2.addEdge(2, 0);
        dfsGraph2.addEdge(0, 2);
        dfsGraph2.addEdge(1, 2);
        dfsGraph2.addEdge(0, 1);
        dfsGraph2.addEdge(3, 3);
        dfsGraph2.addEdge(1, 3);

        // Starting DFS from vertex 2 should output: 2 0 1 3 (depending on insertion order)
        dfsGraph2.DFS(2);
    }
}

