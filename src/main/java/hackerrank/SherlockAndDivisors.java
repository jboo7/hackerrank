package hackerrank;

public class SherlockAndDivisors {
    public static void main(String[] args) {

    }

    public static int divisors(int n) {
        return divs(n, 2);
    }

    private static int divs(int n, int k) {
        // integer to count the divisors
        int count = 0, i, a = (int) Math.sqrt(n);

        // Traverse from 1 to sqrt(N)
        for (i = 1; i <= a; i++)
        {

            // Check if i is a factor
            if (n % i == 0)
            {
                // increase the count if i
                // is divisible by k
                if (i % k == 0)
                {
                    count++;
                }

                // (n/i) is also a factor
                // check whether it is divisible by k
                if ((n / i) % k == 0)
                {
                    count++;
                }
            }
        }

        i--;

        // If the number is a perfect square
        // and it is divisible by k
        if ((i * i == n) && (i % k == 0))
        {
            count--;
        }

        return count;
    }
}
