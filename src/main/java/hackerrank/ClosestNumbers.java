package hackerrank;

import java.util.*;

public class ClosestNumbers {

    public static void main(String[] args) {
        System.out.println(closestNumbers(new ArrayList<>(List.of(-20, -3916237, -357920, -3620601, 7374819, -7330761, 30, 6246457, -6461594, 266854, -520, -470))));
    }

    public static List<Integer> closestNumbers(List<Integer> arr) {
        // Write your code here
        Collections.sort(arr);
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 1; i < arr.size(); i++) {
            map.computeIfAbsent(arr.get(i) - arr.get(i - 1), k -> new ArrayList<>())
               .add(i);
        }
        List<Integer> ans = new ArrayList<>();
        if (!map.isEmpty()) {
            List<Integer> ps = map.values()
                                  .iterator()
                                  .next();
            for (Integer p : ps) {
                ans.add(arr.get(p - 1));
                ans.add(arr.get(p));
            }
        }
        return ans;
    }
}
