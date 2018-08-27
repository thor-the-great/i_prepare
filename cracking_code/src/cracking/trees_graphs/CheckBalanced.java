package cracking.trees_graphs;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Martin G on 1/10/2017.
 */
public class CheckBalanced {

    boolean isBalancedTree(BinarySearchTree<Integer, Integer> bst) {
        int height = checkHeight(bst.root);
        return height != -1;
    }

    int checkHeight(BinarySearchTree.Node root) {
        if (root == null) {
            return 0;
        }

        int leftH = checkHeight(root.left);
        if (leftH == -1)
            return -1;

        int rigthH = checkHeight(root.right);
        if (rigthH == -1)
            return -1;

        int hDiff = Math.abs(leftH - rigthH);
        if (hDiff > 1) {
            return -1;
        }
        else {
            return Math.max(leftH, rigthH) + 1;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {4, 5, 2, 67, 23, 12, 25, 6, 7, 80, 3, 9, 50, 11, 22, 1, 15};
        Arrays.sort(array);
        MinimalTreeFromSortedArray minimalTreeBuilder = new MinimalTreeFromSortedArray();
        BinarySearchTree<Integer, Integer> bst = minimalTreeBuilder.minimalTreeFromArray(array);
        System.out.println("BST #1 " + bst);
        CheckBalanced balancedCheck = new CheckBalanced();
        System.out.println("BST is balanced " + balancedCheck.isBalancedTree(bst));

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
        System.out.println("BST #2 " + bst);
        ListOfDepths obj = new ListOfDepths();
        List<List<BinarySearchTree.Node>> depths = obj.getDepthsLists(bst);
        obj.printBSTLevels(depths);
        System.out.println("BST is balanced " + balancedCheck.isBalancedTree(bst));

    }
}
