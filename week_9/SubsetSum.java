package week_9;

import java.util.ArrayList;
import java.util.List;

public class SubsetSum {
    static boolean flag = false;

    static void printSubsetSum(int i, int n, int[] set, int targetSum,
                               List<Integer> subset) {
        // Base: if we've hit the target, print current subset
        if (targetSum == 0) {
            System.out.println(subset);
            flag = true;
            return;
        }
        // If no more items or targetSum went negative, backtrack
        if (i == n || targetSum < 0) {
            return;
        }

        // 1) Include set[i]
        subset.add(set[i]);
        printSubsetSum(i + 1, n, set, targetSum - set[i], subset);
        subset.remove(subset.size() - 1);

        // 2) Exclude set[i]
        printSubsetSum(i + 1, n, set, targetSum, subset);
    }

    public static void main(String[] args) {
        // Test case 1
        int[] set1 = { 1, 2, 1 };
        int sum1 = 3;
        int n1 = set1.length;
        List<Integer> subset1 = new ArrayList<>();
        System.out.println("Output 1:");
        printSubsetSum(0, n1, set1, sum1, subset1);
        if (!flag) System.out.println("There is no such subset");
        System.out.println();
        flag = false;

        // Test case 2
        int[] set2 = { 3, 34, 4, 12, 5, 2 };
        int sum2 = 30;
        int n2 = set2.length;
        List<Integer> subset2 = new ArrayList<>();
        System.out.println("Output 2:");
        printSubsetSum(0, n2, set2, sum2, subset2);
        if (!flag) {
            System.out.println("There is no such subset");
        }
    }
}
