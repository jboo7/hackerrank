package microsoft;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class C {
    public static void main(String[] args) {
        C c = new C();
        System.out.println(c.solution(5, new int[]{2, 2, 1, 2}, new int[]{1, 3, 4, 4}));
    }

    public int solution(int N, int[] A, int[] B) {
        // write your code in Java SE 8
        final int M = A.length;
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int i = 0; i < M; i++) {
            addEdge(g, A[i], B[i]);
            addEdge(g, B[i], A[i]);
        }
        List<Integer> nodes = IntStream.rangeClosed(1, N)
                                       .boxed()
                                       .collect(Collectors.toList());

        return 0;
    }

    private static <T> void addEdge(Map<T, Set<T>> g, T from, T to) {
        g.computeIfAbsent(from, k -> new HashSet<>())
         .add(to);
    }

    private static <T> void compute(List<T> nodes, Map<T, Set<T>> g) {
        Map<T, Set<T>> components = new HashMap<>();
        findComponents(nodes, g, components);
    }

    private static <T> void findComponents(List<T> nodes, Map<T, Set<T>> g, Map<T, Set<T>> components) {
        Set<T> visited = new HashSet<>();
        for (T v : nodes) {
            if (visited.contains(v)) {
                continue;
            }

            Set<T> u = new HashSet<>();
            bfs(g, v, u);
            visited.addAll(u);
            components.put(v, u);
        }
    }

    private static <T> void bfs(Map<T, Set<T>> g, T s, Set<T> u) {
        Queue<T> q = new ArrayDeque<>();

        q.add(s);
        u.add(s);

        while (!q.isEmpty()) {
            T v = q.poll();
            for (T to : g.getOrDefault(v, Collections.emptySet())) {
                if (u.add(to)) {
                    q.add(to);
                }
            }
        }
    }
}
