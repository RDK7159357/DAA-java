package week_10;

class Kpartition {
    // Recursive helper to try filling subset 'subset' starting from index curIdx
    static boolean isKPartitionPossibleRec(int[] arr, int[] subsetSum, boolean[] taken,
                                           int subset, int K, int N, int curIdx, int limitIdx, int target) {
        // If this subset has reached target sum
        if (subsetSum[subset] == target) {
            // If we've filled K-1 subsets successfully, the last one must also be valid
            if (subset == K - 2) {
                return true;
            }
            // Move on to fill the next subset, starting from index 0
            return isKPartitionPossibleRec(arr, subsetSum, taken, subset + 1, K, N, 0, limitIdx, target);
        }

        // Try to add remaining elements to this subset
        for (int i = curIdx; i <= limitIdx; i++) {
            if (!taken[i] && subsetSum[subset] + arr[i] <= target) {
                // choose element i
                taken[i] = true;
                subsetSum[subset] += arr[i];
                // recurse
                if (isKPartitionPossibleRec(arr, subsetSum, taken, subset, K, N, i + 1, limitIdx, target)) {
                    return true;
                }
                // backtrack
                taken[i] = false;
                subsetSum[subset] -= arr[i];
            }
        }
        return false;
    }

    // Returns true if arr[] can be partitioned into K subsets each of sum = totalSum/K
    static boolean isKPartitionPossible(int[] arr, int N, int K) {
        if (K == 1) return true;
        if (N < K) return false;

        int sum = 0;
        for (int v : arr) sum += v;
        if (sum % K != 0) return false;
        int target = sum / K;

        int[] subsetSum = new int[K];
        boolean[] taken = new boolean[N];
        // Initialize first subset with arr[0]
        subsetSum[0] = arr[0];
        taken[0] = true;

        // Recurse to fill remaining subsets
        return isKPartitionPossibleRec(arr, subsetSum, taken,
                                       0, K, N, 1, N - 1, target);
    }

    // Driver code
    public static void main(String[] args) {
        int[] arr1 = {2, 1, 4, 5, 6};
        int K1 = 3;
        System.out.printf("arr=%s, K=%d → %s\n",
            java.util.Arrays.toString(arr1), K1,
            isKPartitionPossible(arr1, arr1.length, K1) ? "Yes" : "No");

        int[] arr2 = {2, 1, 5, 5, 6};
        int K2 = 3;
        System.out.printf("arr=%s, K=%d → %s\n",
            java.util.Arrays.toString(arr2), K2,
            isKPartitionPossible(arr2, arr2.length, K2) ? "Yes" : "No");
    }
}

