package hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SequenceEquation {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(permutationEquation(new int[]{4, 3, 5, 1, 2})));
    }

    // Complete the permutationEquation function below.
    static int[] permutationEquation(int[] p) {
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < p.length; i++) {
            map2.put(p[i], i + 1);
        }
        int[] ans = new int[p.length];
        for (int i = 0; i < p.length; i++) {
            int x = i + 1;
            int px = map2.get(x);
            int ppx = map2.get(px);
            ans[i] = ppx;
        }
        return ans;
    }
}
