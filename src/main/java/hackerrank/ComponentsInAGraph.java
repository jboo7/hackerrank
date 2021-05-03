package hackerrank;

import java.util.*;

public class ComponentsInAGraph {
    public static List<Integer> componentsInGraph(List<List<Integer>> gb) {
        // Write your code here
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (List<Integer> e : gb) {
            Integer from = e.get(0), to = e.get(1);

            addEdge(g, from, to);
            addEdge(g, to, from);
        }
        return countComponents(g);
    }

    private static <V> void addEdge(Map<V, Set<V>> g, V from, V to) {
        g.computeIfAbsent(from, k -> new HashSet<>())
         .add(to);
    }

    private static <V> List<Integer> countComponents(Map<V, Set<V>> g) {
        Set<V> nodes = new HashSet<>(g.keySet());
        Set<V> visited = new HashSet<>();
        Set<V> u = new HashSet<>();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (V node : nodes) {
            if (visited.contains(node)) {
                continue;
            }

            u.clear();
            bfs(g, node, u);
            if (u.size() > 1) {
                min = Math.min(u.size(), min);
                max = Math.max(u.size(), max);
            }
            visited.addAll(u);
        }
        return Arrays.asList(min, max);
    }

    private static <V> void bfs(Map<V, Set<V>> g, V start, Set<V> u) {
        Queue<V> q = new ArrayDeque<>();
        q.add(start);
        u.add(start);

        while (!q.isEmpty()) {
            V v = q.poll();

            for (V to : g.getOrDefault(v, Collections.emptySet())) {
                if (u.add(to)) {
                    q.add(to);
                }
            }
        }
    }
}
