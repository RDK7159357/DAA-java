package week_11;

import java.util.Arrays;

class Nqueens {
    static int N = 8;

    // Print one solution
    static void printSolution(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] == 1 ? "Q " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check if it's safe to place a queen at (row, col)
    static boolean isSafe(int[][] board, int row, int col) {
        // Check column
        for (int i = 0; i < row; i++)
            if (board[i][col] == 1) return false;
        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1) return false;
        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++)
            if (board[i][j] == 1) return false;
        return true;
    }

    // Recursive utility to place queens row by row
    static void solveNQueensUtil(int[][] board, int row) {
        if (row == N) {
            printSolution(board);
            return;
        }
        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;
                solveNQueensUtil(board, row + 1);
                board[row][col] = 0; // backtrack
            }
        }
    }

    // Driver method to solve and print all solutions
    static void solveNQueens() {
        int[][] board = new int[N][N];
        for (int[] row : board) Arrays.fill(row, 0);
        solveNQueensUtil(board, 0);
    }

    public static void main(String[] args) {
        solveNQueens();
    }
}
