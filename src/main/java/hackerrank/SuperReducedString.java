package hackerrank;

import java.util.LinkedHashMap;
import java.util.Map;

public class SuperReducedString {
    public static void main(String[] args) {
        System.out.println(superReducedString("baab"));
    }

    static String superReducedString(String s) {
        String a;
        while (true) {
            a = reduce(s);
            if (a.length() == s.length()) {
                break;
            }
            s = a;
        }
        return a.isEmpty() ? "Empty String" : a;
    }

    static String reduce(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0, N = chars.length; i < N; i++) {
            char c = chars[i];
            int j = i + 1;
            while (j < N && chars[j] == c) {
                j++;
            }
            if ((j - i) % 2 == 1) {
                sb.append(c);
            }
            i = j - 1;
        }
        String a = sb.toString();
        return a.isEmpty() ? "Empty String" : a;
    }
}
