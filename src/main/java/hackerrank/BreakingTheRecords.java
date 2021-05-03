package hackerrank;

public class BreakingTheRecords {
    static int[] breakingRecords(int[] scores) {
        int[] answer = new int[2];
        int min = scores[0], max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (min > scores[i]) {
                answer[1]++;
                min = scores[i];
            }
            if (max < scores[i]) {
                answer[0]++;
                max = scores[i];
            }
        }
        return answer;
    }
}
