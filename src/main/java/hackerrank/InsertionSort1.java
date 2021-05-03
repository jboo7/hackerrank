package hackerrank;

import java.util.Arrays;

public class InsertionSort1 {
    public static void main(String[] args) {
        insertionSort1(10, new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 1});
    }

    static void insertionSort1(int n, int[] arr) {
        int e = arr[n - 1], i = n - 2;
        for (; i >= 0; --i) {
            if (arr[i] > e) {
                arr[i + 1] = arr[i];
                System.out.println(Arrays.toString(arr).replaceAll("[\\[\\],]", ""));
            } else {
                break;
            }
        }
        arr[i + 1] = e;
        System.out.println(Arrays.toString(arr).replaceAll("[\\[\\],]", ""));
    }
}
