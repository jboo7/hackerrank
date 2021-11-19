package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class JavaList {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int N = sc.nextInt();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                list.add(sc.nextInt());
            }
            int Q = sc.nextInt();
            for (int i = 0; i < Q; i++) {
                String op = sc.next();
                if ("Insert".equalsIgnoreCase(op)) {
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    list.add(x, y);
                } else if ("Delete".equalsIgnoreCase(op)) {
                    int x = sc.nextInt();
                    list.remove(x);
                }
            }
            System.out.println(list.stream().map(Object::toString).collect(Collectors.joining(" ")));
        }
    }
}
