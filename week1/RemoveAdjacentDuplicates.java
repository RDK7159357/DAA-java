package week1;

public class RemoveAdjacentDuplicates {
    static char lastRemoved;
    
    /**
     * Removes all adjacent duplicates from a string recursively.
     */
    public static String removeAdjDuplicates(String s) {
        lastRemoved = '\0';
        return removeUtil(s);
    }
    
    /**
     * Helper method that implements the recursive algorithm.
     */
    private static String removeUtil(String s) {
        // Base case: return string if empty or single character
        if (s.length() <= 1) {
            return s;
        }
        
        // Remove consecutive same characters
        if (s.charAt(0) == s.charAt(1)) {
            lastRemoved = s.charAt(0);
            
            // Skip all occurrences of the same character
            int i = 1;
            while (i < s.length() && s.charAt(i) == s.charAt(0)) {
                i++;
            }
            
            // Process remaining string
            return removeUtil(s.substring(i));
        }
        
        // Process string from second character
        String remaining = removeUtil(s.substring(1));
        
        // Check if first char matches with first of processed string
        if (!remaining.isEmpty() && remaining.charAt(0) == s.charAt(0)) {
            lastRemoved = s.charAt(0);
            return remaining.substring(1);
        }
        
        // Don't include first char if string becomes empty and 
        // last removed char is same as first char
        if (remaining.isEmpty() && lastRemoved == s.charAt(0)) {
            return remaining;
        }
        
        // Prepend first char to processed string
        return s.charAt(0) + remaining;
    }
    
    public static void main(String[] args) {
        String[] testCases = {"azxxzy", "caaabbbaacdddd", "acaaabbbacdddd"};
        
        for (String test : testCases) {
            String result = removeAdjDuplicates(test);
            System.out.println("Input: " + test);
            System.out.println("Output: " + (result.isEmpty() ? "Empty String" : result));
            System.out.println();
        }
    }
}