package hackerrank;

public class MaximizingXOr {
    public static void main(String[] args) {

    }

    public static int maximizingXor(int l, int r) {
        // Write your code here
        int max = -1;
        for (int a = l; a < r; a++) {
            for (int b = a + 1; b <= r; b++) {
                max = Math.max(a ^ b, max);
            }
        }
        return max;
    }
}
