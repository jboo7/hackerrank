package hackerrank;

import java.util.List;

public class LonelyInteger {
    public static void main(String[] args) {

    }

    public static int lonelyinteger(List<Integer> a) {
        int v = 0;
        for (int n : a) {
            v ^= n;
        }
        return v;
    }
}
