package week_6;

class GFG {
    static final int MAX_CHAR = 256;

    // Returns the next character index with max frequency and dist <= 0
    static int nextChar(int[] freq, int[] dist) {
        int max = Integer.MIN_VALUE;
        int idx = -1;
        for (int i = 0; i < MAX_CHAR; i++) {
            if (freq[i] > 0 && dist[i] <= 0 && freq[i] > max) {
                max = freq[i];
                idx = i;
            }
        }
        return idx;
    }

    // Rearranges input string `str` into `out` array such that same characters are d apart
    static int rearrange(char[] str, char[] out, int d) {
        int n = str.length;
        int[] freq = new int[MAX_CHAR];
        int[] dist = new int[MAX_CHAR];

        // Step 1: Count frequencies
        for (int i = 0; i < n; i++)
            freq[str[i]]++;

        for (int i = 0; i < n; i++) {
            int j = nextChar(freq, dist);
            if (j == -1)
                return 0; // Not possible

            // Assign character
            out[i] = (char) j;
            freq[j]--;
            dist[j] = d;

            // Decrease distance counters
            for (int k = 0; k < MAX_CHAR; k++) {
                if (dist[k] > 0)
                    dist[k]--;
            }
        }
        return 1;
    }

    // Driver code
    public static void main(String[] args) {
        char str[] = "aaaabbbcc".toCharArray();
        int n = str.length;
        char[] out = new char[n];

        if (rearrange(str, out, 2) == 1)
            System.out.println(String.valueOf(out));
        else
            System.out.println("Cannot be rearranged");
    }
}

