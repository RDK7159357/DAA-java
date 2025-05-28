package week_9;

class GFG {
    // Check if it's safe to place num at board[row][col]
    public static boolean isSafe(int[][] board, int row, int col, int num) {
        int N = board.length;
        // Row and column check
        for (int d = 0; d < N; d++) {
            if (board[row][d] == num || board[d][col] == num)
                return false;
        }
        // 3x3 subgrid check
        int sqrt = (int) Math.sqrt(N);
        int boxRowStart = row - row % sqrt, boxColStart = col - col % sqrt;
        for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
            for (int c = boxColStart; c < boxColStart + sqrt; c++) {
                if (board[r][c] == num)
                    return false;
            }
        }
        return true;
    }

    // Solve the Sudoku via backtracking
    public static boolean solveSudoku(int[][] board, int N) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= N; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board, N))
                                return true;
                            board[row][col] = 0; // backtrack
                        }
                    }
                    return false; // trigger backtracking
                }
            }
        }
        return true; // no empty cell left
    }

    // Print the board
    public static void print(int[][] board, int N) {
        for (int r = 0; r < N; r++) {
            for (int d = 0; d < N; d++)
                System.out.print(board[r][d] + " ");
            System.out.println();
        }
    }

    // Driver code
    public static void main(String[] args) {
        int[][] board = {
            {3, 0, 6, 5, 0, 8, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 3, 1},
            {0, 0, 3, 0, 1, 0, 0, 8, 0},
            {9, 0, 0, 8, 6, 3, 0, 0, 5},
            {0, 5, 0, 0, 9, 0, 6, 0, 0},
            {1, 3, 0, 0, 0, 0, 2, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 7, 4},
            {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };
        int N = board.length;
        if (solveSudoku(board, N))
            print(board, N);
        else
            System.out.println("No solution");
    }
}
