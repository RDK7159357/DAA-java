package week_2;

import java.util.HashMap;

public class QuadrupleCounter {

    // Function to count quadruples from four arrays such that their sum is equal to x
    public static int countQuadruples(int[] arr1, int[] arr2, int[] arr3, int[] arr4, int x) {
        int count = 0;
        // Create a hash table (map) to store (sum, frequency) for pairs from arr1 and arr2
        HashMap<Integer, Integer> sumMap = new HashMap<>();

        // Generate all possible sums for pairs in arr1 and arr2
        for (int a : arr1) {
            for (int b : arr2) {
                int sum = a + b;
                sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
            }
        }

        // For every pair in arr3 and arr4, find if the complement (x - pair sum) exists in sumMap
        for (int c : arr3) {
            for (int d : arr4) {
                int sumPair = c + d;
                int complement = x - sumPair;
                if (sumMap.containsKey(complement)) {
                    count += sumMap.get(complement);
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Example 1:
        int[] arr1 = {1, 4, 5, 6};
        int[] arr2 = {2, 3, 7, 8};
        int[] arr3 = {1, 4, 6, 10};
        int[] arr4 = {2, 4, 7, 8};
        int x1 = 30;
        int result1 = countQuadruples(arr1, arr2, arr3, arr4, x1);
        System.out.println("For x = " + x1 + ", number of quadruples: " + result1);
        // Expected Output: 4
        // The quadruples are: (4, 8, 10, 8), (5, 7, 10, 8), (5, 8, 10, 7), (6, 7, 10, 7)

        // Example 2:
        int x2 = 25;
        int result2 = countQuadruples(arr1, arr2, arr3, arr4, x2);
        System.out.println("For x = " + x2 + ", number of quadruples: " + result2);
        // Expected Output: 14
    }
}

