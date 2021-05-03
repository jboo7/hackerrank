package hackerrank;

public class CamelCase {
    public static void main(String[] args) {

    }

    static int camelcase(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if ('A' <= c && c <= 'Z') {
                count++;
            }
        }
        return count+1;
    }
}
