
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
    String data;
    Node left;
    Node right;

    public Node(String data) {
        this.data = data;
    }

    static Node makeNode(String data) {
        if (".".equals(data)) {
            return null;
        } else {
            return new Node(data);
        }
    }
}

class Tree {
    Node root;

    Node insert(Node root, String data, Node left, Node right) {
        if (root == null) {
            Node newNode = new Node(data);
            newNode.left = left;
            newNode.right = right;
            return newNode;
        } else if (data.equals(root.data)) {
            root.left = left;
            root.right = right;
            return root;
        } else {
            insert(root.left, data, left, right);
            insert(root.right, data, left, right);
            return root;
        }
    }

    void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.data);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data);
            inOrder(root.right);
        }
    }

    void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data);
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Tree tree = new Tree();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        String data = st.nextToken();
        String left = st.nextToken();
        String right = st.nextToken();
        tree.root = new Node(data);
        tree.root.left = Node.makeNode(left);
        tree.root.right = Node.makeNode(right);
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            data = st.nextToken();
            Node leftNode = Node.makeNode(st.nextToken());
            Node rightNode = Node.makeNode(st.nextToken());
            tree.insert(tree.root, data, leftNode, rightNode);
        }
        tree.preOrder(tree.root);
        System.out.println();
        tree.inOrder(tree.root);
        System.out.println();
        tree.postOrder(tree.root);
    }
}
