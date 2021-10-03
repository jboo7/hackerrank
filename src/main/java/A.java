import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class A {
    static class Node {
        Node left, right;
        int data;
    }

    public static void main(String[] args) {
    }

    static boolean checkBST(Node root) {
        List<Integer> list = new ArrayList<>();

        class C implements java.util.function.Consumer<Node> {
            public void accept(Node n) {
                if (n == null) {
                    return;
                }
                this.accept(n.left);
                list.add(n.data);
                this.accept(n.right);
            }
        }

        new C().accept(root);
        boolean r = true;
        for (int i = 0, N = list.size() - 1; r && i < N; i++) {
            r = list.get(i) < list.get(i + 1);
        }
        return r;
    }
}
