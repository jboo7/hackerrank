package hackerrank;

import java.util.Arrays;

public class CircularArrayRotation {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(circularArrayRotation(new int[]{1, 2, 3}, 2, new int[]{0, 1, 2})));
    }

    // Complete the circularArrayRotation function below.
    static int[] circularArrayRotation(int[] a, int k, int[] queries) {
        int[] arr = Arrays.copyOf(a, a.length * 2);
        System.arraycopy(a, 0, arr, a.length, a.length);
        int[] answers = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            answers[i] = arr[queries[i] + (a.length + (a.length - k) % a.length) % a.length];
        }
        return answers;
    }
}
