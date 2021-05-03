package hackerrank;

public class BinarySearchTreeInsertion {
    public static void main(String[] args) {

    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            Node n = new Node();
            n.data = data;
            return n;
        }

        if (data < root.data) {
            root.left = insert(root.left, data);
        } else if (data > root.data) {
            root.right = insert(root.right, data);
        }
        return root;
    }

    static class Node {
        int data;
        Node left;
        Node right;
    }
}
