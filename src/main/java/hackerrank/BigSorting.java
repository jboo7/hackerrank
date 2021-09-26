package hackerrank;

import java.util.ArrayList;
import java.util.List;

public class BigSorting {
    public static List<String> bigSorting(List<String> unsorted) {
        // Write your code here
        List<String> sorted = new ArrayList<>(unsorted);
        sorted.sort(BigSorting::compare);
        return sorted;
    }

    private static int compare(String a, String b) {
        if (a.length() < b.length()) {
            return -1;
        } else if (a.length() > b.length()) {
            return 1;
        }
        for (int i = 0; i < a.length(); i++) {
            int ad = a.charAt(i) - '0';
            int bd = b.charAt(i) - '0';

            if (ad < bd) {
                return -1;
            } else if (ad > bd) {
                return 1;
            }
        }
        return 0;
    }
}
