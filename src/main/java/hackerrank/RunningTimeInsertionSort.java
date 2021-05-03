package hackerrank;

import java.util.Arrays;

public class RunningTimeInsertionSort {
    static int runningTime(int[] arr) {
        int shifts = 0;
        for (int i = 1, j; i < arr.length; i++) {
            int e = arr[i];
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > e) {
                    arr[j + 1] = arr[j];
                    shifts++;
                } else {
                    break;
                }
            }
            arr[j + 1] = e;
        }
        return shifts;
    }
}
