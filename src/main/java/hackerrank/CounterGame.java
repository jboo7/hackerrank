package hackerrank;

import java.util.Arrays;

public class CounterGame {
    static final int N = 64;
    static final long[] pows2 = new long[N];

    static {
        long a = 1;
        for (int i = 0; i < N; i++, a <<= 1) {
            pows2[i] = a;
        }
    }

    public static void main(String[] args) {
        System.out.println(counterGame(1));
        System.out.println(counterGame(2));
        System.out.println(counterGame(3));
        System.out.println(counterGame(4));
        System.out.println(counterGame(5));
        System.out.println(counterGame(6));
        System.out.println(counterGame(7));
        System.out.println(counterGame(8));
        System.out.println(counterGame(9));
        System.out.println(counterGame(10));
    }

    public static String counterGame(long n) {
        // Write your code here
        int i = 0;
        while (n > 1) {
            int p = Arrays.binarySearch(pows2, n);
            if (p >= 0) {
                i += p;
                n -= pows2[p];
            } else {
                n -= pows2[-(p + 2)];
                i++;
            }
        }
        return (i % 2) == 1 ? "Louise" : "Richard";
    }
}
