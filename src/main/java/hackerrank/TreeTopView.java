package hackerrank;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TreeTopView {
    public static void main(String[] args) {

    }

    public static void topView(Node root) {
        if (root == null) {
            return;
        }

        final Map<Integer, Node> map = new TreeMap<>();

        Queue<NQ> q = new ArrayDeque<>();
        q.add(new NQ(root, 0));

        while (!q.isEmpty()) {
            NQ v = q.poll();

            if (!map.containsKey(v.level)) {
                map.put(v.level, v.node);
            }

            if (v.node.left != null) {
                q.add(new NQ(v.node.left, v.level - 1));
            }
            if (v.node.right != null) {
                q.add(new NQ(v.node.right, v.level + 1));
            }
        }

        map.values()
           .stream()
           .map(n -> n.data + " ")
           .forEach(System.out::print);
    }

    private static class NQ {
        final Node node;
        final int level;

        public NQ(Node node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    class Node {
        int data;
        Node left;
        Node right;
    }
}
