package week_5;

import java.util.Comparator;
import java.util.PriorityQueue;

// Node class represents each node in the Huffman Tree.
class HuffmanNode {
    int data;           // Frequency of the character
    char c;             // The character
    HuffmanNode left;   // Left child
    HuffmanNode right;  // Right child
}

// Comparator class to order the Huffman nodes in the priority queue.
class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        // Compare nodes based on frequency.
        return x.data - y.data;
    }
}

public class Huffman {

    // Recursive function to print the Huffman code from the root of the tree.
    // 's' accumulates the code as we traverse the tree.
    public static void printCode(HuffmanNode root, String s) {
        // Base case: if this node is a leaf node, print the code.
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + " : " + s);
            return;
        }
        // Traverse left subtree and append "0"
        if (root.left != null) {
            printCode(root.left, s + "0");
        }
        // Traverse right subtree and append "1"
        if (root.right != null) {
            printCode(root.right, s + "1");
        }
    }

    public static void main(String[] args) {
        // Number of characters.
        int n = 6;
        // Array of characters.
        char[] charArray = { 'a', 'b', 'c', 'd', 'e', 'f' };
        // Array of frequencies corresponding to the characters.
        int[] charfreq = { 5, 9, 12, 13, 16, 45 };

        // Create a priority queue (min-heap) for building the Huffman Tree.
        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n, new MyComparator());

        // Create leaf nodes for each character and add them to the priority queue.
        for (int i = 0; i < n; i++) {
            HuffmanNode hn = new HuffmanNode();
            hn.c = charArray[i];
            hn.data = charfreq[i];
            hn.left = null;
            hn.right = null;
            q.add(hn);
        }

        // Iterate until there is only one node left in the priority queue.
        // This node will be the root of the Huffman Tree.
        HuffmanNode root = null;
        while (q.size() > 1) {
            // Remove the two nodes with the lowest frequency.
            HuffmanNode x = q.poll();
            HuffmanNode y = q.poll();

            // Create a new internal node with a frequency equal to the sum of the two nodes.
            HuffmanNode sum = new HuffmanNode();
            sum.data = x.data + y.data;
            sum.c = '-'; // Internal node; not a leaf.
            sum.left = x;
            sum.right = y;

            // Add the new node to the priority queue.
            q.add(sum);
        }

        // The remaining node is the root of the Huffman Tree.
        root = q.poll();

        // Print the Huffman Codes by traversing the tree.
        System.out.println("Huffman Codes are : ");
        printCode(root, "");
    }
}

