package hackerrank;

public class Quicksort1 {
    static int[] quickSort(int[] arr) {
        final int N = arr != null ? arr.length : 0;
        if (N == 0) {
            return arr;
        }
        int l = 0, e = 0, r = 0, i;
        int[] left = new int[N];
        int[] equal = new int[N];
        int[] right = new int[N];
        int pivot = arr[0];
        for (i = 0; i < N; i++) {
            int a = arr[i];
            if (a < pivot) {
                left[l++] = a;
            } else if (a > pivot) {
                right[r++] = a;
            } else {
                equal[e++] = a;
            }
        }
        i = 0;
        while (--l >= 0) {
            arr[i++] = left[l];
        }
        while (--e >= 0) {
            arr[i++] = equal[e];
        }
        while (--r >= 0) {
            arr[i++] = right[r];
        }
        return arr;
    }
}
