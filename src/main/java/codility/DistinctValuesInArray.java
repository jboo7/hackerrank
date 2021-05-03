package codility;

import java.util.HashSet;
import java.util.Set;

public class DistinctValuesInArray {
    public static void main(String[] args) {

    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        Set<Integer> set = new HashSet<>();
        for (int a : A) {
            set.add(a);
        }
        return set.size();
    }
}
