package week_10;

// import java.util.*;

public class LCS{
    static int MAX = 100;
    static int[][] dp = new int[MAX + 1][MAX + 1];
    static int lcslen;

    // Build dp table for LCS lengths starting at (i,j)
    static void computeLCS(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        for (int i = n; i >= 0; i--) {
            for (int j = m; j >= 0; j--) {
                if (i == n || j == m) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        lcslen = dp[0][0];
    }

    // Recursively build and print all LCS of length lcslen
    static void printAll(String s1, String s2, char[] buf, int pos,
                         int i, int j) {
        if (pos == lcslen) {
            System.out.println(new String(buf));
            return;
        }
        // Try characters 'a' to 'z' to ensure lex order
        for (char c = 'a'; c <= 'z'; c++) {
            boolean used = false;
            for (int ii = i; ii < s1.length(); ii++) {
                if (s1.charAt(ii) != c) continue;
                for (int jj = j; jj < s2.length(); jj++) {
                    if (s2.charAt(jj) != c) continue;
                    // If choosing c here can still complete an LCS
                    if (dp[ii][jj] == lcslen - pos) {
                        buf[pos] = c;
                        printAll(s1, s2, buf, pos + 1, ii + 1, jj + 1);
                        used = true;
                        break;
                    }
                }
                if (used) break;
            }
        }
    }

    // Driver to compute and print all LCS in sorted order
    static void prinlAllLCSSorted(String s1, String s2) {
        computeLCS(s1, s2);
        if (lcslen == 0) {
            System.out.println("No Common Subsequence");
            return;
        }
        char[] buf = new char[lcslen];
        printAll(s1, s2, buf, 0, 0, 0);
    }

    public static void main(String[] args) {
        String str1 = "abcabcaa", str2 = "acbacba";
        prinlAllLCSSorted(str1, str2);
    }
}

