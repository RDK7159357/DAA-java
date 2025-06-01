package internal;

public class Knapsack01 {
    
    // Bottom-up Dynamic Programming approach for 0/1 Knapsack
    static int knapSackDP(int W, int wt[], int val[], int n) {
        // Create a 2D array to store results of subproblems
        int dp[][] = new int[n + 1][W + 1];
        
        // Build table dp[][] in bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                // Base case: no items or no capacity
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
                // If weight of current item is less than or equal to capacity j
                else if (wt[i - 1] <= j) {
                    // Maximum of including or excluding the current item
                    dp[i][j] = Math.max(
                        val[i - 1] + dp[i - 1][j - wt[i - 1]],
                        dp[i - 1][j]
                    );
                }
                // If weight of current item is more than capacity j
                else {
                    // Cannot include this item
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        // Return the maximum value that can be put in a knapsack of capacity W
        return dp[n][W];
    }
    
    // Driver program to test the above function
    public static void main(String args[]) {
        // Example from the problem statement
        int val[] = { 10, 20, 30 };
        int wt[] = { 1, 1, 1 };
        int W = 2;
        int n = val.length;
        
        System.out.println("Example from problem statement:");
        System.out.println("Items: 3 with weights [1, 1, 1] and values [10, 20, 30]");
        System.out.println("Knapsack capacity: 2");
        
        // Using bottom-up DP approach
        System.out.println("Maximum value using Bottom-up DP approach: " + 
                          knapSackDP(W, wt, val, n));
        
        // Another example
        int val2[] = { 60, 100, 120 };
        int wt2[] = { 10, 20, 30 };
        int W2 = 50;
        int n2 = val2.length;
        
        System.out.println("\nAnother example:");
        System.out.println("Items: 3 with weights [10, 20, 30] and values [60, 100, 120]");
        System.out.println("Knapsack capacity: 50");
        
        System.out.println("Maximum value using Bottom-up DP approach: " + 
                          knapSackDP(W2, wt2, val2, n2));
    }
}