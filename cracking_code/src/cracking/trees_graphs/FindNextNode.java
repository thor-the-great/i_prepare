package cracking.trees_graphs;

import java.util.Arrays;

/**
 * Created by Martin G on 1/11/2017.
 */
public class FindNextNode {

    BinarySearchTree.Node getNextNodeInOrder(BinarySearchTree.Node node) {
        if (node == null)
            return null;
        //first look for the most left child
        if (node.right != null)
            return getMostLeftNode(node.right);
        BinarySearchTree.Node parentTmp = node.parent;
        BinarySearchTree.Node tmpNode = node;
        while (parentTmp != null && tmpNode == parentTmp.right) {
            tmpNode = parentTmp;
            parentTmp = parentTmp.parent;
        }
        return parentTmp;
    }

    BinarySearchTree.Node getMostLeftNode(BinarySearchTree.Node node) {
        if (node == null)
            return null;
        BinarySearchTree.Node tmpNode = node;
        while( tmpNode.left != null) {
            tmpNode = tmpNode.left;
        }
        return tmpNode;
    }

    public static void main(String[] args) {
        int[] array = new int[] {4, 5, 2, 67, 23, 12, 25, 6, 7, 80, 3, 9, 50, 11, 22, 1, 15};
        Arrays.sort(array);
        MinimalTreeFromSortedArray minimalTreeBuilder = new MinimalTreeFromSortedArray();
        BinarySearchTree<Integer, Integer> bst = minimalTreeBuilder.minimalTreeFromArray(array);
        System.out.println("BST #1 ");
        ListOfDepths listOfD = new ListOfDepths();
        listOfD.printBSTLevels(listOfD.getDepthsLists(bst));

        FindNextNode obj = new FindNextNode();
        BinarySearchTree.Node nodeToWork = null;
        nodeToWork = bst.root;
        System.out.println("\nFor node " + nodeToWork + " next is " + obj.getNextNodeInOrder(nodeToWork));

        nodeToWork = bst.root.right.right.right;
        System.out.println("\nFor node " + nodeToWork + " next is " + obj.getNextNodeInOrder(nodeToWork));

        nodeToWork = bst.root.left.left;
        System.out.println("\nFor node " + nodeToWork + " next is " + obj.getNextNodeInOrder(nodeToWork));

        nodeToWork = bst.root.left.left.left;
        System.out.println("\nFor node " + nodeToWork + " next is " + obj.getNextNodeInOrder(nodeToWork));

        nodeToWork = bst.root.left;
        System.out.println("\nFor node " + nodeToWork + " next is " + obj.getNextNodeInOrder(nodeToWork));
    }
}


