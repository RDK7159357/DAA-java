package section1;

import java.util.*;

public class Kruskal {
    static class Edge implements Comparable<Edge> {
        int u, v, w;
        Edge(int a, int b, int c) { u = a; v = b; w = c; }
        public int compareTo(Edge e) { return w - e.w; }
    }

    static int find(int[] parent, int i) {
        return parent[i] == i ? i : (parent[i] = find(parent, parent[i]));
    }

    static int kruskal(int n, List<Edge> edges) {
        Collections.sort(edges);
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        int cost = 0, count = 0;

        for (Edge e : edges) {
            int a = find(parent, e.u), b = find(parent, e.v);
            if (a != b) {
                cost += e.w;
                parent[a] = b;
                if (++count == n - 1) break;
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
            new Edge(0, 1, 10), new Edge(0, 2, 6), new Edge(0, 3, 5),
            new Edge(1, 3, 15), new Edge(2, 3, 4)
        );
        System.out.println("MST Cost: " + kruskal(4, edges));
    }
}
