package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PickingNumbers {
    public static void main(String[] args) {
        System.out.println(pickingNumbers(List.of(4, 6, 5, 3, 3, 1)));
    }

    public static int pickingNumbers(List<Integer> a) {
        // Write your code here
        int max = 0;
        Map<Integer, Integer> map = new TreeMap<>();
        for (Integer v : a) {
            map.put(v, map.getOrDefault(v, 0) + 1);
        }
        max = map.values()
                 .stream()
                 .reduce(max, Math::max);
        List<Integer> vs = new ArrayList<>(map.keySet());
        for (int i = 0; i < vs.size() - 1; i++) {
            int q = vs.get(i), w = vs.get(i + 1);
            if (w - q == 1) {
                max = Math.max(max, map.get(q) + map.get(w));
            }
        }
        return max;
    }
}