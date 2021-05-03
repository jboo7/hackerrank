package hackerrank;

import java.util.List;

public class MigratoryBirds {
    // Complete the migratoryBirds function below.
    static int migratoryBirds(List<Integer> arr) {
        int[] fr = new int[5];
        for (int a : arr) {
            fr[a - 1]++;
        }
        int type = 0, max = fr[0];
        for (int i = 1; i < 5; i++) {
            if (max < fr[i]) {
                max = fr[i];
                type = i;
            }
        }
        return type + 1;
    }
}
