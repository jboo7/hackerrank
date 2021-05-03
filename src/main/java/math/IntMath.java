package math;

import java.util.Arrays;

import static java.lang.Math.max;
import static math.Int.*;

public class IntMath {
    public static Int sum(Int x, Int y) {
        final int[] r = array_sum(x.getDigits(), x.isNegative(), y.getDigits(), y.isNegative());
        if (r[0] == BASE - 1) {
            return Int.negative(normalize(array_sum(complement(r), NON_NEG, ONE.getDigits(), ONE.isNegative())));
        }
        return Int.nonNegative(normalize(r));
    }

    public static Int subtract(Int x, Int y) {
        final int[] r = array_sum(x.getDigits(), x.isNegative(), y.getDigits(), !y.isNegative());
        if (r[0] == BASE - 1) {
            return Int.negative(normalize(array_sum(complement(r), NON_NEG, ONE.getDigits(), ONE.isNegative())));
        }
        return Int.nonNegative(normalize(r));
    }

    public static Int mul(Int x, Int y) {
        final int[] r = normalize(array_mul(x.getDigits(), y.getDigits()));
        if (x.isNegative() != y.isNegative()) {
            return Int.negative(r);
        }
        return Int.nonNegative(r);
    }

    public static Int mul_kuratsuba(Int x, Int y) {
        final int[] xd = x.getDigits(), yd = y.getDigits();
        final int nx = xd.length, ny = yd.length, n = max(nx, ny);
        final int[] a = new int[n], b = new int[n];
        System.arraycopy(xd, 0, a, n - nx, nx);
        System.arraycopy(yd, 0, b, n - ny, ny);

        final int[] r = normalize(array_kuratsuba_mul(a, 0, n, b, 0, n));
        if (x.isNegative() != y.isNegative()) {
            return Int.negative(r);
        }
        return Int.nonNegative(r);
    }

    public static Int shift_left(Int x, int shift) {
        if (x.isNegative()) {
            return Int.negative(shift_left(x.getDigits(), shift));
        }
        return Int.nonNegative(shift_left(x.getDigits(), shift));
    }

    static int[] array_sum(int[] a, boolean aneg, int[] b, boolean bneg) {
        final int na = a.length, nb = b.length, nr = max(na, nb) + 1;
        final int[] r = new int[nr];
        int c = (aneg ? 1 : 0) + (bneg ? 1 : 0);
        for (int ai = na - 1, bi = nb - 1, ri = nr - 1, ad, bd; ai >= 0 || bi >= 0 || ri >= 0; --ai, --bi, --ri) {
            ad = 0 <= ai ? a[ai] : 0;
            ad = aneg ? BASE - 1 - ad : ad;

            bd = 0 <= bi ? b[bi] : 0;
            bd = bneg ? BASE - 1 - bd : bd;

            c += ad + bd;
            r[ri] = c % BASE;
            c /= BASE;
        }
        return r;
    }

    static int[] array_mul(int[] a, int[] b) {
        final int na = a.length, nb = b.length, nr = na + nb;
        final int[] r = new int[nr];
        for (int ai = na - 1, ri = nr - 1; ai >= 0; --ai, --ri) {
            for (int bi = nb - 1, k = 0; bi >= 0; --bi, --k) {
                r[ri + k] += a[ai] * b[bi];
            }
        }
        for (int ri = nr - 1, c = 0; ri >= 0; --ri) {
            c += r[ri];
            r[ri] = c % BASE;
            c /= BASE;
        }
        return r;
    }

    static int[] array_kuratsuba_mul(int[] x, int xb, int xe, int[] y, int yb, int ye) {
        if (xe - xb == 1 || ye - yb == 1) {
            return normalize(array_mul(Arrays.copyOfRange(x, xb, xe), Arrays.copyOfRange(y, yb, ye)));
        }

        final int xm = (xb + xe) / 2, ym = (yb + ye) / 2;
        // a -> [xb, xm)
        // b -> [xm, xe)
        // c -> [yb, ym)
        // d -> [ym, ye)
        int[] a = Arrays.copyOfRange(x, xb, xm);
        int[] b = Arrays.copyOfRange(x, xm, xe);
        int[] c = Arrays.copyOfRange(y, yb, ym);
        int[] d = Arrays.copyOfRange(y, ym, ye);

        // a*c
        int[] aXc = array_kuratsuba_mul(x, xb, xm, y, yb, ym);
        // b*d
        int[] bXd = array_kuratsuba_mul(x, xm, xe, y, ym, ye);
        // a+b
        int[] aPb = normalize(array_sum(a, NON_NEG, b, NON_NEG));
        // c+d
        int[] cPd = normalize(array_sum(c, NON_NEG, d, NON_NEG));
        // (a+b)*(c+d)
        int[] aPbXcPd = array_kuratsuba_mul(aPb, 0, aPb.length, cPd, 0, cPd.length);
        // a*c+b*d
        int[] aXcPbXd = normalize(array_sum(aXc, NON_NEG, bXd, NON_NEG));
        // (a+b)*(c+d)-(a*c+b*d)
        int[] q = normalize(array_sum(aPbXcPd, NON_NEG, aXcPbXd, NEG));
        // r
        return normalize(array_sum(array_sum(shift_left(aXc, xe - xb), NON_NEG, shift_left(q, (xe - xb) / 2), NON_NEG), NON_NEG,
            bXd, NON_NEG));
    }

    static int[] normalize(int[] a) {
        final int n = a.length;
        int i = 0;
        while (i < n - 1 && a[i] == 0) {
            ++i;
        }
        return Arrays.copyOfRange(a, i, n);
    }

    static int[] complement(int[] a) {
        final int an = a.length;
        final int[] r = new int[an];
        for (int i = 0; i < an; i++) {
            r[i] = BASE - 1 - a[i];
        }
        return r;
    }

    static int[] shift_left(int[] a, int shift) {
        final int na = a.length;
        final int[] r = new int[na + shift];
        int ri = 0;
        for (int ai = 0; ai < na; ++ri, ++ai) {
            r[ri] = a[ai];
        }
        for (; ri < na + shift; ++ri) {
            r[ri] = 0;
        }
        return r;
    }
}
