package week_8;

class MatrixChainMultiplication {
    
    // Matrix Chain Multiplication using Dynamic Programming
    static int MatrixChainOrder(int p[], int i, int j) {
        // Create a table to store results of subproblems
        int[][] dp = new int[j+1][j+1];
        
        // Initialize the table
        // For chains of length 1, cost is 0
        for (int a = 1; a <= j; a++) {
            dp[a][a] = 0;
        }
        
        // L is chain length
        for (int L = 2; L <= j - i + 1; L++) {
            for (int k = i; k <= j - L + 1; k++) {
                int l = k + L - 1;
                dp[k][l] = Integer.MAX_VALUE;
                
                // Try each possible split point
                for (int m = k; m < l; m++) {
                    // Calculate cost for current split
                    int cost = dp[k][m] + dp[m+1][l] + p[k-1] * p[m] * p[l];
                    
                    // Update if this is better than previous
                    if (cost < dp[k][l]) {
                        dp[k][l] = cost;
                    }
                }
            }
        }
        
        return dp[i][j];
    }
    
    // Driver code
    public static void main(String args[]) {
        int arr[] = new int[] { 1, 2, 3, 4, 3 };
        int N = arr.length;
        
        // Function call
        System.out.println(
            "Minimum number of multiplications is "
            + MatrixChainOrder(arr, 1, N - 1));
    }
}