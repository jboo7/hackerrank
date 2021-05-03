package hackerrank;

import java.util.ArrayDeque;
import java.util.Queue;

public class TreeLevelOrderTraversal {
    public static void levelOrder(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node v = q.poll();
            if (v == null) {
                continue;
            }
            System.out.print(v.data + " ");
            if (v.left != null) {
                q.add(v.left);
            }
            if (v.right != null) {
                q.add(v.right);
            }
        }
    }

    class Node {
        int data;
        Node left;
        Node right;
    }
}
