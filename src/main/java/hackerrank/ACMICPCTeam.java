package hackerrank;

import java.math.BigInteger;
import java.util.*;

public class ACMICPCTeam {
    public static void main(String[] args) {
        var topics = List.of(
                "10101",
                "11100",
                "11010",
                "00101"
        );

        System.out.println(acmTeam(topics));
    }

    public static List<Integer> acmTeam(List<String> topic) {
        // Write your code here
        Map<Integer, BigInteger> m = new HashMap<>();
        Map<Integer, Integer> t = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0, id = 1; i < topic.size(); i++) {
            String a = topic.get(i);
            BigInteger abi = m.computeIfAbsent(i, k -> new BigInteger(a, 2));

            for (int j = i + 1; j < topic.size(); j++, id++) {
                String b = topic.get(j);
                BigInteger bbi = m.computeIfAbsent(j, k -> new BigInteger(b, 2));

                BigInteger r = abi.or(bbi);

                int numberOfTopics = getNumberOfTopics(r);
                t.put(numberOfTopics, t.getOrDefault(numberOfTopics, 0) + 1);
            }
        }
        var n = t.entrySet().iterator().next();
        return Arrays.asList(n.getKey(), n.getValue());
    }

    private static int getNumberOfTopics(BigInteger bi) {
        int count = 0;
        for (int  i = 0; i < bi.bitLength(); i++) {
            count += (bi.testBit(i) ? 1 : 0);
        }
        return count;
    }
}
