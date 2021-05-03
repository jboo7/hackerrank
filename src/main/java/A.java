import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A {
    public static void main(String[] args) {
        System.out.println(rotateLeft(4, List.of(1, 2, 3, 4, 5)));
    }

    /*
     * Complete the 'rotateLeft' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER d
     *  2. INTEGER_ARRAY arr
     */

    public static List<Integer> rotateLeft(int d, List<Integer> arr) {
        // Write your code here
        List<Integer> r = new ArrayList<>();
        for (int i = 0, N = arr.size(); i < N; ++i) {
            int j = (N + (i + d)) % N;
            r.add(arr.get(j));
        }
        return r;
    }

    /*
     * Complete the 'dynamicArray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */

    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
        // Write your code here
        List<Integer> r = new ArrayList<>();
        int[][] a = new int[n][];
        for (int i = 0; i < n; ++i) {
            a[i] = new int[0];
        }
        int lastAnswer = 0;
        for (List<Integer> q : queries) {
            int t = q.get(0);
            int x = q.get(1);
            int y = q.get(2);

            int idx = ((x ^ lastAnswer) % n);
            int[] b = a[idx];

            if (t == 1) {
                b = Arrays.copyOf(b, b.length + 1);
                b[b.length - 1] = y;
            } else {
                r.add(lastAnswer = b[y % b.length]);
            }

            a[idx] = b;
        }
        return r;
    }
}
