// Java program for Floyd Warshall All Pairs Shortest Path algorithm. 
package internal;

class F {
    static int INF = 99999;
    
    public static void main(String[] args) {
        int[][] g = {
            {0, 5, INF, 10},
            {INF, 0, 3, INF},
            {INF, INF, 0, 1},
            {INF, INF, INF, 0}
        };
        
        // Floyd-Warshall algorithm
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (g[i][k] != INF && g[k][j] != INF && 
                        g[i][k] + g[k][j] < g[i][j]) {
                        g[i][j] = g[i][k] + g[k][j];
                    }
                }
            }
        }
        
        // Print result
        for (int[] row : g) {
            for (int val : row) {
                System.out.print((val == INF ? "∞" : val) + " ");
            }
            System.out.println();
        }
    }
}