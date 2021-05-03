package hackerrank;

import java.util.List;

public class SubarrayDivision {
    public static void main(String[] args) {
        System.out.println(birthday(List.of(1, 2, 1, 3, 2), 3, 2));
        System.out.println(birthday(List.of(4), 4, 1));
    }

    static int birthday(List<Integer> s, int d, int m) {
        int answer = 0;
        final int N = s.size();
        if (m > N) {
            return answer;
        }
        int sum = 0, count = 0;
        for (int i = 0, j = 0; i < N; i++) {
            sum += s.get(i);
            count++;
            if (count > m) {
                count--;
                sum -= s.get(j++);
            }
            if (count == m && sum == d) {
                answer++;
            }
        }
        return answer;
    }
}
