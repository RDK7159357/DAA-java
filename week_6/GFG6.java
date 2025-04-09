package week_6;

import java.util.*;

class GFG6 {
    // Function to return the index with maximum value in dist[]
    static int maxindex(int[] dist, int n) {
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (dist[i] > dist[max])
                max = i;
        }
        return max;
    }

    // Function to select k cities to place ATMs (or centers)
    static void selectKcities(int n, int[][] weights, int k) {
        int[] dist = new int[n]; // stores distance of every city to nearest center
        Arrays.fill(dist, Integer.MAX_VALUE);

        List<Integer> centers = new ArrayList<>();

        // Choose the first city arbitrarily as a center
        centers.add(0);

        // Update distances to the first center
        for (int i = 0; i < n; i++) {
            dist[i] = weights[0][i];
        }

        // Choose remaining k-1 centers
        for (int count = 1; count < k; count++) {
            int maxCity = maxindex(dist, n);
            centers.add(maxCity);

            // Update distances to nearest of selected centers
            for (int i = 0; i < n; i++) {
                dist[i] = Math.min(dist[i], weights[maxCity][i]);
            }
        }

        // Print the selected centers
        System.out.println("Selected centers:");
        for (int city : centers) {
            System.out.print(city + " ");
        }

        // Print the maximum distance any city has to its nearest center
        int maxDist = 0;
        for (int d : dist) {
            maxDist = Math.max(maxDist, d);
        }
        System.out.println("\nMaximum distance to nearest center: " + maxDist);
    }

    // Driver Code
    public static void main(String[] args) {
        int n = 4;
        int[][] weights = {
            { 0, 4, 8, 5 },
            { 4, 0, 10, 7 },
            { 8, 10, 0, 9 },
            { 5, 7, 9, 0 }
        };
        int k = 2;

        // Function Call
        selectKcities(n, weights, k);
    }
}

