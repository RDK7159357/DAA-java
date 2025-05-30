package week_9;

import java.util.ArrayList;
import java.util.List;

public class SubsetSum {
    static boolean found = false;
    
    static void solve(int i, int[] arr, int target, List<Integer> sub) {
        if (target == 0) {
            System.out.println(sub);
            found = true;
            return;
        }
        if (i == arr.length || target < 0) return;
        
        sub.add(arr[i]);
        solve(i + 1, arr, target - arr[i], sub);
        sub.remove(sub.size() - 1);
        
        solve(i + 1, arr, target, sub);
    }
    
    public static void main(String[] args) {
        int[][] sets = {{1, 2, 1}, {3, 34, 4, 12, 5, 2}};
        int[] targets = {3, 30};
        
        for (int t = 0; t < sets.length; t++) {
            System.out.println("Output " + (t + 1) + ":");
            found = false;
            solve(0, sets[t], targets[t], new ArrayList<>());
            if (!found) System.out.println("There is no such subset");
            System.out.println();
        }
    }
}