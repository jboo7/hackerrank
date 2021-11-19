package hackerrank;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class JavaStack {
    private static final Set<Character> OPENING = Set.of('[', '{', '(');
    private static final Map<Character, Character> CLOSING = Map.of(']', '[', '}', '{', ')', '(');

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNext()) {
                String input = sc.next();
                System.out.println(validate(input));
            }
        }
    }

    private static boolean validate(String s) {
        Stack<Character> st = new Stack<>();
        char[] chars = s.toCharArray();
        boolean isValid = true;
        for (int i = 0, N = chars.length; isValid && i < N; i++) {
            char c = chars[i];
            if (CLOSING.containsKey(c)) {
                isValid = !st.isEmpty() && st.pop() == CLOSING.get(c);
            } else {
                st.push(c);
            }
        }
        return isValid && st.isEmpty();
    }
}
