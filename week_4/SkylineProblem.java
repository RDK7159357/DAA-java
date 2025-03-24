package week_4;


import java.util.*;

public class SkylineProblem {

    // Main function to compute the skyline for an array of buildings.
    public static List<int[]> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0)
            return new ArrayList<>();
        return computeSkyline(buildings, 0, buildings.length - 1);
    }

    // Recursive divide and conquer function.
    private static List<int[]> computeSkyline(int[][] buildings, int left, int right) {
        if (left == right) {
            List<int[]> skyline = new ArrayList<>();
            // A single building's skyline: starting at left edge with height, ending at right edge with 0 height.
            skyline.add(new int[]{buildings[left][0], buildings[left][1]});
            skyline.add(new int[]{buildings[left][2], 0});
            return skyline;
        }
        int mid = left + (right - left) / 2;
        List<int[]> leftSky = computeSkyline(buildings, left, mid);
        List<int[]> rightSky = computeSkyline(buildings, mid + 1, right);
        return mergeSkylines(leftSky, rightSky);
    }

    // Merge two skylines into one.
    private static List<int[]> mergeSkylines(List<int[]> left, List<int[]> right) {
        List<int[]> merged = new ArrayList<>();
        int i = 0, j = 0, h1 = 0, h2 = 0;

        while (i < left.size() && j < right.size()) {
            int x;
            // Choose the strip with the smaller x-coordinate
            if (left.get(i)[0] < right.get(j)[0]) {
                x = left.get(i)[0];
                h1 = left.get(i)[1];
                i++;
            } else if (left.get(i)[0] > right.get(j)[0]) {
                x = right.get(j)[0];
                h2 = right.get(j)[1];
                j++;
            } else {
                // Both x coordinates are the same; take maximum height
                x = left.get(i)[0];
                h1 = left.get(i)[1];
                h2 = right.get(j)[1];
                i++;
                j++;
            }
            int maxHeight = Math.max(h1, h2);
            addStrip(merged, x, maxHeight);
        }
        // Append remaining strips from left and right skylines.
        while (i < left.size()) {
            addStrip(merged, left.get(i)[0], left.get(i)[1]);
            i++;
        }
        while (j < right.size()) {
            addStrip(merged, right.get(j)[0], right.get(j)[1]);
            j++;
        }
        return merged;
    }

    // Adds a new strip to the skyline, ensuring no redundant strips.
    private static void addStrip(List<int[]> skyline, int x, int height) {
        // Only add the strip if the height changes.
        if (skyline.isEmpty() || skyline.get(skyline.size() - 1)[1] != height) {
            skyline.add(new int[]{x, height});
        }
    }

    // Testing the Skyline algorithm with sample input.
    public static void main(String[] args) {
        int[][] buildings = {
            {1, 11, 5},
            {2, 6, 7},
            {3, 13, 9},
            {12, 7, 16},
            {14, 3, 25},
            {19, 18, 22},
            {23, 13, 29},
            {24, 4, 28}
        };

        List<int[]> skyline = getSkyline(buildings);
        System.out.print("Skyline: { ");
        for (int[] strip : skyline) {
            System.out.print("{" + strip[0] + ", " + strip[1] + "} ");
        }
        System.out.println("}");
    }
}

