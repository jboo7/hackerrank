package hackerrank;

import math.Int;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class IsThisaBinarySearchTree {
    public static void main(String[] args) {
        int[] arr = {50,
                30, 70,
                20, 40, 60, 80,
                15, 25, 35, 45};
        Node root = build(arr, 1);

        System.out.println(new IsThisaBinarySearchTree().checkBST(root));
    }

    private static Node build(int[] arr, int i) {
        if (arr.length >= i) {
            Node n = new Node(arr[i - 1]);
            n.left = build(arr, i * 2);
            n.right = build(arr, i * 2 + 1);
            return n;
        }
        return null;
    }

    boolean checkBST(Node root) {
        List<Integer> list = new ArrayList<Integer>();
        traverse(root, list);
        for (int i = 0, N = list.size(); i < N - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    static void traverse(Node n, List<Integer> list) {
        if (n == null) {
            return;
        }
        traverse(n.left, list);
        list.add(n.data);
        traverse(n.right, list);
    }

    static class Node {
        private final int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
