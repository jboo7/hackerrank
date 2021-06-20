package hackerrank;

import java.util.HashSet;
import java.util.Set;

public class IsFibo {

    private static final Set<Long> fibs = new HashSet<>();

    static {
        long a = 0, b = 1, t;
        while (a < 20_000_000_000L) {
            fibs.add(a);
            t = b;
            b = a + b;
            a = t;
        }
    }

    public static String isFibo(long n) {
        // Write your code here
        return fibs.contains(n) ? "IsFibo" : "IsNotFibo";
    }
}
