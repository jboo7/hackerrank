import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Solution {
    private static boolean debug;

    public static void main(String[] args) {
        Solution.debug = true;
        Solution s = new Solution();
        final int N = 1;
        try (FileInputStream fis = new FileInputStream(Paths.get(String.format("D:/input%d.txt", N))
                                                            .toFile())) {
            long[] expected = Files.readAllLines(Paths.get(String.format("D:/output%d.txt", N)))
                                   .stream()
                                   .mapToLong(Long::parseLong)
                                   .toArray();

            long[] actual = s.compute(fis);

            boolean same = true;
            for (int i = 0; i < expected.length; i++) {
                long a = actual[i], e = expected[i];
                if (a != e) {
                    System.out.printf("%d: %d != %d%n", i, e, a);
                    same = false;
                } else {
                    System.out.printf("%d: %d == %d%n", i, e, a);
                }
            }
            System.out.println(same ? "SAME" : "NOT SAME");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void println(Object o) {
        if (debug) {
            System.out.println(o);
        }
    }

    public long[] compute(InputStream is) {
        try (Scanner sc = new Scanner(is)) {
            return Algorithm.compute(sc);
        }
    }

    /**
     * Graph reading method
     *
     * @param sc scanner for reading
     * @return the graph
     */
    public static int[][] read_graph(Scanner sc) {
        long start, end;

        start = System.currentTimeMillis();
        int a, b;
        final int N = sc.nextInt() + 1;
        int[][] g = new int[N][];
        for (int i = 0; i < N; ++i) {
            g[i] = new int[0];
        }
        for (int i = 2; i < N; i++) {
            a = sc.nextInt();
            b = sc.nextInt();

            add_edge(g, a, b);
            add_edge(g, b, a);
        }
        end = System.currentTimeMillis();
        println(String.format("read graph = %dms", end - start));

        return g;
    }

    /**
     * Method to add edge to the graph
     *
     * @param g    graph
     * @param from edge FROM node
     * @param to   edge TO node
     */
    public static void add_edge(int[][] g, int from, int to) {
        int[] tos = g[from];
        tos = Arrays.copyOf(tos, tos.length + 1);
        g[from] = tos;

        tos[tos.length - 1] = to;
    }

    /**
     * Tree structure for everything
     */
    public static class Tree {
        final int N;
        final int[][] g;

        // LCA part
        /*int timer = 0;
        final int[] time_in, time_out;
        final int[][] up;
        final int L;*/

        // HLD part
        final int[] parent, depth, heavy, head, pos;

        // segment tree part
        final int[] seg_tree;

        public Tree(int N, int[][] g) {
            this.N = N;
            this.g = g;

            // LCA part
            /*time_in = new int[N];
            time_out = new int[N];

            int L = 1;
            while ((1 << L) <= N) ++L;
            this.L = L;

            up = new int[N][];
            for (int i = 0; i < N; i++) {
                up[i] = new int[L + 1];
            }*/

            // HLD part
            parent = new int[N];
            depth = new int[N];
            heavy = new int[N];
            for (int i = 0; i < N; ++i) {
                heavy[i] = -1;
            }
            head = new int[N];
            pos = new int[N];

            // segment tree part
            seg_tree = new int[4 * N];
        }

        /*public int lca(int a, int b) {
            if (upper(a, b)) return a;
            if (upper(b, a)) return b;
            for (int i = L; i >= 0; --i) {
                if (!upper(up[a][i], b)) a = up[a][i];
            }
            return up[a][0];
        }

        private boolean upper(int a, int b) {
            return time_in[a] <= time_in[b] && time_out[a] >= time_out[b];
        }*/

        //void update_seg_tree(int v, )
    }

    public static class Algorithm {
        public static long[] compute(Scanner sc) {
            Tree tree = read_tree(sc);
            preprocess_tree(tree);
            return do_computation(sc, tree);
        }

        private static Tree read_tree(Scanner sc) {
            final int[][] g = read_graph(sc);
            final int N = g.length;
            return new Tree(N, g);
        }

        private static void preprocess_tree(Tree tree) {
            dfs(tree, 1);
            decompose(tree, new AtomicInteger(), 1, 1);
        }

        private static int dfs(Tree tree, int v) {
            int size = 1, max_c_size = 0;
            for (int i = 0, to; i < tree.g[v].length; ++i) {
                to = tree.g[v][i];
                if (to != tree.parent[v]) {
                    tree.parent[to] = v;
                    tree.depth[to] = tree.depth[v] + 1;

                    int c_size = dfs(tree, to);

                    size += c_size;
                    if (c_size > max_c_size) {
                        max_c_size = c_size;
                        tree.heavy[v] = to;
                    }
                }
            }
            return size;
        }

        private static void decompose(Tree tree, AtomicInteger cur_pos, int v, int h) {
            tree.head[v] = h;
            tree.pos[v] = cur_pos.getAndIncrement();
            if (tree.heavy[v] != -1) {
                decompose(tree, cur_pos, tree.heavy[v], h);
            }
            for (int i = 0, to; i < tree.g[v].length; ++i) {
                to = tree.g[v][i];
                if (to != tree.parent[v] && to != tree.heavy[v]) {
                    decompose(tree, cur_pos, to, to);
                }
            }
        }

        private static long[] do_computation(Scanner sc, Tree tree) {
            final int Q = sc.nextInt();
            final long[] r = new long[Q];
            int m = 0;
            for (int i = 0; i < Q; ++i) {
                final String op = sc.next();
                final int a = sc.nextInt();
                final int b = sc.nextInt();

                if ("add".equalsIgnoreCase(op)) {
                    //query_add(tree, a, b);
                } else {
                    r[m++] = query_max(tree, a, b);
                }
            }
            return Arrays.copyOf(r, m);
        }

        private static long query_max(Tree tree, //
                                      int a, int b) {
            long res = 0;
            int t;
            for (; tree.head[a] != tree.head[b]; b = tree.parent[tree.head[b]]) {
                if (tree.depth[tree.head[a]] > tree.depth[tree.head[b]]) {
                    t = a;
                    a = b;
                    b = t;
                }
                long cur_heavy_path_max = segment_tree_query(tree.pos[tree.head[b]], tree.pos[b]);
                res = Math.max(res, cur_heavy_path_max);
            }
            if (tree.depth[a] > tree.depth[b]) {
                t = a;
                a = b;
                b = t;
            }
            long last_heavy_path_max = segment_tree_query(tree.pos[a], tree.pos[b]);
            return Math.max(res, last_heavy_path_max);
        }

        private static long segment_tree_query(int a, int b) {
            return 0;
        }
    }
}