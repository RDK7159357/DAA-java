package internal;

public class MatrixChainMultiplication {
    
    static int matrixChainOrder(int[] dimensions) {
        int n = dimensions.length - 1; // number of matrices
        int[][] dp = new int[n + 1][n + 1];
        
        // Fill dp table for chain lengths from 2 to n
        for (int length = 2; length <= n; length++) {
            for (int i = 1; i <= n - length + 1; i++) {
                int j = i + length - 1;
                dp[i][j] = Integer.MAX_VALUE;
                
                // Try all possible split points
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + 
                              dimensions[i - 1] * dimensions[k] * dimensions[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        
        return dp[1][n];
    }
    
    public static void main(String[] args) {
        int[] dimensions = {1, 2, 3, 4, 3};
        System.out.println("Minimum multiplications: " + matrixChainOrder(dimensions));
    }
}