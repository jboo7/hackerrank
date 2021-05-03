package hackerrank;

public class StrangeCounter {
    public static void main(String[] args) {
        for (int i = 1; i <= 22; i++) {
            System.out.println(i + " = " + strangeCounter(i));
        }
    }

    // Complete the strangeCounter function below.
    static long strangeCounter(long t) {
        long l = 0, r = 1;
        while ((3L * r) < t) {
            l = r;
            r = (r << 1) | 1;
        }
        return 6 * l + 4 - t;
    }
}
