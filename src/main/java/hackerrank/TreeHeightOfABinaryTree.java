package hackerrank;

public class TreeHeightOfABinaryTree {
    public static void main(String[] args) {

    }

    public static int height(Node root) {
        return height(root, 1) - 1;
    }

    private static int height(Node root, int h) {
        if (root == null) {
            return h;
        }
        return Math.max(height(root.left, h + 1), height(root.right, h + 1));
    }

    class Node {
        int data;
        Node left;
        Node right;
    }
}
