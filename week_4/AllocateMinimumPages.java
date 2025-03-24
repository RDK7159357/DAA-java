package week_4;



public class AllocateMinimumPages {

    // Function to check if it is possible to allocate books such that no student reads more than 'maxPages'
    public static boolean isFeasible(int[] pages, int M, int maxPages) {
        int studentCount = 1;
        int currentSum = 0;
        for (int page : pages) {
            // If a single book has more pages than maxPages, allocation is not feasible.
            if (page > maxPages) return false;
            if (currentSum + page > maxPages) {
                studentCount++;  // Allocate to next student.
                currentSum = page;
                if (studentCount > M) return false;
            } else {
                currentSum += page;
            }
        }
        return true;
    }

    // Main function to find the minimum possible maximum number of pages
    public static int allocatePages(int[] pages, int M) {
        int N = pages.length;
        if (M > N) return -1;  // More students than books, allocation not possible.

        // Define the search range: lower bound is max(pages), upper bound is sum(pages)
        int start = 0, end = 0;
        for (int page : pages) {
            start = Math.max(start, page);
            end += page;
        }

        int result = end;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isFeasible(pages, M, mid)) {
                result = mid;   // Mid is a possible solution
                end = mid - 1;  // Try to find a smaller maximum value
            } else {
                start = mid + 1;
            }
        }
        return result;
    }

    // Testing the solution using a sample input.
    public static void main(String[] args) {
        int[] pages = {12, 34, 67, 90};
        int M = 2;  // Number of students
        int minMaxPages = allocatePages(pages, M);
        System.out.println("Minimum number of pages = " + minMaxPages);
    }
}

