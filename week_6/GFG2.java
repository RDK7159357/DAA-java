package week_6;

import java.util.Arrays;
import java.util.Collections;

class GFG2 {

    // Returns minimum cost required to break the board into squares.
    // X[] represents the costs of horizontal cuts.
    // Y[] represents the costs of vertical cuts.
    // m and n are the number of horizontal and vertical cuts respectively.
    static int minimumCostOfBreaking(Integer X[], Integer Y[], int m, int n) {
        // Sort the horizontal and vertical cost arrays in descending order.
        Arrays.sort(X, Collections.reverseOrder());
        Arrays.sort(Y, Collections.reverseOrder());

        int totalCost = 0;
        int i = 0, j = 0;
        // Initially, there is 1 piece along both dimensions.
        int horizontalPieces = 1;   // Number of vertical segments
        int verticalPieces = 1;     // Number of horizontal segments

        // Process both arrays until one is completely processed.
        while (i < m && j < n) {
            // If the current horizontal cut cost is greater than or equal to the current vertical cost,
            // perform a horizontal cut.
            if (X[i] >= Y[j]) {
                totalCost += X[i] * verticalPieces;
                horizontalPieces++; // A horizontal cut increases the number of vertical pieces.
                i++;
            } else {
                // Otherwise, perform a vertical cut.
                totalCost += Y[j] * horizontalPieces;
                verticalPieces++;   // A vertical cut increases the number of horizontal pieces.
                j++;
            }
        }

        // Process any remaining horizontal cuts.
        while (i < m) {
            totalCost += X[i] * verticalPieces;
            horizontalPieces++;
            i++;
        }

        // Process any remaining vertical cuts.
        while (j < n) {
            totalCost += Y[j] * horizontalPieces;
            verticalPieces++;
            j++;
        }

        return totalCost;
    }

    // Driver program
    public static void main(String[] args) {
        // Given board dimensions: m and n.
        // Here m and n represent the number of segments, so the number of cuts are m-1 and n-1 respectively.
        int m = 6, n = 4;
        // Horizontal cut costs (m-1 = 5 cuts).
        Integer X[] = {2, 1, 3, 1, 4};
        // Vertical cut costs (n-1 = 3 cuts).
        Integer Y[] = {4, 1, 2};

        // Compute and print the minimum cost.
        System.out.print("Minimum cost to cut the board into squares is: " +
                minimumCostOfBreaking(X, Y, m - 1, n - 1));
    }
}

