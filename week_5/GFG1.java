package week_5;

import java.util.*;

class GFG1 {

    // This function outputs the required order of storage for the programs
    // and computes the Minimum Retrieval Time (MRT) for that order.
    static void findOrderMRT(int[] L, int n) {
        // Sort the program lengths in non-decreasing order.
        // This is the core of the greedy strategy: shorter programs first.
        Arrays.sort(L);

        System.out.println("Optimal order in which programs are to be stored is:");

        // Print the optimal order.
        for (int i = 0; i < n; i++) {
            System.out.print(L[i] + " ");
        }
        System.out.println();

        // Calculate the Minimum Retrieval Time (MRT).
        // The idea is to compute a running cumulative sum (total time taken to retrieve
        // up to the current program) and then add each cumulative value to the MRT.
        double MRT = 0;
        int cumulativeSum = 0;
        for (int i = 0; i < n; i++) {
            cumulativeSum += L[i];  // Add the current program length to the cumulative sum.
            MRT += cumulativeSum;     // Each program's retrieval time is the sum of its own length
                                      // plus all lengths before it.
        }

        System.out.println("Minimum Retrieval Time of this order is: " + MRT);
    }

    // Driver Code
    public static void main(String[] args) {
        int[] L = {2, 5, 4};
        int n = L.length;
        findOrderMRT(L, n);
    }
}

