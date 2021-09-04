package hackerrank;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MissingNumbers {
    public static void main(String[] args) {

    }

    public static List<Integer> missingNumbers(List<Integer> arr, List<Integer> brr) {
        // Write your code here
        return List.copyOf(find(arr, brr));
    }

    static <T extends Comparable<T>> Set<T> find(List<T> A, List<T> B) {
        Map<T, Integer> am = new TreeMap<>(), bm = new TreeMap<>();
        for (T a : A) {
            am.put(a, am.getOrDefault(a, 0) + 1);
        }
        for (T b : B) {
            bm.put(b, bm.getOrDefault(b, 0) + 1);
        }
        for (Map.Entry<T, Integer> ae : am.entrySet()) {
            int bf = bm.get(ae.getKey());
            if (bf == ae.getValue()) {
                bm.remove(ae.getKey());
            }
        }
        return bm.keySet();
    }
}
