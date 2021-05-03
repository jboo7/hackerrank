package codility;

public class TapeEquilibrium {
    public static void main(String[] args) {
        TapeEquilibrium tapeEquilibrium = new TapeEquilibrium();
        System.out.println(tapeEquilibrium.solution(new int[]{3, 1, 2, 3, 4}));
    }

    public int solution(int[] A) {
        long left = 0, right = 0;
        for (int a : A) {
            right += a;
        }
        long min = Integer.MAX_VALUE;
        for (int i = 0; i < A.length - 1; i++) {
            left += A[i];
            right -= A[i];

            min = Math.min(min, Math.abs(left - right));
        }
        return (int) min;
    }
}
