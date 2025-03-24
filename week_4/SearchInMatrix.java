package week_4;

public class SearchInMatrix {
    public static void main(String[] args) {
        int[][] mat = new int[][] { 
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {27, 29, 37, 48},
            {32, 33, 39, 50}
        };
        int rowCount = 4, colCount = 4;
        // Example: Search for key 50
        int key = 50;
        boolean found = search(mat, 0, rowCount - 1, 0, colCount - 1, key);
        System.out.println("Key " + key + (found ? " found." : " not found."));

        // Alternatively, test the search for every element in the matrix
        System.out.println("\nVerifying search on each element in the matrix:");
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                int current = mat[i][j];
                if (search(mat, 0, rowCount - 1, 0, colCount - 1, current))
                    System.out.println("Element " + current + " found.");
                else
                    System.out.println("Element " + current + " not found.");
            }
        }
    }

    /**
     * Searches for a key in a row-wise and column-wise sorted submatrix defined by
     * rows [fromRow, toRow] and columns [fromCol, toCol] using divide and conquer.
     *
     * @param mat     The 2D matrix.
     * @param fromRow Starting row index.
     * @param toRow   Ending row index.
     * @param fromCol Starting column index.
     * @param toCol   Ending column index.
     * @param key     The key to search for.
     * @return        true if key is found; false otherwise.
     */
    public static boolean search(int[][] mat, int fromRow, int toRow, int fromCol, int toCol, int key) {
        // If submatrix boundaries are invalid, key is not present.
        if (fromRow > toRow || fromCol > toCol)
            return false;
        
        int midRow = (fromRow + toRow) / 2;
        int midCol = (fromCol + toCol) / 2;
        int midValue = mat[midRow][midCol];
        
        if (midValue == key)
            return true;
        
        // If the middle element is greater than the key, then key cannot be in
        // the bottom-right quadrant. Search in:
        // (i) the upper submatrix and (ii) the left submatrix (including part of the middle row).
        if (midValue > key) {
            return search(mat, fromRow, midRow - 1, fromCol, toCol, key) ||
                   search(mat, midRow, toRow, fromCol, midCol - 1, key);
        } 
        // Else, the middle element is less than the key, so key cannot be in
        // the upper-left quadrant. Search in:
        // (i) the right submatrix (including part of the middle row) and (ii) the bottom submatrix.
        else {
            return search(mat, fromRow, midRow, midCol + 1, toCol, key) ||
                   search(mat, midRow + 1, toRow, fromCol, toCol, key);
        }
    }
}
