package codility;

import java.util.HashMap;
import java.util.Map;

public class CountDivisors {
    public static void main(String[] args) {
        CountDivisors countDivisors = new CountDivisors();
        System.out.println(countDivisors.solution(Integer.MAX_VALUE));
    }

    public int solution(int N) {
        // write your code in Java SE 8
        if (N == 1) {
            return 1;
        }
        final Map<Integer, Integer> primeFactors = new HashMap<>();
        final int sqrtN = (int) Math.sqrt(N) + 1;
        int div = 2;
        while (N > 1 && div <= sqrtN) {
            if (N % div == 0) {
                primeFactors.put(div, primeFactors.getOrDefault(div, 0) + 1);
                N /= div;
                continue;
            }
            div++;
        }
        if (N > 1) {
            primeFactors.put(N, primeFactors.getOrDefault(N, 0) + 1);
        }
        System.out.println(primeFactors);
        int count = 1;
        for (int pf : primeFactors.values()) {
            count *= (pf + 1);
        }
        return count;
    }
}
