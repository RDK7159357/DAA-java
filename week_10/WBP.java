package week_10;

import java.util.*;

class WBP {
    // Prints all possible word breaks of given string s using words in dict
    static void wordBreak(int n, List<String> dict, String s) {
        wordBreakUtil(s, dict, "");
    }

    static void wordBreakUtil(String s, List<String> dict, String ans) {
        int n = s.length();
        if (n == 0) {
            // We've segmented the entire string; print result without trailing space
            System.out.println(ans.trim());
            return;
        }
        // Try every prefix of s
        for (int i = 1; i <= n; i++) {
            String prefix = s.substring(0, i);
            // If prefix is a valid word
            if (dict.contains(prefix)) {
                // Recurse on the remainder
                wordBreakUtil(s.substring(i), dict, ans + prefix + " ");
            }
        }
    }

    public static void main(String[] args) {
        List<String> dict = Arrays.asList(
            "i", "like", "sam", "sung", "samsung",
            "mobile", "ice", "cream", "icecream",
            "and", "man", "go", "mango"
        );

        String str1 = "ilikesamsungmobile";
        System.out.println("First Test:");
        wordBreak(str1.length(), dict, str1);

        String str2 = "ilikeicecreamandmango";
        System.out.println("\nSecond Test:");
        wordBreak(str2.length(), dict, str2);
    }
}

