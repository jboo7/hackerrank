package codility;

public class CodilityBinaryGap {
    public static void main(String[] args) {
        CodilityBinaryGap codilityBinaryGap = new CodilityBinaryGap();
        System.out.println(codilityBinaryGap.solution(20));
    }

    public int solution(int N) {
        // write your code in Java SE 8
        int max = 0, len = 0;
        boolean started = false;
        while (N > 0) {
            int b = N & 1;
            if (b == 1 && !started) {
                started = true;
                N >>>= 1;
                continue;
            } else if (b == 1) {
                max = Math.max(max, len);
                len = 0;
                started = false;
                continue;
            }
            if (started) {
                len++;
            }
            N >>>= 1;
        }
        return max;
    }
}
