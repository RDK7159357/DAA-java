package week_6;

import java.util.*;

class ConnectRopes {

    // Function to return the minimum cost to connect ropes
    static int minCost(int arr[], int n) {
        // Create a min-heap (priority queue)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Add all rope lengths into the min-heap
        for (int i = 0; i < n; i++) {
            pq.add(arr[i]);
        }

        int totalCost = 0;

        // Loop until only one rope is left in the heap
        while (pq.size() > 1) {
            // Remove the two shortest ropes
            int first = pq.poll();
            int second = pq.poll();

            // Combine the ropes
            int cost = first + second;
            totalCost += cost;

            // Add the combined rope back into the heap
            pq.add(cost);
        }

        return totalCost;
    }

    // Driver program to test the function
    public static void main(String[] args) {
        int len[] = { 4, 3, 2, 6 };
        int size = len.length;
        System.out.println("Total cost for connecting ropes is " + minCost(len, size));
    }
}

