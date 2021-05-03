package hackerrank;

import java.math.BigInteger;

public class ExtraLongFactorials {
    public static void main(String[] args) {
        extraLongFactorials(1000);
    }

    // Complete the extraLongFactorials function below.
    static void extraLongFactorials(int n) {
        BigInteger ans = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            ans = ans.multiply(BigInteger.valueOf(i));
        }
        System.out.println(ans);
    }
}
