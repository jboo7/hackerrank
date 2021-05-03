package microsoft;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class A {
    public static void main(String[] args) {
        A a = new A();

        int[] arr1 = new int[]{3, 2, 1, 1, 2, 3, 1};
        int[] arr2 = new int[]{4, 1, 4, 1};
        int[] arr3 = new int[]{3, 3, 3};

        int[] arr4 = new int[]{1, 2, 3};
        int[] arr5 = new int[]{1, 1, 1, 2, 3};

        System.out.println(a.solution(arr1));
        System.out.println(a.solution(arr2));
        System.out.println(a.solution(arr3));
        System.out.println(a.solution(arr4));
        System.out.println(a.solution(arr5));
    }

    public int solution(int[] A) {
        Arrays.sort(A);
        final int N = A.length;
        int cost = 0, m = A[N / 2];
        for (int a : A) {
            cost += Math.abs(a - m);
        }
        if (N % 2 == 0) {
            int t = 0;
            m = A[(N / 2) - 1];
            for (int a : A) {
                t += Math.abs(a - m);
            }
            cost = Math.min(cost, t);
        }
        return cost;
    }

    private void sort_by_fr(int[] nums, int[] fr) {
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (fr[i] < fr[j]) {
                    swap(nums, i, j);
                    swap(fr, i, j);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
