// Java code for Bridge and Torch problem
// using Tabulation
package week_8;
import java.util.*;

class GfG {

    // Function to calculate minimum crossing 
    // time using tabulation
    static int bridgeAndTorch(ArrayList<Integer> times) {
        Collections.sort(times);
        int n = times.size();

        // Create a dp array to store minimum crossing 
        // times for each count of people
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = times.get(0);
        if (n > 1) dp[2] = times.get(1);
        if (n > 2) dp[3] = times.get(0) 
                     + times.get(1) + times.get(2);

        // Fill dp array iteratively for 
        // each count of people
        for (int i = 4; i <= n; i++) {
          
            // Case 1: Send two fastest people first 
            // and the fastest returns
            int option1 = times.get(1) + times.get(0) + 
                            times.get(i - 1) + times.get(1);
            
            // Case 2: Send two slowest people first
            // and the second fastest returns
            int option2 = times.get(i - 1) + times.get(0) 
                           + times.get(i - 2) + times.get(0);

            // Minimum time required for i people
            dp[i] = Math.min(dp[i - 2] + option1, 
                             dp[i - 2] + option2);
        }

        return dp[n];
    }

    public static void main(String[] args) {
      
        ArrayList<Integer> times 
          = new ArrayList<>(Arrays.asList(10, 20, 30));
      
        System.out.println(bridgeAndTorch(times));
    }
}
