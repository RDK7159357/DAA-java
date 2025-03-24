package week_3;

import java.util.Scanner;

public class StrassenMul {

    /** Function to multiply matrices using Strassen's algorithm **/
    public int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] R = new int[n][n];
        // Base case
        if (n == 1) {
            R[0][0] = A[0][0] * B[0][0];
            return R;
        }

        int newSize = n / 2;
        int [][] A11 = new int[newSize][newSize];
        int [][] A12 = new int[newSize][newSize];
        int [][] A21 = new int[newSize][newSize];
        int [][] A22 = new int[newSize][newSize];
        split(A, A11, 0, 0);
        split(A, A12, 0, newSize);
        split(A, A21, newSize, 0);
        split(A, A22, newSize, newSize);

        int [][] B11 = new int[newSize][newSize];
        int [][] B12 = new int[newSize][newSize];
        int [][] B21 = new int[newSize][newSize];
        int [][] B22 = new int[newSize][newSize];
        split(B, B11, 0, 0);
        split(B, B12, 0, newSize);
        split(B, B21, newSize, 0);
        split(B, B22, newSize, newSize);

        int [][] M1 = multiply(add(A11, A22), add(B11, B22));
        int [][] M2 = multiply(add(A21, A22), B11);
        int [][] M3 = multiply(A11, sub(B12, B22));
        int [][] M4 = multiply(A22, sub(B21, B11));
        int [][] M5 = multiply(add(A11, A12), B22);
        int [][] M6 = multiply(sub(A21, A11), add(B11, B12));
        int [][] M7 = multiply(sub(A12, A22), add(B21, B22));

        int [][] C11 = add(sub(add(M1, M4), M5), M7);
        int [][] C12 = add(M3, M5);
        int [][] C21 = add(M2, M4);
        int [][] C22 = add(sub(add(M1, M3), M2), M6);

        join(C11, R, 0, 0);
        join(C12, R, 0, newSize);
        join(C21, R, newSize, 0);
        join(C22, R, newSize, newSize);

        return R;
    }

    /** Function to subtract two matrices **/
    public int[][] sub(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    /** Function to add two matrices **/
    public int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    /** Function to split parent matrix into child matrices **/
    public void split(int[][] P, int[][] C, int iB, int jB) {
        int size = C.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                C[i][j] = P[i + iB][j + jB];
            }
        }
    }

    /** Function to join child matrices into parent matrix **/
    public void join(int[][] C, int[][] P, int iB, int jB) {
        int size = C.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                P[i + iB][j + jB] = C[i][j];
            }
        }
    }

    /** Main function with sample inputs **/
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
        
        StrassenMul strassen = new StrassenMul();
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
