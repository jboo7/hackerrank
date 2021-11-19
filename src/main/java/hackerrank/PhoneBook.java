package hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < N; i++) {
                String name = br.readLine();
                String number = br.readLine();
                map.put(name, number);
            }
            while (true) {
                String name = br.readLine();
                if (name == null || name.isBlank()) {
                    break;
                }
                String number = map.get(name);
                if (number != null) {
                    System.out.printf("%s=%s%n", name, number);
                } else {
                    System.out.println("Not found");
                }
            }
        } catch (IOException e) {
        }
    }
}
