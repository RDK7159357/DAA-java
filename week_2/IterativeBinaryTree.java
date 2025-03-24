package week_2;

import java.util.Stack;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

public class IterativeBinaryTree {
    Node root;

    public IterativeBinaryTree() {
        root = null;
    }

    // Iterative Inorder Traversal using a stack
    public void inorderIterative(Node root) {
        Stack<Node> stack = new Stack<>();
        Node current = root;

        System.out.print("Inorder Traversal: ");
        // Traverse the tree
        while (current != null || !stack.isEmpty()) {
            // Reach the left most Node of the current Node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // current must be null at this point, so we pop the top element
            current = stack.pop();
            System.out.print(current.data + " ");
            // We have visited the node and its left subtree. Now, it's right subtree's turn
            current = current.right;
        }
        System.out.println();
    }

    // Iterative Preorder Traversal using a stack
    public void preorderIterative(Node root) {
        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        System.out.print("Preorder Traversal: ");
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.data + " ");
            // Push right child first so that left is processed first
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println();
    }

    // Iterative Postorder Traversal using one stack
    public void postorderIterative(Node root) {
        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        Node current = root;
        Node lastVisited = null;
        System.out.print("Postorder Traversal: ");

        while (current != null || !stack.isEmpty()) {
            // Reach the left most node of the current node
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                Node peekNode = stack.peek();
                // if right child exists and traversing node from left, then move to right child
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    current = peekNode.right;
                } else {
                    System.out.print(peekNode.data + " ");
                    lastVisited = stack.pop();
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /*
                Constructed Binary Tree:
                        1
                      /   \
                     2     3
                    / \   / 
                   4   5 6  
        */
        IterativeBinaryTree tree = new IterativeBinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);

        tree.inorderIterative(tree.root);
        tree.preorderIterative(tree.root);
        tree.postorderIterative(tree.root);
    }
}

