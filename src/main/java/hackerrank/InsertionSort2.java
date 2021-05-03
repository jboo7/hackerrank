package hackerrank;

import java.util.Arrays;

public class InsertionSort2 {
    public static void main(String[] args) {
        insertionSort2(7, new int[]{3, 4, 7, 5, 6, 2, 1});
    }

    static void insertionSort2(int n, int[] arr) {
        for (int i = 1, j; i < n; i++) {
            int e = arr[i];
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > e) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = e;
            System.out.println(Arrays.toString(arr).replaceAll("[\\[\\],]", ""));
        }

    }
}
