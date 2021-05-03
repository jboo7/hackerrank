package codility;

public class MinPerimeterRectangle {
    public static void main(String[] args) {
        MinPerimeterRectangle minPerimeterRectangle = new MinPerimeterRectangle();
        System.out.println(minPerimeterRectangle.solution(101));
    }

    public int solution(int N) {
        // write your code in Java SE 8
        int a, b, min = Integer.MAX_VALUE;
        final int sqrtN = (int) Math.sqrt(N) + 1;
        for (int i = 1; i <= sqrtN; i++) {
            a = i;
            if (N % a != 0) {
                continue;
            }
            b = N / a;

            min = Math.min(min, 2 * (a + b));
        }
        return min;
    }
}
