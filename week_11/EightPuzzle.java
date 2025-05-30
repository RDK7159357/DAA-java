package week_11;

import java.util.*;

public class EightPuzzle {
    static final int N = 3, S = N*N;
    static final int[] GOAL = {1,2,3,4,5,6,7,8,0};

    static class Node {
        int[] s; int zi, g;
        Node(int[] s, int zi, int g) { this.s = s; this.zi = zi; this.g = g; }
    }

    // heuristic: number of misplaced tiles
    static int h(int[] s) {
        int c = 0;
        for (int i = 0; i < S; i++)
            if (s[i] != 0 && s[i] != GOAL[i]) c++;
        return c;
    }

    static void solve(int[] start) {
        int zi = 0; while (start[zi] != 0) zi++;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.g + h(n.s)));
        Set<String> seen = new HashSet<>();
        pq.add(new Node(start, zi, 0));
        int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};

        while (!pq.isEmpty()) {
            Node u = pq.poll();
            if (h(u.s) == 0) { print(u.s); return; }
            String key = Arrays.toString(u.s);
            if (!seen.add(key)) continue;
            int x = u.zi / N, y = u.zi % N;
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k], ny = y + dy[k];
                if (nx<0||nx>=N||ny<0||ny>=N) continue;
                int nz = nx*N + ny;
                int[] ns = u.s.clone();
                ns[u.zi] = ns[nz]; ns[nz] = 0;
                if (seen.contains(Arrays.toString(ns))) continue;
                pq.add(new Node(ns, nz, u.g+1));
            }
        }
        System.out.println("No solution");
    }

    static void print(int[] s) {
        for (int i = 0; i < S; i++) {
            System.out.print((s[i]==0 ? ". " : s[i]+" "));
            if (i%N==N-1) System.out.println();
        }
    }

    public static void main(String[] args) {
        solve(new int[]{1,2,3,4,0,6,7,5,8});
    }
}
