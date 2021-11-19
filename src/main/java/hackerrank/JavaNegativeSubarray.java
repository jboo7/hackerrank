package hackerrank;

import java.util.Scanner;

public class JavaNegativeSubarray {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            System.out.println(solve(arr));
        }
    }

    static int solve(int[] arr) {
        final int N = arr != null ? arr.length : 0;
        int count = 0;
        for (int s = 0; s < N; s++) {
            for (int i = 0; i + s < N; i++) {
                int sum = 0;
                for (int j = 0; j < s + 1; j++) {
                    sum += arr[i + j];
                }
                if (sum < 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
