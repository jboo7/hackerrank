package hackerrank;

import java.util.Scanner;

public class MergingCommunities {
    private static int[] parent, size;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int N = sc.nextInt()+1;
            int Q = sc.nextInt();

            parent = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            int x, y;
            for (int i = 0; i < Q; i++) {
                String q = sc.next();
                if ("M".equalsIgnoreCase(q)) {
                    x = sc.nextInt();
                    y = sc.nextInt();
                    union_sets(x, y);
                } else if ("Q".equalsIgnoreCase(q)) {
                    x = sc.nextInt();
                    System.out.println(size[find_set(x)]);
                }
            }
        }
    }

    private static int find_set(int v) {
        if (v == parent[v]) {
            return v;
        }
        return parent[v] = find_set(parent[v]);
    }

    private static void union_sets(int a, int b) {
        a = find_set(a);
        b = find_set(b);
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
}
