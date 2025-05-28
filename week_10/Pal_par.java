package week_10;

import java.util.ArrayList;
import java.util.List;

class Pal_par {
    List<List<String>> ans = new ArrayList<>();

    // Check each substring in currPartition is a palindrome
    boolean checkPalindrome(List<String> currPartition) {
        for (String part : currPartition) {
            int i = 0, j = part.length() - 1;
            while (i < j) {
                if (part.charAt(i++) != part.charAt(j--)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Given a bitString of length n-1, build the partition of s
    // '1' at bitString[p] means cut after s.charAt(p)
    void generatePartition(String s, String bitString) {
        List<String> partition = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        for (int p = 0; p < bitString.length(); p++) {
            if (bitString.charAt(p) == '1') {
                // cut here
                partition.add(sb.toString());
                sb = new StringBuilder();
            }
            // always append the next character
            sb.append(s.charAt(p + 1));
        }
        partition.add(sb.toString());
        if (checkPalindrome(partition)) {
            ans.add(partition);
        }
    }

    // Recursively generate all 2^(n-1) bitStrings of length n-1
    // We try '1' first so that partitions with more cuts (smaller pieces) come out first
    void bitManipulation(String s, String bitString) {
        int n = s.length();
        if (bitString.length() == n - 1) {
            generatePartition(s, bitString);
            return;
        }
        // '1' means cut after this position
        bitManipulation(s, bitString + '1');
        // '0' means no cut here
        bitManipulation(s, bitString + '0');
    }

    public static void main(String[] args) {
        String s = "nitin";
        Pal_par ob = new Pal_par();
        ob.bitManipulation(s, "");
        // Print all palindromic partitions
        for (List<String> part : ob.ans) {
            for (String piece : part) {
                System.out.print(piece + " ");
            }
            System.out.println();
        }
    }
}
