package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaxMin {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int N = sc.nextInt();
            int k = sc.nextInt();
            List<Integer> arr = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                arr.add(sc.nextInt());
            }

            System.out.println(maxMin(k, arr));
        }
    }

    public static int maxMin(int k, List<Integer> arr) {
        // Write your code here
        int[] A = arr.stream().sorted()
                .mapToInt(i -> i)
                .toArray();

        int min = Integer.MAX_VALUE;
        for (int i = 0, j = k - 1; j < A.length; i++, j++) {
            min = Math.min(min, A[j] - A[i]);
        }
        return min;
    }
}
