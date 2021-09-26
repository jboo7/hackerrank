package hackerrank;

import java.util.*;

public class BreadthFirstSearchShortestReach {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int q = sc.nextInt();
            while (q-- > 0) {
                int n = sc.nextInt();
                int m = sc.nextInt();

                List<List<Integer>> edges = new ArrayList<>();
                for (int i = 0; i < m; i++) {
                    int v = sc.nextInt();
                    int to = sc.nextInt();

                    edges.add(List.of(v, to));
                }
                int s = sc.nextInt();

                System.out.println(bfs(n, m, edges, s));
            }
        }
    }


    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
        // Write your code here
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (List<Integer> edge : edges) {
            addEdge(g, edge.get(0), edge.get(1));
            addEdge(g, edge.get(1), edge.get(0));
        }
        Map<Integer, Integer> ds = bfs(g, s);

        List<Integer> a = new ArrayList<>();
        for (int v = 1; v <= n; v++) {
            if (v == s) {
                continue;
            }

            Integer d = ds.get(v);
            if (d == null) {
                a.add(-1);
            } else {
                a.add(d * 6);
            }
        }
        return a;
    }

    private static <V> void addEdge(Map<V, Set<V>> g, V v, V to) {
        g.computeIfAbsent(v, k -> new HashSet<>())
         .add(to);
    }

    private static <V> Map<V, Integer> bfs(Map<V, Set<V>> g, V start) {
        Queue<V> q = new ArrayDeque<>();
        Set<V> u = new HashSet<>();
        Map<V, Integer> d = new HashMap<>();

        q.add(start);
        u.add(start);
        d.put(start, 0);

        while (!q.isEmpty()) {
            V v = q.poll();

            for (V to : g.getOrDefault(v, Set.of())) {
                if (u.add(to)) {
                    q.add(to);
                    d.put(to, Math.min(d.get(v) + 1, d.getOrDefault(to, Integer.MAX_VALUE)));
                }
            }
        }

        return d;
    }
}
