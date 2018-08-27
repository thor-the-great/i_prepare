package cracking.trees_graphs;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Martin G on 1/9/2017.
 */
public class MinimalTreeFromSortedArray {

    BinarySearchTree<Integer, Integer> minimalTreeFromArray(int[] array) {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.root = insertNode(array, 0, array.length - 1, bst);
        return bst;
    }

    BinarySearchTree.Node insertNode(int[] array, int start, int end, BinarySearchTree<Integer, Integer> bst) {
        if (start > end)
            return null;
        int index = (start + end)/ 2;
        BinarySearchTree.Node mid = bst.new Node(array[index], array[index]);
        mid.left = insertNode(array, start, index - 1, bst);
        if (mid.left != null && mid.left.parent == null)
            mid.left.parent = mid;
        mid.right = insertNode(array, index + 1, end, bst);
        if (mid.right != null && mid.right.parent == null)
            mid.right.parent = mid;
        return mid;
    }

    public static void main(String[] args) {
        int[] array = new int[] {4, 5, 2, 67, 23, 12, 25, 6, 7, 80, 3, 9, 50, 11, 22, 1, 15};
        Arrays.sort(array);
        MinimalTreeFromSortedArray obj = new MinimalTreeFromSortedArray();
        BinarySearchTree<Integer, Integer> bst = obj.minimalTreeFromArray(array);
        System.out.println("Resulted BST is " + bst);
    }
}
