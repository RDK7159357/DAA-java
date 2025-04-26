package week_8;

import java.util.Scanner;

public class SherlockCost {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read number of test cases
        int t = scanner.nextInt();
        
        while (t-- > 0) {
            // Read length of array B
            int n = scanner.nextInt();
            
            // Read array B
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                b[i] = scanner.nextInt();
            }
            
            // Calculate and print the maximum cost
            System.out.println(calculateMaxCost(b, n));
        }
        
        scanner.close();
    }
    
    /**
     * Calculates the maximum possible cost for array A
     * 
     * @param b The input array B where 1 <= A[i] <= B[i]
     * @param n The length of array B
     * @return The maximum possible sum of absolute differences
     */
    public static int calculateMaxCost(int[] b, int n) {
        // dp[i][0] represents maximum cost when A[i] = 1
        // dp[i][1] represents maximum cost when A[i] = B[i]
        int[][] dp = new int[n][2];
        
        // Base case: for i=0, the cost is 0 as there's no previous element
        dp[0][0] = 0; // A[0] = 1
        dp[0][1] = 0; // A[0] = B[0]
        
        // Fill the dp table
        for (int i = 1; i < n; i++) {
            // Case 1: A[i] = 1
            // Previous element can be either 1 or B[i-1]
            dp[i][0] = Math.max(
                dp[i-1][0] + Math.abs(1 - 1),           // A[i-1] = 1, A[i] = 1
                dp[i-1][1] + Math.abs(1 - b[i-1])       // A[i-1] = B[i-1], A[i] = 1
            );
            
            // Case 2: A[i] = B[i]
            // Previous element can be either 1 or B[i-1]
            dp[i][1] = Math.max(
                dp[i-1][0] + Math.abs(b[i] - 1),        // A[i-1] = 1, A[i] = B[i]
                dp[i-1][1] + Math.abs(b[i] - b[i-1])    // A[i-1] = B[i-1], A[i] = B[i]
            );
        }
        
        // Return the maximum of the two possible values for the last element
        return Math.max(dp[n-1][0], dp[n-1][1]);
    }
}