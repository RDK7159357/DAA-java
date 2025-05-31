package section1;

class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class TreeTraversal
 {
    Node root;

    public TreeTraversal() {
        root = null;
    }

    // Inorder traversal: Left, Root, Right
    public void inorder(Node node) {
        if (node != null) {
            inorder(node.left);           // Traverse left subtree
            System.out.print(node.data + " "); // Visit root
            inorder(node.right);          // Traverse right subtree
        }
    }

    // Preorder traversal: Root, Left, Right
    public void preorder(Node node) {
        if (node != null) {
            System.out.print(node.data + " "); // Visit root
            preorder(node.left);          // Traverse left subtree
            preorder(node.right);         // Traverse right subtree
        }
    }

    // Postorder traversal: Left, Right, Root
    public void postorder(Node node) {
        if (node != null) {
            postorder(node.left);         // Traverse left subtree
            postorder(node.right);        // Traverse right subtree
            System.out.print(node.data + " "); // Visit root
        }
    }

    public static void main(String[] args) {
        // Create a sample tree:
        //         1
        //        / \
        //       2   3
        //      / \
        //     4   5
        TreeTraversal tree = new TreeTraversal();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        System.out.print("Inorder traversal: ");
        tree.inorder(tree.root);
        System.out.println();

        System.out.print("Preorder traversal: ");
        tree.preorder(tree.root);
        System.out.println();

        System.out.print("Postorder traversal: ");
        tree.postorder(tree.root);
        System.out.println();
    }
}

