package week_5;

import java.util.*;

public class MSTAlgorithms {

    // ********************
    // Prim's Algorithm Part
    // ********************

    // A utility function to find the vertex with the minimum key value,
    // from the set of vertices not yet included in the MST.
    static int minKey(int key[], Boolean mstSet[], int V) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // A utility function to print the MST stored in the parent[]
    static void printPrimMST(int parent[], int graph[][], int V) {
        System.out.println("Prim's MST:");
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
        System.out.println();
    }

    // Function that implements Prim's MST algorithm for a graph represented
    // using an adjacency matrix representation.
    public static void primMST(int graph[][]) {
        int V = graph.length;
        int parent[] = new int[V];   // Array to store constructed MST
        int key[] = new int[V];      // Key values used to pick minimum weight edge in cut.
        Boolean mstSet[] = new Boolean[V]; // To represent set of vertices included in MST

        // Initialize all keys as INFINITE and mstSet[] as false
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first vertex in MST. Make key 0 so that this vertex is picked first.
        key[0] = 0;
        parent[0] = -1;  // First node is always the root of the MST

        // The MST will have V vertices.
        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet, V); // Pick the vertex with the minimum key.
            mstSet[u] = true;              // Add the picked vertex to the MST set.

            // Update key value and parent index of the adjacent vertices of the picked vertex.
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Print the constructed MST.
        printPrimMST(parent, graph, V);
    }

    // ***********************
    // Kruskal's Algorithm Part
    // ***********************

    // Define an Edge class for Kruskal's algorithm
    static class Edge {
        int src, dest, weight;
        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Define a Subset class used for union-find (disjoint set) operations.
    static class Subset {
        int parent, rank;
        public Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    // A utility function to find set (with path compression)
    static int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    // A function that does union of two sets (uses union by rank)
    static void union(Subset[] subsets, int x, int y) {
        int xRoot = find(subsets, x);
        int yRoot = find(subsets, y);

        // Attach smaller rank tree under root of higher rank tree.
        if (subsets[xRoot].rank < subsets[yRoot].rank) {
            subsets[xRoot].parent = yRoot;
        } else if (subsets[xRoot].rank > subsets[yRoot].rank) {
            subsets[yRoot].parent = xRoot;
        } else {
            subsets[yRoot].parent = xRoot;
            subsets[xRoot].rank++;
        }
    }

    // Function to construct MST using Kruskal's algorithm.
    public static void kruskalMST(int V, List<Edge> edges) {
        // Sort edges in ascending order by weight.
        Collections.sort(edges, Comparator.comparingInt(edge -> edge.weight));

        // This will store the resultant MST.
        List<Edge> result = new ArrayList<>();

        // Allocate memory for creating V subsets.
        Subset[] subsets = new Subset[V];
        for (int v = 0; v < V; v++) {
            subsets[v] = new Subset(v, 0);
        }

        // Number of edges to be taken is equal to V-1.
        for (Edge edge : edges) {
            int x = find(subsets, edge.src);
            int y = find(subsets, edge.dest);

            // If including this edge doesn't cause a cycle, include it in the result.
            if (x != y) {
                result.add(edge);
                union(subsets, x, y);
            }
            if (result.size() == V - 1) {
                break;
            }
        }

        // Print the contents of the resultant MST.
        System.out.println("Kruskal's MST:");
        System.out.println("Edge \tWeight");
        int minCost = 0;
        for (Edge edge : result) {
            System.out.println(edge.src + " -- " + edge.dest + "\t" + edge.weight);
            minCost += edge.weight;
        }
        System.out.println("Minimum Cost Spanning Tree: " + minCost);
    }

    // ***********************
    // Main Method to Run Both Algorithms
    // ***********************
    public static void main(String[] args) {
        // ----- Prim's Algorithm Demo -----
        // Define a graph as an adjacency matrix for Prim's algorithm.
        int primGraph[][] = new int[][] {
            { 0, 2, 0, 6, 0 },
            { 2, 0, 3, 8, 5 },
            { 0, 3, 0, 0, 7 },
            { 6, 8, 0, 0, 9 },
            { 0, 5, 7, 9, 0 }
        };
        primMST(primGraph);

        // ----- Kruskal's Algorithm Demo -----
        // Define a graph for Kruskal's algorithm using edge list representation.
        // In this demo, V = 4 (vertices are labeled from 0 to 3).
        int kruskalV = 4;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        kruskalMST(kruskalV, edges);
    }
}

