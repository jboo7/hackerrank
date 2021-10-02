package hackerrank;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PasswordDecryption {
    public static void main(String[] args) {
        System.out.println(decryptPassword(""));
    }

    public static String decryptPassword(String s) {
        Deque<Character> chars = new ArrayDeque<>();
        for (int N = s.length(), e = 0, i = N - 1; i > e; ) {
            char cur = s.charAt(i);
            if (cur == '*') {
                char prev = s.charAt(i - 1);
                char beforePref = s.charAt(i - 2);
                chars.addFirst(beforePref);
                chars.addFirst(prev);
                i -= 3;
                continue;
            } else if (cur == '0') {
                chars.addFirst(s.charAt(e));
                e++;
            } else {
                chars.addFirst(cur);
            }
            i--;
        }
        return new String(chars.stream()
                               .mapToInt(Character::charValue)
                               .toArray(), 0, chars.size());
    }
}
