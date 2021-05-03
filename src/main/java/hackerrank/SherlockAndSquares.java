package hackerrank;

import java.util.NavigableSet;
import java.util.TreeSet;

public class SherlockAndSquares {
    // Complete the squares function below.
    static int squares(int a, int b) {
        NavigableSet<Integer> set = new TreeSet<>();
        for (int i = 1; i * i <= 1_000_000_000; i++) {
            set.add(i * i);
        }
        return set.subSet(a, true, b, true).size();
    }

    public static void main(String[] args) {
        System.out.println(squares(1, 1_000_000_000));
    }
}
