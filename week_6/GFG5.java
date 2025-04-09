package week_6;

class GFG5 {
    // Number of persons
    static final int N = 3;

    // Get index of person with maximum credit
    static int getMax(int amount[]) {
        int maxInd = 0;
        for (int i = 1; i < N; i++)
            if (amount[i] > amount[maxInd])
                maxInd = i;
        return maxInd;
    }

    // Get index of person with maximum debt
    static int getMin(int amount[]) {
        int minInd = 0;
        for (int i = 1; i < N; i++)
            if (amount[i] < amount[minInd])
                minInd = i;
        return minInd;
    }

    // Return minimum of 2 values
    static int minOf2(int x, int y) {
        return (x < y) ? x : y;
    }

    // Recursive function to settle debts
    static void minCashFlowRec(int amount[]) {
        int maxCred = getMax(amount), maxDebt = getMin(amount);

        // If both are 0, all amounts are settled
        if (amount[maxCred] == 0 && amount[maxDebt] == 0)
            return;

        // Find minimum of max credit and max debit
        int min = minOf2(-amount[maxDebt], amount[maxCred]);
        amount[maxCred] -= min;
        amount[maxDebt] += min;

        System.out.println("Person " + maxDebt + " pays " + min + " to Person " + maxCred);

        // Recur for remaining unsettled amounts
        minCashFlowRec(amount);
    }

    // Function to calculate and print minimum cash flow
    static void minCashFlow(int graph[][]) {
        int[] amount = new int[N];

        // Calculate net amount for every person
        for (int p = 0; p < N; p++)
            for (int i = 0; i < N; i++)
                amount[p] += (graph[i][p] - graph[p][i]);

        minCashFlowRec(amount);
    }

    // Driver code
    public static void main(String[] args) {
        int graph[][] = {
            {0, 1000, 2000},
            {0, 0, 5000},
            {0, 0, 0}
        };
        minCashFlow(graph);
    }
}

