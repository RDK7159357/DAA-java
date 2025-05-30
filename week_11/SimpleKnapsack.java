package week_11;

import java.util.*;

public class SimpleKnapsack {
    static int N, W = 10, best = 0;
    static float[] w;
    static int[] v;

    public static void main(String[] args) {
        // weights and values
        w = new float[]{2f, 3.14f, 1.98f, 5f, 3f};
        v = new int[]{40, 50, 100, 95, 30};
        N = w.length;

        // sort items by decreasing value/weight ratio
        Integer[] idx = new Integer[N];
        for (int i = 0; i < N; i++) idx[i] = i;
        Arrays.sort(idx, (i, j) -> Float.compare(v[j] / w[j], v[i] / w[i]));
        float[] w2 = new float[N];
        int[]   v2 = new int[N];
        for (int i = 0; i < N; i++) {
            w2[i] = w[idx[i]];
            v2[i] = v[idx[i]];
        }
        w = w2; v = v2;

        // branch-and-bound DFS
        dfs(0, 0, 0);
        System.out.println("Maximum possible profit = " + best);
    }

    // i = current item index
    // cw = current weight, cp = current profit
    static void dfs(int i, int cw, int cp) {
        if (cw > W) return;                 // overweight
        best = Math.max(best, cp);          // update best
        if (i == N) return;                 // no more items

        // compute upper bound via fractional fill
        float bound = cp;
        int rem = W - cw;
        for (int j = i; j < N; j++) {
            if (w[j] <= rem) {
                rem -= (int) w[j];
                bound += v[j];
            } else {
                bound += v[j] * rem / w[j];
                break;
            }
        }
        if (bound <= best) return;          // prune if can't beat best

        // branch: take item i
        dfs(i + 1, cw + (int) w[i], cp + v[i]);
        // branch: skip item i
        dfs(i + 1, cw, cp);
    }
}

