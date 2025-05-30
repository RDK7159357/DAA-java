package week_11;

// Branch and bound
public class Nqueens {
    static int N;
    static int[] pos;
    static boolean[] cols, diag1, diag2;

    public static void main(String[] args) {
        N = 8;                         // board size
        pos   = new int[N];            // pos[r] = column of queen in row r
        cols  = new boolean[N];        // columns occupied
        diag1 = new boolean[2 * N];    // ↙ diagonals (r + c)
        diag2 = new boolean[2 * N];    // ↖ diagonals (r – c + N)
        place(0);
    }

    static void place(int r) {
        if (r == N) {                  // all queens placed
            printSolution();
            return;
        }
        for (int c = 0; c < N; c++) {
            int d1 = r + c, d2 = r - c + N;
            if (!cols[c] && !diag1[d1] && !diag2[d2]) {
                pos[r] = c;
                cols[c] = diag1[d1] = diag2[d2] = true;
                place(r + 1);
                cols[c] = diag1[d1] = diag2[d2] = false;
            }
        }
    }

    static void printSolution() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                System.out.print(pos[r] == c ? "Q " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
