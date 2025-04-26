package week_8;

public class StockMaximize {
    public static void main(String[] args) {
        int[] prices = {1, 2, 100};
        System.out.println("Maximum Profit: " + maxProfit(prices));
        
        // Additional test cases
        int[] prices2 = {5, 3, 2};
        System.out.println("Maximum Profit: " + maxProfit(prices2));
        
        int[] prices3 = {1, 3, 1, 2};
        System.out.println("Maximum Profit: " + maxProfit(prices3));
    }
    
    public static long maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int n = prices.length;
        long maxProfit = 0;
        
        // Find the maximum price in the future for each day
        int maxSoFar = prices[n - 1];
        
        // Traverse the array from right to left
        for (int i = n - 2; i >= 0; i--) {
            // If current price is less than maxSoFar, we can make profit
            if (prices[i] < maxSoFar) {
                maxProfit += maxSoFar - prices[i];
            }
            
            // Update maxSoFar if current price is higher
            maxSoFar = Math.max(maxSoFar, prices[i]);
        }
        
        return maxProfit;
    }
}
