package hackerrank;

public class FindDigits {
    public static void main(String[] args) {

    }

    /*
     * Complete the 'findDigits' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER n as parameter.
     */

    public static int findDigits(int n) {
        // Write your code here
        final int N = n;
        int count = 0;
        while (n > 0) {
            int a = n % 10;
            if (a != 0 && (N % a == 0)) {
                count++;
            }
            n /= 10;
        }
        return count;
    }
}
