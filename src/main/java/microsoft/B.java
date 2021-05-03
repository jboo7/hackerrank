package microsoft;

public class B {
    public static void main(String[] args) {
        int[] arr1 = new int[]{4, 2, 5, 8, 7, 3, 7};
        int[] arr2 = new int[]{14, 21, 16, 35, 22};
        int[] arr3 = new int[]{5, 5, 5, 5, 5, 5};
        int[] arr4 = new int[]{1, 1, 1, 5, 1, 1};

        B b = new B();
        System.out.println(b.solution(arr1));
        System.out.println(b.solution(arr2));
        System.out.println(b.solution(arr3));
        System.out.println(b.solution(arr4));
    }

    public int solution(int[] A) {
        final int N = A.length;
        int m1 = 0, m2 = 0;
        for (int i = 0, j = 1; i < N - 1 || j < N; i++, j++) {
            if ((i < N - 1) && ((A[i] ^ A[i + 1]) & 1) == 0) {
                m1++;
                i++;
            }
            if ((j < N) && ((A[j] ^ A[(j + 1) % N]) & 1) == 0) {
                m2++;
                j++;
            }
        }
        return Math.max(m1, m2);
    }
}
