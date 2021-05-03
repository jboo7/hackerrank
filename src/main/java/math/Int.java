package math;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class Int {
    static boolean NON_NEG = false;
    static boolean NEG = true;

    static final int BASE = 10;

    static final Int ZERO = nonNegative(new int[]{0});
    static final Int ONE = nonNegative(new int[]{1});


    private final boolean negative;
    private final int[] digits;

    private Int(boolean negative, int[] digits) {
        this.negative = negative;
        if (digits == null || digits.length == 0) {
            throw new IllegalArgumentException("invalid digits " + Arrays.toString(digits));
        }
        for (int d : digits) {
            if (d < 0 || 10 <= d) {
                throw new IllegalArgumentException("invalid digits " + Arrays.toString(digits));
            }
        }
        if (negative && digits.length == 1 && digits[0] == 0) {
            throw new IllegalArgumentException("invalid negative and digits " + true + " " + Arrays.toString(digits));
        }
        this.digits = digits;
    }

    @Override
    public String toString() {
        return String.format("Int[%s%s]", isNegative() ? "-" : "", Arrays.stream(getDigits())
                                                                         .mapToObj(Integer::toString)
                                                                         .collect(Collectors.joining("")));
    }

    boolean isNegative() {
        return negative;
    }

    int[] getDigits() {
        return digits;
    }

    static Int nonNegative(int[] digits) {
        return new Int(NON_NEG, digits);
    }

    static Int negative(int[] digits) {
        return new Int(NEG, digits);
    }

    public static Int of(int v) {
        // trivial cases
        if (v == 0) {
            return Int.ZERO;
        }
        if (v == 1) {
            return Int.ONE;
        }
        // calculating the size of the number
        int n, size, i;
        for (size = 0, n = abs(v); n > 0; n /= BASE) {
            ++size;
        }
        // parsing digits
        int[] digits = new int[size];
        for (i = size - 1, n = abs(v); i >= 0; --i, n /= BASE) {
            digits[i] = n % BASE;
        }
        return v > 0 ? Int.nonNegative(digits) : Int.negative(digits);
    }

    public static Int of(String v) {
        // getting contents
        final char[] chars = v != null ? v.toCharArray() : null;
        final int len = chars != null ? chars.length : 0;
        // index
        int i = 0;
        // trivial cases
        if (len == 0 || len == 1 && chars[i] == '0') {
            return Int.ZERO;
        }
        if (len == 1 && chars[i] == '1') {
            return Int.ONE;
        }
        // sign parsing and skipping
        boolean negative = false;
        if (chars[i] == '-' || chars[i] == '+') {
            negative = chars[i] == '-';
            ++i;
        } else if (chars[i] < '0' || '9' < chars[i]) {
            throw new IllegalArgumentException("invalid " + v);
        }
        // skipping leading zeros if any
        while (i < len - 1 && chars[i] == '0') {
            ++i;
        }
        // parsing digits
        int[] digits = new int[len - i];
        int k = 0;
        for (; i < len; ++i) {
            digits[k++] = chars[i] - '0';
        }
        return negative ? Int.negative(digits) : Int.nonNegative(digits);
    }
}
