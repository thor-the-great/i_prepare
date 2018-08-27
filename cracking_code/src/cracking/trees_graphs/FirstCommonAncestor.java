package cracking.trees_graphs;

import java.util.Arrays;

/**
 * Created by Martin G on 1/11/2017.
 */
public class FirstCommonAncestor {

    BinarySearchTree.Node findCommonAncestor(BinarySearchTree.Node node1, BinarySearchTree.Node node2, BinarySearchTree.Node root) {
        //if at least one node is root then ancestor is the root
        if (root == node1 || root == node2)
            return root;

        //need to check if both nodes are on the same subtree or not. If not then root is the ancestor
        boolean isNode1InLeftSubtree = isInSubtree(root.left, node1);
        boolean isNode2InLeftSubtree = isInSubtree(root.left, node2);

        if (isNode1InLeftSubtree != isNode2InLeftSubtree) {
            return root;
        }
        else {
            BinarySearchTree.Node childRoot = isNode1InLeftSubtree ? root.left : root.right;
            return findCommonAncestor(node1, node2, childRoot);
        }
    }

    boolean isInSubtree(BinarySearchTree.Node root, BinarySearchTree.Node node) {
        if (root == null)
            return false;
        if (root == node)
            return true;
        return isInSubtree(root.left, node) || isInSubtree(root.right, node);
    }

    public static void main(String[] args) {
        int[] array = new int[] {4, 5, 2, 67, 23, 12, 25, 6, 7, 80, 3, 9, 50, 11, 22, 1, 15};
        Arrays.sort(array);
        MinimalTreeFromSortedArray minimalTreeBuilder = new MinimalTreeFromSortedArray();
        BinarySearchTree<Integer, Integer> bst = minimalTreeBuilder.minimalTreeFromArray(array);
        System.out.println("BST #1 ");
        ListOfDepths listOfD = new ListOfDepths();
        listOfD.printBSTLevels(listOfD.getDepthsLists(bst));

        BinarySearchTree.Node node1 = null;
        BinarySearchTree.Node node2 = null;
        node1 = bst.getNode(2);
        node2 = bst.getNode(7);
        FirstCommonAncestor obj = new FirstCommonAncestor();
        System.out.println("\nFor nodes " + node1 + " and "+node2 + " common ancestor is " + obj.findCommonAncestor(node1, node2, bst.root));

        node1 = bst.getNode(1);
        node2 = bst.getNode(15);
        System.out.println("\nFor nodes " + node1 + " and "+node2 + " common ancestor is " + obj.findCommonAncestor(node1, node2, bst.root));

        node1 = bst.getNode(7);
        node2 = bst.getNode(9);
        System.out.println("\nFor nodes " + node1 + " and "+node2 + " common ancestor is " + obj.findCommonAncestor(node1, node2, bst.root));
    }
}
