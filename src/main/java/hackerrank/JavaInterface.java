package hackerrank;

public class JavaInterface {
    static class MyCalculator {
        public int divisor_sum(int n) {
            int sum = n;
            for (int i = 1; n / i > 1; i++) {
                if (n % i == 0) {
                    sum += i;
                }
                System.out.println("i="+i);
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        MyCalculator c = new MyCalculator();
        System.out.println(c.divisor_sum(0));
    }
}
