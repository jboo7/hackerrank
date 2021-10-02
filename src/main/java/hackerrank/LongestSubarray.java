package hackerrank;

import java.util.List;

public class LongestSubarray {
    public static int longestSubarray(List<Integer> arr) {
        // Write your code here
        int max_len = Integer.MIN_VALUE;
        for (int i = 0, N = arr.size(); i < N; i++) {
            int pivot = arr.get(i);
            int r = Integer.MAX_VALUE;
            int len = 1;
            for (int j = i + 1; j < N; j++) {
                int cur = arr.get(j);
                if (r == Integer.MAX_VALUE && pivot == cur) {
                    len++;
                } else if (r == Integer.MAX_VALUE && Math.abs(pivot - cur) <= 1) {
                    len++;
                    r = cur;
                } else if (r != Integer.MAX_VALUE && (pivot == cur || r == cur)) {
                    len++;
                } else {
                    break;
                }
            }
            max_len = Math.max(len, max_len);
        }
        return max_len;
    }
}
