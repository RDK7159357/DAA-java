package week_5;

class ShortestPath {
    // Number of vertices in the graph.
    static final int V = 9;

    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree.
    int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    // A utility function to print the constructed distance array.
    void printSolution(int dist[]) {
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }

    // Function that implements Dijkstra's single source shortest path algorithm
    // for a graph represented using an adjacency matrix representation.
    void dijkstra(int graph[][], int src) {
        // dist[i] will hold the shortest distance from src to i.
        int dist[] = new int[V];

        // sptSet[i] will be true if vertex i is included in shortest path tree.
        Boolean sptSet[] = new Boolean[V];

        // Initialize all distances as INFINITE and sptSet[] as false.
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0.
        dist[src] = 0;

        // Find shortest path for all vertices.
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices not yet processed.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed.
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the picked vertex.
            for (int v = 0; v < V; v++) {
                // Update dist[v] only if is not in sptSet, there is an edge from u to v,
                // and total weight of path from src to v through u is smaller than current value of dist[v].
                if (!sptSet[v] && graph[u][v] != 0 &&
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // Print the constructed distance array.
        printSolution(dist);
    }

    // Driver's code
    public static void main(String[] args) {
        ShortestPath t = new ShortestPath();

        /* Let us create the example graph discussed above.
           The graph is represented using an adjacency matrix where
           graph[i][j] = weight of edge from vertex i to j (0 means no direct edge) */
        int graph[][] = new int[][] {
            { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
            { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
            { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
            { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
            { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
            { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
            { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
            { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
            { 0, 0, 2, 0, 0, 0, 6, 7, 0 }
        };

        // Call Dijkstra's algorithm with source vertex 0.
        t.dijkstra(graph, 0);
    }
}

