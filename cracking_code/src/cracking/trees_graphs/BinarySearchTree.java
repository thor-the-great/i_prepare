package cracking.trees_graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Martin G on 1/9/2017.
 */
public class BinarySearchTree <Key extends Comparable, Value> {

    Node root;

    public BinarySearchTree() {

    }

    public BinarySearchTree(Value[] initArray) {
        this();
        for (Value val : initArray) {
            this.put((Key) val, val);
        }
    }

    public void put(Key key, Value value) {
        root = put(key, value, root);
    }

    private Node put(Key key, Value value, Node node) {
        if (node == null) {
            return new Node(key, value);
        }
        int comparision = key.compareTo(node.key);
        if (comparision < 0) {
            node.left = put(key, value, node.left);
            if (node.left.parent == null) {
                node.left.parent = node;
            }
        } else if (comparision >= 0 ) {
            node.right = put(key, value, node.right);
            if (node.right.parent == null) {
                node.right.parent = node;
            }
        } /*else {
            node.value = value;
        }*/
        return node;
    }

    public Value get(Key key) {
        Node nodeToWork = root;
        while (nodeToWork != null) {
            int comparision = key.compareTo(nodeToWork.key);
            if (comparision > 0) {
                nodeToWork = nodeToWork.right;
            } else if (comparision < 0 ) {
                nodeToWork = nodeToWork.left;
            } else {
                return nodeToWork.value;
            }
        }
        return null;
    }

    public Node getNode(Key key) {
        Node nodeToWork = root;
        while (nodeToWork != null) {
            int comparision = key.compareTo(nodeToWork.key);
            if (comparision > 0) {
                nodeToWork = nodeToWork.right;
            } else if (comparision < 0 ) {
                nodeToWork = nodeToWork.left;
            } else {
                return nodeToWork;
            }
        }
        return null;
    }

    /*Iterable<Key> iterator() {

    }*/

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BST in level order traversal : \n");

        int bstH = getHeight(root);
        for (int i = 1; i <= bstH; i++) {
            sb.append(i).append(" : ");
            printOneLevel(root, i, sb);
            sb.append("\n");
        }
        return sb.toString();
    }

    private void printOneLevel(Node n, int i, StringBuilder sb) {
        if (n == null) {
            sb.append("null ");
            return;
        }
        if (i == 1)
            sb.append(n.value).append(" ");
        else {
            printOneLevel(n.left, i - 1, sb);
            printOneLevel(n.right, i - 1, sb);
        }
    }

    public int getBSTHeight() {
        return getHeight(root);
    }

    private int getHeight(Node n) {
        if (n == null)
            return 0;
        int lH = getHeight(n.left);
        int lR = getHeight(n.right);

        if (lH > lR) return lH + 1;
        else         return lR + 1;
    }

    class Node {
        Key key;
        Value value;
        Node left, right, parent;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node (" + key + ", " + value + ")";
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.put(1, 1);
        bst.put(4, 4);
        bst.put(7, 7);
        bst.put(2, 2);
        bst.put(100, 100);
        bst.put(15, 15);
        bst.put(8, 8);
        bst.put(6, 6);

        System.out.print(bst);
    }
}
