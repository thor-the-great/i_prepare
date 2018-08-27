package cracking.trees_graphs;

import java.util.Arrays;

public class MinBSTFromSortedArray {

    BinarySearchTree.Node getBST(int[] arr) {
        BinarySearchTree.Node root = createBST(arr, 0, arr.length - 1);
        return root;
    }

    BinarySearchTree.Node createBST(int[] arr, int l, int r) {
        if (l > r)
            return null;
        int m = (r + l)/2;
        BinarySearchTree.Node node = new BinarySearchTree().new Node(arr[m], arr[m]);
        BinarySearchTree.Node leftChild = createBST(arr, l, m -1);
        if (leftChild != null)
            node.left = leftChild;
        BinarySearchTree.Node rightChild = createBST(arr, m + 1, r);
        if (rightChild != null)
            node.right = rightChild;
        return node;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2, 4, 60, 32, 1, 6, 78, 45, 31, 8, 10, 25, 7, 87, 86};
        Arrays.sort(arr);
        MinBSTFromSortedArray obj = new MinBSTFromSortedArray();
        BinarySearchTree.Node bstRoot = obj.getBST(arr);
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.root = bstRoot;
        System.out.println(bst);
    }
}
