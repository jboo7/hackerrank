package hackerrank;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class JourneyToTheMoon {
    public static void main(String[] args) {
        List<Integer> list = IntStream.range(0, 100_000)
                                      .mapToObj(i -> 1)
                                      .collect(Collectors.toList());
        long start = System.currentTimeMillis();
        LOG.info("{}", mul_and_sum(list));
        long end = System.currentTimeMillis();
        LOG.info("{}", end - start);

        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            int p = sc.nextInt();
            List<List<Integer>> as = new ArrayList<>();
            for (int i = 0; i < p; i++) {
                int l = sc.nextInt();
                int r = sc.nextInt();
                as.add(List.of(l, r));
            }
            LOG.info("{}", journeyToMoon(n, as));
        }
    }

    public static int journeyToMoon(int n, List<List<Integer>> astronaut) {
        // Write your code here
        return (int) disjoint_sets_impl(n, astronaut);
    }

    static long disjoint_sets_impl(int n, List<List<Integer>> astronaut) {
        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            make_set(parent, size, i);
        }
        for (List<Integer> a : astronaut) {
            int l = a.get(0);
            int r = a.get(1);
            union_sets(parent, size, l, r);
        }
        Map<Integer, Integer> sizes = new HashMap<>();
        Set<Integer> u = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (u.add(i)) {
                int p = find_set2(parent, i, u);
                sizes.put(p, size[p]);
            }
        }
        return mul_and_sum(List.copyOf(sizes.values()));
    }

    static void make_set(int[] parent, int[] size, int v) {
        parent[v] = v;
        size[v] = 1;
    }

    static int find_set(int[] parent, int v) {
        if (v == parent[v]) {
            return v;
        }
        return parent[v] = find_set(parent, parent[v]);
    }

    static int find_set2(int[] parent, int v, Set<Integer> u) {
        u.add(v);
        if (v == parent[v]) {
            return v;
        }
        return parent[v] = find_set(parent, parent[v]);
    }

    static void union_sets(int[] parent, int[] size, int a, int b) {
        a = find_set(parent, a);
        b = find_set(parent, b);
        if (a != b) {
            if (size[a] < size[b]) {
                int t = a;
                a = b;
                b = t;
            }
            parent[b] = a;
            size[a] += size[b];
        }
    }

    static long graph_impl(int n, List<List<Integer>> astronaut) {
        Set<Integer> nodes = IntStream.range(0, n)
                                      .boxed()
                                      .collect(Collectors.toSet());
        Map<Integer, Set<Integer>> g = new HashMap<>();
        Set<Integer> vs = new HashSet<>();
        for (List<Integer> a : astronaut) {
            int l = a.get(0);
            int r = a.get(1);
            vs.add(l);
            vs.add(r);
            edge(g, l, r);
            edge(g, r, l);
        }
        nodes.removeAll(vs);
        List<Integer> subGraphSizes = new ArrayList<>(sub_graph_sizes(g, vs));
        nodes.forEach(v -> subGraphSizes.add(1));
        return mul_and_sum(subGraphSizes);
    }

    static <V> void edge(Map<V, Set<V>> g, V v, V to) {
        g.computeIfAbsent(v, k -> new HashSet<>())
         .add(to);
    }

    static <V> Set<V> bfs(Map<V, Set<V>> g, V start) {
        Set<V> u = new HashSet<>();
        Queue<V> q = new ArrayDeque<>();
        u.add(start);
        q.add(start);
        while (!q.isEmpty()) {
            V v = q.poll();
            for (V to : g.getOrDefault(v, Set.of())) {
                if (u.add(to)) {
                    q.add(to);
                }
            }
        }
        return u;
    }

    static <V> List<Integer> sub_graph_sizes(Map<V, Set<V>> g, Set<V> vs) {
        Set<V> visited = new HashSet<>(vs);
        List<Integer> subGraphSizes = new ArrayList<>();
        while (!visited.isEmpty()) {
            V v = visited.iterator()
                         .next();
            Set<V> u = bfs(g, v);
            subGraphSizes.add(u.size());
            visited.removeAll(u);
        }
        return subGraphSizes;
    }

    static long mul_and_sum(List<Integer> countrySizes) {
        long sum = 0;
        long result = 0;
        for (long size : countrySizes) {
            result += sum * size;
            sum += size;
        }
        return result;
    }
}
