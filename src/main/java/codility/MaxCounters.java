package codility;

public class MaxCounters {
    public static void main(String[] args) {
        MaxCounters maxCounters = new MaxCounters();
        maxCounters.solution(5, new int[]{3, 4, 4, 6, 1, 4, 4});
    }

    private int[] solution(int N, int[] A) {
        final int[] t = new int[N * 4];
        int lazy = 0;

        for (int a : A) {
            if (a == N + 1) {
                lazy = t[1];
            } else {
                update(t, lazy, 1, 0, N - 1, a - 1, 1);
            }
        }

        final int[] counters = new int[N];
        for (int i = 0; i < N; i++) {
            update(t, lazy, 1, 0, N - 1, i, 0);
            counters[i] = get(t, 1, 0, N - 1, i);
        }
        return counters;
    }

    private void update(int[] t, int lazy, int v, int tl, int tr, int pos, int add) {
        if (tl == tr) {
            if (t[v] <= lazy) {
                t[v] = lazy + add;
            } else {
                t[v] += add;
            }
        } else {
            int tm = (tl + tr) / 2;
            if (pos <= tm) {
                update(t, lazy, v * 2, tl, tm, pos, add);
            } else {
                update(t, lazy, v * 2 + 1, tm + 1, tr, pos, add);
            }
            t[v] = Math.max(t[v * 2], t[v * 2 + 1]);
        }
    }

    private int get(int[] t, int v, int tl, int tr, int pos) {
        if (tl == tr) {
            return t[v];
        } else {
            int tm = (tl + tr) / 2;
            if (pos <= tm) {
                return get(t, v * 2, tl, tm, pos);
            } else {
                return get(t, v * 2 + 1, tm + 1, tr, pos);
            }
        }
    }
}
