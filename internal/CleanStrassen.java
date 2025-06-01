package internal;


import java.util.Scanner;

public class CleanStrassen {
    
    public int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        
        // Base case - use simple multiplication for small matrices
        if (n <= 2) {
            return simpleMultiply(A, B);
        }
        
        int half = n / 2;
        
        // Split matrices into quadrants
        int[][] A11 = getQuadrant(A, 0, 0, half);
        int[][] A12 = getQuadrant(A, 0, half, half);
        int[][] A21 = getQuadrant(A, half, 0, half);
        int[][] A22 = getQuadrant(A, half, half, half);
        
        int[][] B11 = getQuadrant(B, 0, 0, half);
        int[][] B12 = getQuadrant(B, 0, half, half);
        int[][] B21 = getQuadrant(B, half, 0, half);
        int[][] B22 = getQuadrant(B, half, half, half);
        
        // Calculate Strassen's 7 products
        int[][] M1 = multiply(add(A11, A22), add(B11, B22));
        int[][] M2 = multiply(add(A21, A22), B11);
        int[][] M3 = multiply(A11, subtract(B12, B22));
        int[][] M4 = multiply(A22, subtract(B21, B11));
        int[][] M5 = multiply(add(A11, A12), B22);
        int[][] M6 = multiply(subtract(A21, A11), add(B11, B12));
        int[][] M7 = multiply(subtract(A12, A22), add(B21, B22));
        
        // Calculate result quadrants
        int[][] C11 = add(subtract(add(M1, M4), M5), M7);
        int[][] C12 = add(M3, M5);
        int[][] C21 = add(M2, M4);
        int[][] C22 = add(subtract(add(M1, M3), M2), M6);
        
        // Combine quadrants
        return combineQuadrants(C11, C12, C21, C22);
    }
    
    private int[][] simpleMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }
    
    private int[][] getQuadrant(int[][] matrix, int startRow, int startCol, int size) {
        int[][] quad = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                quad[i][j] = matrix[startRow + i][startCol + j];
            }
        }
        return quad;
    }
    
    private int[][] combineQuadrants(int[][] C11, int[][] C12, int[][] C21, int[][] C22) {
        int half = C11.length;
        int n = half * 2;
        int[][] result = new int[n][n];
        
        for (int i = 0; i < half; i++) {
            for (int j = 0; j < half; j++) {
                result[i][j] = C11[i][j];
                result[i][j + half] = C12[i][j];
                result[i + half][j] = C21[i][j];
                result[i + half][j + half] = C22[i][j];
            }
        }
        return result;
    }
    
    private int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }
    
    private int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the dimension n for the n x n matrices (n must be a power of 2):");
        int n = sc.nextInt();
        
        int[][] A = new int[n][n];
        int[][] B = new int[n][n];
        
        System.out.println("Enter elements for Matrix A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = sc.nextInt();
            }
        }
        
        System.out.println("Enter elements for Matrix B:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                B[i][j] = sc.nextInt();
            }
        }
        
        CleanStrassen strassen = new CleanStrassen();
        int[][] result = strassen.multiply(A, B);
        
        System.out.println("Resultant Matrix:");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}

// A = [A11  A12]    B = [B11  B12]
//     [A21  A22]        [B21  B22]

// M1: "Diagonal plus diagonal, that's the way"
// M2: "Left column sum times B-one-one"
// M3: "A-one-one times right column's difference"
// M4: "A-two-two times left column's difference" 
// M5: "Top row sum times B-two-two"
// M6: "Left difference times top row sum"
// M7: "Right difference times bottom row sum"