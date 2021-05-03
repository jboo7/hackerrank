package hackerrank;

import java.util.ArrayList;
import java.util.List;

public class BetweenTwoSets {
    public static int getTotalX(List<Integer> a, List<Integer> b) {
        // Write your code here
        int l = a.get(a.size() - 1);
        List<Integer> list = new ArrayList<>();
        for (int i = l; i <= 100; i++) {
            boolean f = true;
            for (int d : a) {
                if (i % d != 0) {
                    f = false;
                    break;
                }
            }
            for (int j = 0; f && j < b.size(); j++) {
                if (b.get(j) % i != 0) {
                    f = false;
                    break;
                }
            }
            if (f) {
                list.add(i);
            }
        }
        return list.size();
    }

    private static int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }
}
