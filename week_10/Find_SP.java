package week_10;

import java.util.Arrays;

class Find_SP {
    static final int R = 12;
    static final int C = 10;
    static int[] rowNum = { -1, 0, 0, 1 };
    static int[] colNum = { 0, -1, 1, 0 };
    static int min_dist;

    // Check if (x,y) is within bounds
    static boolean isValid(int x, int y) {
        return (x >= 0 && x < R && y >= 0 && y < C);
    }

    // Check if we can step on (x,y)
    static boolean isSafe(boolean[][] safe, boolean[][] visited, int x, int y) {
        return isValid(x, y) && safe[x][y] && !visited[x][y];
    }

    // Mark all landmines and their 4-neighbors as unsafe
    static void markUnsafeCells(int[][] mat, boolean[][] safe) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (mat[i][j] == 0) {
                    safe[i][j] = false;
                    for (int k = 0; k < 4; k++) {
                        int ni = i + rowNum[k], nj = j + colNum[k];
                        if (isValid(ni, nj)) safe[ni][nj] = false;
                    }
                }
            }
        }
    }

    // Backtracking to find shortest from (i,j) to any last-col cell
    static void findShortestPathUtil(boolean[][] safe, boolean[][] visited,
                                     int i, int j, int dist) {
        if (j == C - 1) {
            min_dist = Math.min(min_dist, dist);
            return;
        }
        // try 4 directions
        for (int k = 0; k < 4; k++) {
            int ni = i + rowNum[k], nj = j + colNum[k];
            if (isSafe(safe, visited, ni, nj) && dist + 1 < min_dist) {
                visited[ni][nj] = true;
                findShortestPathUtil(safe, visited, ni, nj, dist + 1);
                visited[ni][nj] = false;
            }
        }
    }

    // Wrapper: prepare safe matrix and launch search from col 0
    static void findShortestPath(int[][] mat) {
        boolean[][] safe = new boolean[R][C];
        for (boolean[] row : safe) Arrays.fill(row, true);
        markUnsafeCells(mat, safe);

        boolean[][] visited = new boolean[R][C];
        min_dist = Integer.MAX_VALUE;

        // try each safe start in first column
        for (int i = 0; i < R; i++) {
            if (safe[i][0]) {
                visited[i][0] = true;
                findShortestPathUtil(safe, visited, i, 0, 0);
                visited[i][0] = false;
            }
        }

        if (min_dist != Integer.MAX_VALUE)
            System.out.println("Length of shortest safe route is " + min_dist);
        else
            System.out.println("No safe path exists");
    }

    public static void main(String[] args) {
        int[][] mat = {
            {1,1,1,1,1,1,1,1,1,1},
            {1,0,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,1,1,1,1,1},
            {1,1,1,1,0,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,0,1,1,1,1},
            {1,0,1,1,1,1,1,1,0,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {0,1,1,1,1,0,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,1,1,1,1,1,1}
        };
        findShortestPath(mat);
    }
}
