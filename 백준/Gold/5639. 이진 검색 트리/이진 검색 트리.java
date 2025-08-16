
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }
}

class BST {
    Node root;

    public BST(int data) {
        root = new Node(data);
    }


    void insert(int data) {
        insert(this.root, data);
    }

    Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            if (root.data > data) {
                root.left = insert(root.left, data);
            } else {
                root.right = insert(root.right, data);
            }
        }
        return root;
    }

    void postOrder() {
        postOrder(root);
    }

    void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.data);
        }
    }


}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int data = Integer.parseInt(br.readLine());
        BST bst = new BST(data);
        String line = null;
        while (true) {
            line = br.readLine();
            if (line == null || line.equals("")) {
                break;
            }
            int d = Integer.parseInt(line);
            bst.insert(d);
        }
        bst.postOrder();
    }
}
