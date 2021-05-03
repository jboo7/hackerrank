package hackerrank;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class TheFullCountingSort {
    public static void main(String[] args) throws IOException {
        try (Scanner sc = new Scanner(new FileReader("D:/input05.txt"))) {
            int N = sc.nextInt();

            final int F = 100;
            StringBuilder[] counts = IntStream.range(0, F)
                                              .mapToObj(StringBuilder::new)
                                              .toArray(StringBuilder[]::new);
            for (int i = 0; i < N; i++) {
                int x = sc.nextInt();
                String s = sc.next();

                if (counts[x].length() > 0) {
                    counts[x].append(" ");
                }
                counts[x].append(i < N / 2 ? "-" : s);
            }

            boolean first = true;
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < F; i++) {
                if (counts[i].length() > 0) {
                    if (!first) {
                        result.append(" ");
                    }
                    first = false;
                    result.append(counts[i]);
                }
            }
            System.out.println(result);
        }
    }

    static void countSort(List<List<String>> arr) {
        final int N = arr != null ? arr.size() : 0;
        final int F = 100;
        StringBuilder[] counts = IntStream.range(0, F)
                                          .mapToObj(StringBuilder::new)
                                          .toArray(StringBuilder[]::new);
        for (int i = 0; i < N; i++) {
            List<String> a = arr.get(i);
            int x = Integer.parseInt(a.get(0));
            String s = a.get(1);

            if (counts[x].length() > 0) {
                counts[x].append(" ");
            }
            counts[x].append(i < N / 2 ? "-" : s);
        }
        boolean first = true;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < F; i++) {
            if (counts[i].length() > 0) {
                if (!first) {
                    result.append(" ");
                }
                first = false;
                result.append(counts[i]);
            }
        }
        System.out.println(result);
    }
}
