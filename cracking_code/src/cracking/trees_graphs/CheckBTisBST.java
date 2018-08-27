package cracking.trees_graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by thor on 1/10/17.
 */
public class CheckBTisBST {

    boolean checkTree(BinarySearchTree<Integer, Integer> tree) {
        //this approach is based on in-order traversal
        int minElement = Integer.MIN_VALUE;
        Stack<BinarySearchTree.Node> stack = new Stack<>();
        BinarySearchTree<Integer, Integer>.Node node = tree.root;
        visitNodesToLeft(stack, node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            if (node.value > minElement) {
                minElement = node.value;
            }
            else {
                return false;
            }
            if (node.right != null) {
                node = node.right;
                visitNodesToLeft(stack, node);
            }
        }
        return true;
    }

    private void visitNodesToLeft(Stack<BinarySearchTree.Node> stack, BinarySearchTree<Integer, Integer>.Node node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    boolean checkTreeMinMaxApproach(BinarySearchTree<Integer, Integer> tree) {
        //this approach visits all nodes from the root and checking value range. It supports identical elements. O is O(N), space is O(logN)
        return checkNodes(tree.root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private boolean checkNodes(BinarySearchTree<Integer, Integer>.Node node, int max, int min) {
        if (node == null) {
            return true;
        }
        if (node.value <= min || node.value > max) {
            return false;
        }
        if (!checkNodes(node.left, node.value, min) || !checkNodes(node.right, max, node.value)) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        CheckBTisBST obj = new CheckBTisBST();

        int[] array = new int[] {4, 5, 2, 67, 23, 12, 25, 6, 7, 80, 3, 9, 50, 11, 22, 1, 15};
        Arrays.sort(array);
        MinimalTreeFromSortedArray minimalTreeBuilder = new MinimalTreeFromSortedArray();
        BinarySearchTree<Integer, Integer> bst = minimalTreeBuilder.minimalTreeFromArray(array);
        System.out.println("BST #1 ");
        ListOfDepths listOfD = new ListOfDepths();
        listOfD.printBSTLevels(listOfD.getDepthsLists(bst));

        System.out.println("\nIs this tree BST : " + obj.checkTree(bst));

        bst = new BinarySearchTree<>();
        bst.put(1, 1);
        bst.put(4, 4);
        bst.put(7, 7);
        bst.put(2, 2);
        bst.put(100, 100);
        bst.put(15, 15);
        bst.put(8, 8);
        bst.put(6, 6);
        bst.put(102, 102);
        bst.put(103, 103);
        bst.put(104, 104);
        bst.put(105, 105);
        bst.put(106, 106);
        bst.put(107, 107);
        bst.put(108, 108);
        System.out.println("BST #2 ");
        listOfD.printBSTLevels(listOfD.getDepthsLists(bst));

        System.out.println("\nIs this tree BST : " + obj.checkTree(bst));

        bst = new BinarySearchTree<>();
        bst.put(10, 10);
        bst.put(4, 4);
        bst.put(7, 7);
        bst.put(5, 5);
        bst.put(12, 12);
        bst.put(2, 2);
        bst.put(3, 3);
        System.out.println("BST #3");
        listOfD.printBSTLevels(listOfD.getDepthsLists(bst));

        System.out.println("\nIs this tree BST : " + obj.checkTree(bst));

        //now corrupt the tree
        BinarySearchTree.Node tmpLeft = bst.new Node(bst.root.key, bst.root.value);

        BinarySearchTree.Node tmpRoot = bst.new Node(bst.root.left.key, bst.root.left.value);
        tmpRoot.left = tmpLeft;
        tmpRoot.right = bst.root.right;

        tmpLeft.left = bst.root.left.left;
        tmpLeft.right = bst.root.left.right;

        bst.root = tmpRoot;
        System.out.println("BST #3 changed");
        listOfD.printBSTLevels(listOfD.getDepthsLists(bst));
        System.out.println("\nIs this tree BST : " + obj.checkTree(bst));
    }
}
