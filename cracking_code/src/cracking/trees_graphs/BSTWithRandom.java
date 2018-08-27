package cracking.trees_graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Martin G on 1/16/2017.
 */
public class BSTWithRandom<Key extends Comparable, Value> extends BinarySearchTree<Key, Value> {

    public BSTWithRandom(Value[] initArray) {
        super(initArray);
    }

    public void delete(Key key) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        Node searchNode = null;
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if (key.equals(node.key)) {
                searchNode = node;
                break;
            }
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        if (searchNode == null)
            return;
        removeNode(searchNode);
    }

    private void removeNode(Node searchNode) {
        if (searchNode.right == null && searchNode.left == null) {
            if (searchNode.parent.left.equals(searchNode)){
                searchNode.parent.left = null;
            } else {
                searchNode.parent.right = null;
            }
            return;
        }
        else if (searchNode.left == null || searchNode.right == null) {
            Node parentNode = searchNode.parent;
            if (searchNode.left != null) {
                if (searchNode.equals(parentNode.left)) {
                    parentNode.left = searchNode.left;
                } else {
                    parentNode.right = searchNode.left;
                }
            } else {
                if (searchNode.equals(parentNode.left)) {
                    parentNode.left = searchNode.right;
                } else {
                    parentNode.right = searchNode.right;
                }
            }
            searchNode = null;
        } //if node has both children - this is the most complex case
        else {
            Node tmp = searchNode.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            searchNode.key = tmp.key;
            searchNode.value = tmp.value;
            removeNode(tmp);
        }
    }

    public static void main(String[] args) {
        BSTWithRandom<Integer, Integer> bst = new BSTWithRandom(new Integer[] {7, 2, 8, 1, 3});
        System.out.println(bst);
        bst.delete(1);
        System.out.println(bst);
        bst.delete(8);
        System.out.println(bst);

        bst = new BSTWithRandom(new Integer[] {7, 2, 8, 1, 3});
        System.out.println(bst);
        bst.delete(2);
        System.out.println(bst);

        bst = new BSTWithRandom(new Integer[] {7, 2, 8, 1, 3, 5, 10, 15, 9});
        System.out.println(bst);
        bst.delete(8);
        System.out.println(bst);
    }
}
