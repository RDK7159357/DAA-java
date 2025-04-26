package week_7;

public class OBST {
    /* A Dynamic Programming based function that calculates
    minimum cost of a Binary Search Tree. */
    static int optimalSearchTree(int keys[], int freq[], int n) {
        /* Create an auxiliary 2D matrix to store results of subproblems */
        int cost[][] = new int[n + 1][n + 1];
        
        // Initialize cost[][] for single key trees
        // Cost of a single key is equal to its frequency
        for (int i = 0; i < n; i++) {
            cost[i][i] = freq[i];
        }
        
        // Consider chains of increasing lengths (L = 2 to n)
        for (int L = 2; L <= n; L++) {
            // i is the starting index of the chain
            for (int i = 0; i <= n - L; i++) {
                // j is the ending index of the chain
                int j = i + L - 1;
                
                // Initialize cost[i][j] to a maximum value
                cost[i][j] = Integer.MAX_VALUE;
                
                // Try making each key from i to j as the root
                for (int r = i; r <= j; r++) {
                    // Calculate cost when keys[r] becomes root of the subtree
                    // Cost = Sum of frequencies + Cost of left subtree + Cost of right subtree
                    int c = ((r > i) ? cost[i][r - 1] : 0) + 
                            ((r < j) ? cost[r + 1][j] : 0) + 
                            sum(freq, i, j);
                    
                    // Update cost[i][j] if this is less costly
                    if (c < cost[i][j]) {
                        cost[i][j] = c;
                    }
                }
            }
        }
        
        // Return the minimum cost for the entire array (0 to n-1)
        return cost[0][n - 1];
    }
    
    // A utility function to get sum of array elements freq[i] to freq[j]
    static int sum(int freq[], int i, int j) {
        int s = 0;
        for (int k = i; k <= j; k++) {
            s += freq[k];
        }
        return s;
    }
    
    public static void main(String[] args) {
        int keys[] = { 10, 12, 20 };
        int freq[] = { 34, 8, 50 };
        int n = keys.length;
        System.out.println("Cost of Optimal BST is " 
                + optimalSearchTree(keys, freq, n));
    }
}
