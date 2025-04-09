package week_5;

import java.util.*;

class Jobseq {
    // Each job has a unique-id, deadline and profit.
    char id;
    int deadline, profit;

    // Constructors
    public Jobseq() { }

    public Jobseq(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }

    // Function to schedule the jobs given an array list of jobs and the number of jobs to schedule (t).
    void printJobScheduling(ArrayList<Jobseq> arr, int t) {
        // Sort the jobs in descending order of profit.
        Collections.sort(arr, (a, b) -> b.profit - a.profit);

        // result[] stores the sequence (job id) in which jobs are scheduled.
        // slot[] keeps track of time slots filled. Initialize all slots as free.
        char[] result = new char[t];
        boolean[] slot = new boolean[t];
        
        // Variable to store the total profit.
        int maxProfit = 0;

        // Iterate through all given jobs.
        for (Jobseq job : arr) {
            // Find a free slot for this job, starting from the last possible slot.
            // Math.min(t, job.deadline) ensures that we don't consider slots beyond the number available.
            for (int j = Math.min(t, job.deadline) - 1; j >= 0; j--) {
                // If the slot is free, assign this job to that slot.
                if (!slot[j]) {
                    slot[j] = true;
                    result[j] = job.id;
                    maxProfit += job.profit;
                    break; // Once the job is scheduled, move to the next job.
                }
            }
        }

        // Print the sequence of jobs scheduled.
        System.out.print("The job sequence is: ");
        for (char id : result) {
            System.out.print(id + " ");
        }
        // Print the calculated maximum profit.
        System.out.println("\nMaximum Profit = " + maxProfit);
    }

    // Driver's code
    public static void main(String args[]) {
        ArrayList<Jobseq> arr = new ArrayList<>();
        arr.add(new Jobseq('a', 2, 100));
        arr.add(new Jobseq('b', 1, 19));
        arr.add(new Jobseq('c', 2, 27));
        arr.add(new Jobseq('d', 1, 25));
        arr.add(new Jobseq('e', 3, 15));

        System.out.println("Following is maximum profit sequence of jobs");
        Jobseq job = new Jobseq();
        // Function call to schedule jobs. Here, '3' indicates we have three time slots available.
        job.printJobScheduling(arr, 3);
    }
}
