package week_4;

public class TilingProblem {
    // Global tile counter (each tile will have a unique number)
    static int tile = 1;
    // Board to be tiled
    static int[][] board;

    /**
     * Tiles an n x n board with one missing cell using L-shaped tiles.
     * @param size      The size of the board/sub-board.
     * @param top       The top row index of the current sub-board.
     * @param left      The left column index of the current sub-board.
     * @param missingR  The row index of the missing cell in this sub-board.
     * @param missingC  The column index of the missing cell in this sub-board.
     */
    public static void tile(int size, int top, int left, int missingR, int missingC) {
        // Base Case: a 2x2 board can be filled with one L-shaped tile.
        if (size == 2) {
            // Fill all four cells except the missing one
            for (int i = top; i < top + 2; i++) {
                for (int j = left; j < left + 2; j++) {
                    if (i == missingR && j == missingC) {
                        continue; // Skip the already missing cell
                    }
                    board[i][j] = tile;
                }
            }
            tile++; // Use a new tile number for subsequent placements
            return;
        }

        // Calculate the middle point (dividing the board into four quadrants)
        int mid = size / 2;

        // Determine center coordinates for the four quadrants
        // These coordinates represent the bottom-right cell of the top-left quadrant,
        // bottom-left of the top-right, top-right of the bottom-left, and top-left of the bottom-right.
        int centerTopLeftR = top + mid - 1;
        int centerTopLeftC = left + mid - 1;
        int centerTopRightR = top + mid - 1;
        int centerTopRightC = left + mid;
        int centerBottomLeftR = top + mid;
        int centerBottomLeftC = left + mid - 1;
        int centerBottomRightR = top + mid;
        int centerBottomRightC = left + mid;

        // Place an L-shaped tile at the center covering three quadrants.
        // The quadrant that already has the missing cell is skipped.
        int currentTile = tile++;
        // Top-left quadrant
        if (!(missingR < top + mid && missingC < left + mid)) {
            board[centerTopLeftR][centerTopLeftC] = currentTile;
        }
        // Top-right quadrant
        if (!(missingR < top + mid && missingC >= left + mid)) {
            board[centerTopRightR][centerTopRightC] = currentTile;
        }
        // Bottom-left quadrant
        if (!(missingR >= top + mid && missingC < left + mid)) {
            board[centerBottomLeftR][centerBottomLeftC] = currentTile;
        }
        // Bottom-right quadrant
        if (!(missingR >= top + mid && missingC >= left + mid)) {
            board[centerBottomRightR][centerBottomRightC] = currentTile;
        }

        // Recursively tile each of the four quadrants.
        // For each quadrant, the missing cell is either the original missing cell (if it lies in that quadrant)
        // or the cell just filled by the L-tile (serving as an artificial missing cell).

        // Top-left quadrant
        int newMissingR = (missingR < top + mid && missingC < left + mid) ? missingR : centerTopLeftR;
        int newMissingC = (missingR < top + mid && missingC < left + mid) ? missingC : centerTopLeftC;
        tile(mid, top, left, newMissingR, newMissingC);

        // Top-right quadrant
        newMissingR = (missingR < top + mid && missingC >= left + mid) ? missingR : centerTopRightR;
        newMissingC = (missingR < top + mid && missingC >= left + mid) ? missingC : centerTopRightC;
        tile(mid, top, left + mid, newMissingR, newMissingC);

        // Bottom-left quadrant
        newMissingR = (missingR >= top + mid && missingC < left + mid) ? missingR : centerBottomLeftR;
        newMissingC = (missingR >= top + mid && missingC < left + mid) ? missingC : centerBottomLeftC;
        tile(mid, top + mid, left, newMissingR, newMissingC);

        // Bottom-right quadrant
        newMissingR = (missingR >= top + mid && missingC >= left + mid) ? missingR : centerBottomRightR;
        newMissingC = (missingR >= top + mid && missingC >= left + mid) ? missingC : centerBottomRightC;
        tile(mid, top + mid, left + mid, newMissingR, newMissingC);
    }

    public static void main(String[] args) {
        // Example 1: size = 2, missing cell at (0, 0)
        // Expected output:
        // -1 1
        //  1 1
        int size1 = 2;
        board = new int[size1][size1];
        // Mark all cells initially as 0.
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size1; j++) {
                board[i][j] = 0;
            }
        }
        // Mark the missing cell with -1.
        int missingR = 0;
        int missingC = 0;
        board[missingR][missingC] = -1;

        // Reset tile counter before tiling
        tile = 1;
        tile(size1, 0, 0, missingR, missingC);
        System.out.println("Tiled board for size = 2:");
        printBoard(size1);

        // Example 2: size = 4, missing cell at (0, 0)
        // Expected output (tile numbers may vary):
        // -1 3 2 2
        //  3 3 1 2
        //  4 1 1 5
        //  4 4 5 5
        int size2 = 4;
        board = new int[size2][size2];
        for (int i = 0; i < size2; i++) {
            for (int j = 0; j < size2; j++) {
                board[i][j] = 0;
            }
        }
        missingR = 0;
        missingC = 0;
        board[missingR][missingC] = -1;

        tile = 1; // reset tile counter for new tiling
        tile(size2, 0, 0, missingR, missingC);
        System.out.println("\nTiled board for size = 4:");
        printBoard(size2);
    }

    // Utility method to print the board
    public static void printBoard(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

