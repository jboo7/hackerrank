package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MaximumElement {
    public static List<Integer> getMax(List<String> operations) {
        Stack<int[]> stack = new Stack<>();
        List<Integer> results = new ArrayList<>();
        for (String op : operations) {
            String[] opp = op.split(" ");
            if ("1".equalsIgnoreCase(opp[0])) {
                int v = Integer.parseInt(opp[1]);
                if (stack.isEmpty()) {
                    stack.push(new int[]{v, v});
                } else {
                    stack.push(new int[]{v, Math.max(v, stack.peek()[1])});
                }
            } else if ("2".equalsIgnoreCase(opp[0])) {
                stack.pop();
            } else {
                results.add(stack.peek()[1]);
            }
        }
        return results;
    }
}