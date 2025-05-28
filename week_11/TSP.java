package week_11;

import java.util.*;

class TSP{
    static int N = 4;
    static int[] final_path = new int[N + 1];
    static boolean[] visited = new boolean[N];
    static int final_res = Integer.MAX_VALUE;

    // copy current best path to final_path
    static void copyToFinal(int[] curr_path) {
        System.arraycopy(curr_path, 0, final_path, 0, N);
        final_path[N] = curr_path[0];
    }

    // first minimum edge cost from vertex i
    static int firstMin(int[][] adj, int i) {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < N; k++)
            if (adj[i][k] < min && i != k)
                min = adj[i][k];
        return min;
    }

    // second minimum edge cost from vertex i
    static int secondMin(int[][] adj, int i) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int j = 0; j < N; j++) {
            if (i == j) continue;
            int cost = adj[i][j];
            if (cost <= first) {
                second = first;
                first = cost;
            } else if (cost < second) {
                second = cost;
            }
        }
        return second;
    }

    // recursive branch-and-bound
    static void TSPRec(int[][] adj, int curr_bound, int curr_weight,
                       int level, int[] curr_path) {
        if (level == N) {
            // check last to first edge
            if (adj[curr_path[level - 1]][curr_path[0]] != 0) {
                int res = curr_weight + adj[curr_path[level - 1]][curr_path[0]];
                if (res < final_res) {
                    copyToFinal(curr_path);
                    final_res = res;
                }
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (adj[curr_path[level - 1]][i] != 0 && !visited[i]) {
                int temp = curr_bound;
                curr_weight += adj[curr_path[level - 1]][i];

                // compute new bound
                if (level == 1) {
                    curr_bound -= (firstMin(adj, curr_path[level - 1])
                                 + firstMin(adj, i)) / 2;
                } else {
                    curr_bound -= (secondMin(adj, curr_path[level - 1])
                                 + firstMin(adj, i)) / 2;
                }

                if (curr_bound + curr_weight < final_res) {
                    curr_path[level] = i;
                    visited[i] = true;
                    TSPRec(adj, curr_bound, curr_weight, level + 1, curr_path);
                }

                // backtrack
                curr_weight -= adj[curr_path[level - 1]][i];
                curr_bound = temp;
                Arrays.fill(visited, level, level + 1, false); // reset visit of i
                visited[curr_path[level - 1]] = true;
            }
        }
    }

    // sets up initial bound and calls TSPRec
    static void solveTSP(int[][] adj) {
        int[] curr_path = new int[N + 1];
        int curr_bound = 0;
        Arrays.fill(visited, false);

        // compute initial bound
        for (int i = 0; i < N; i++)
            curr_bound += firstMin(adj, i) + secondMin(adj, i);
        curr_bound = (curr_bound + 1) / 2;

        visited[0] = true;
        curr_path[0] = 0;

        TSPRec(adj, curr_bound, 0, 1, curr_path);
    }

    public static void main(String[] args) {
        int[][] adj = {
            { 0, 10, 15, 20 },
            { 10, 0, 35, 25 },
            { 15, 35, 0, 30 },
            { 20, 25, 30, 0 }
        };
        solveTSP(adj);
        System.out.printf("Minimum cost : %d\n", final_res);
        System.out.print("Path Taken : ");
        for (int i = 0; i <= N; i++) {
            System.out.print(final_path[i] + " ");
        }
        System.out.println();
    }
}

