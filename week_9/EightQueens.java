package week_9;
public class EightQueens {
    static int N = 8;
    
    public static void main(String[] args) {
        int[][] b = new int[N][N];
        if (!solve(0, b)) System.out.println("No solution");
    }
    
    static boolean solve(int c, int[][] b) {
        if (c == N) {
            print(b);
            return true;
        }
        for (int r = 0; r < N; r++) {
            if (safe(b, r, c)) {
                b[r][c] = 1;
                if (solve(c + 1, b)) return true;
                b[r][c] = 0;
            }
        }
        return false;
    }
    
    static boolean safe(int[][] b, int r, int c) {
        for (int i = 0; i < c; i++) if (b[r][i] == 1) return false;
        for (int i = r, j = c; i >= 0 && j >= 0; i--, j--) if (b[i][j] == 1) return false;
        for (int i = r, j = c; i < N && j >= 0; i++, j--) if (b[i][j] == 1) return false;
        return true;
    }
    
    static void print(int[][] b) {
        for (int[] row : b) {
            for (int v : row) System.out.print(v == 1 ? "Q " : ". ");
            System.out.println();
        }
    }
}