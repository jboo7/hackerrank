package hackerrank;

public class TreePostOrderTraversal {
    public static void main(String[] args) {

    }

    public static void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    }

    class Node {
        int data;
        Node left;
        Node right;
    }
}
