package week_6;

import java.util.*;

public class GFG1 {

    // Returns minimum time required to place all mice in the holes.
    public int assignHole(ArrayList<Integer> mice, ArrayList<Integer> holes) {
        if (mice.size() != holes.size())
            return -1; // Return -1 if the number of mice and holes differs.
        
        // Sort both the mice and holes arrays in ascending order.
        Collections.sort(mice);
        Collections.sort(holes);

        // Finding the maximum absolute difference between the ith mouse and ith hole.
        int result = 0;
        for (int i = 0; i < mice.size(); i++) {
            int diff = Math.abs(mice.get(i) - holes.get(i));
            result = Math.max(result, diff);
        }
        
        return result;
    }

    // Driver Function to test the assignHole function.
    public static void main(String[] args) {
        GFG1 gfg = new GFG1();
        
        ArrayList<Integer> mice = new ArrayList<>();
        mice.add(4);
        mice.add(-4);
        mice.add(2);
        
        ArrayList<Integer> holes = new ArrayList<>();
        holes.add(4);
        holes.add(0);
        holes.add(5);
        
        System.out.println("The last mouse gets into the hole in time: " + gfg.assignHole(mice, holes));
    }
}

