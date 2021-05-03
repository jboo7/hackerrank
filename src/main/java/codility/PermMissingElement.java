package codility;

public class PermMissingElement {
    public static void main(String[] args) {

    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        final long N = A.length;
        long sum = (1 + N + 1) * N / 2;
        for (int a : A) {
            sum -= a;
        }
        return (int) sum;
    }
}
