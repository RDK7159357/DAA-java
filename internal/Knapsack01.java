package internal;

public class Knapsack01 {
    
    static int knapsack(int capacity, int[] weights, int[] values) {
        int n = values.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(
                        values[i - 1] + dp[i - 1][w - weights[i - 1]], // include item
                        dp[i - 1][w]  // exclude item
                    );
                } else {
                    dp[i][w] = dp[i - 1][w]; // can't include item
                }
            }
        }
        
        return dp[n][capacity];
    }
    
    public static void main(String[] args) {
        // Test case 1
        int[] values1 = {10, 20, 30};
        int[] weights1 = {1, 1, 1};
        int capacity1 = 2;
        System.out.println("Test 1 - Max value: " + knapsack(capacity1, weights1, values1));
        
        // Test case 2
        int[] values2 = {60, 100, 120};
        int[] weights2 = {10, 20, 30};
        int capacity2 = 50;
        System.out.println("Test 2 - Max value: " + knapsack(capacity2, weights2, values2));
    }
}