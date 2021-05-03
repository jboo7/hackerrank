package hackerrank;

public class CountingSort2 {
    static int[] countingSort(int[] arr) {
        final int N = arr != null ? arr.length : 0;
        final int F = 100;
        int[] counts = new int[F];
        for (int i = 0; i < N; i++) {
            counts[arr[i]]++;
        }
        for (int i = 0, j = 0; i < F; i++) {
            while (counts[i]-- > 0) {
                arr[j++] = i;
            }
        }
        return arr;
    }
}
