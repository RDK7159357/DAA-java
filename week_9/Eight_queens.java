package week_9;

public class Eight_queens {
    static int N = 8;

    public static void main(String[] args) {
        int[][] board = new int[N][N];
        if (!placeQueen(0, board)) {
            System.out.println("No solution found");
        }
    }

    static boolean placeQueen(int colIdx, int[][] grid) {
        if (colIdx == N) {
            printGrid(grid);
            return true;
        }
        for (int row = 0; row < N; row++) {
            if (isSafe(grid, row, colIdx)) {
                grid[row][colIdx] = 1;
                if (placeQueen(colIdx + 1, grid)) return true;
                grid[row][colIdx] = 0;
            }
        }
        return false;
    }

    static boolean isSafe(int[][] mat, int r, int c) {
        // left row
        for (int cc = 0; cc < c; cc++) if (mat[r][cc] == 1) return false;

        // upper-left diagonal
        for (int rr = r, cc = c; rr >= 0 && cc >= 0; rr--, cc--)
            if (mat[rr][cc] == 1) return false;

        // lower-left diagonal
        for (int rr = r, cc = c; rr < N && cc >= 0; rr++, cc--)
            if (mat[rr][cc] == 1) return false;

        return true;
    }

    static void printGrid(int[][] arr) {
        for (int[] line : arr) {
            for (int val : line) {
                System.out.print(val == 1 ? "Q " : ". ");
            }
            System.out.println();
        }
    }
}

