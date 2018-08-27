package cracking.trees_graphs;

/**
 * Created by Martin G on 1/11/2017.
 */
public class CheckSubtree {

    boolean isSubtree(BinarySearchTree<Integer, Integer> large, BinarySearchTree<Integer, Integer> small) {
        //return processUsingPreorderTraversal(large, small);
        return processUsingRecursiveSearch(large, small);
    }

    private boolean processUsingRecursiveSearch(BinarySearchTree<Integer, Integer> large, final BinarySearchTree<Integer, Integer> small) {
        if (small == null)
            return true;
        return matchTrees(large.root, small.root);
    }

    boolean matchTrees(BinarySearchTree.Node bigNode, final BinarySearchTree.Node smallNode) {
        if (bigNode == null)
            return false;
        else if (bigNode.value.equals(smallNode.value) && matchSubtreesAfterRoot(bigNode, smallNode)) {
            return true;
        }
        return matchTrees(bigNode.left, smallNode) || matchTrees(bigNode.right, smallNode);
    }

    boolean matchSubtreesAfterRoot(BinarySearchTree.Node bigNode, final BinarySearchTree.Node smallNode) {
        if (bigNode == null && smallNode == null) {
            return true;
        } else if (bigNode == null || smallNode == null) {
            return false;
        } else if (!bigNode.value.equals(smallNode.value)) {
            return false;
        } else {
            return matchSubtreesAfterRoot(bigNode.left, smallNode.left) && matchSubtreesAfterRoot(bigNode.right, smallNode.right);
        }
    }

    private boolean processUsingPreorderTraversal(BinarySearchTree<Integer, Integer> large, BinarySearchTree<Integer, Integer> small) {
        StringBuilder preOrderStringLarge = getPreOrderTraversal(large);
        StringBuilder preOrderStringSmall = getPreOrderTraversal(small);
        System.out.println("Large pre-order: " + preOrderStringLarge.toString());
        System.out.println("Small pre-order: " + preOrderStringSmall.toString());
        return preOrderStringLarge.toString().contains(preOrderStringSmall.toString());
    }

    StringBuilder getPreOrderTraversal(BinarySearchTree<Integer, Integer> bst) {
        StringBuilder sb = new StringBuilder();
        traverse(bst.root, sb);
        return  sb;
    }

    void traverse(BinarySearchTree.Node node, StringBuilder sb) {
        writeValue(node, sb);
        if (node != null) {
            traverse(node.left, sb);
            traverse(node.right, sb);
        }
    }

    void writeValue(BinarySearchTree.Node node, StringBuilder sb) {
        if (node != null)
            sb.append(node.value).append(" ");
        else
            sb.append("X").append(" ");
    }

    public static void main(String[] args) {
        CheckSubtree obj = new CheckSubtree();
        BinarySearchTree<Integer, Integer> largeBst = null;
        BinarySearchTree<Integer, Integer> smallBst = null;

        largeBst = new BinarySearchTree(new Integer[] {7, 2, 8, 1, 3});
        smallBst = new BinarySearchTree(new Integer[] { 2, 1, 3});
        System.out.println("Is sub-tree " + obj.isSubtree(largeBst, smallBst));

        largeBst = new BinarySearchTree(new Integer[] {7, 2, 8, 1, 3});
        smallBst = new BinarySearchTree(new Integer[] { 7, 1, 3});
        System.out.println("Is sub-tree " + obj.isSubtree(largeBst, smallBst));

        largeBst = new BinarySearchTree(new Integer[] {1, 1, 1, 1, 1});
        smallBst = new BinarySearchTree(new Integer[] { 1, 1 });
        System.out.println("Is sub-tree " + obj.isSubtree(largeBst, smallBst));

        largeBst = new BinarySearchTree(new Integer[] {1, 1, 1, 1, 1});
        smallBst = new BinarySearchTree(new Integer[] { 1, 1 });
        smallBst.root.left = smallBst.new Node(1, 1);
        System.out.println("Is sub-tree " + obj.isSubtree(largeBst, smallBst));
    }
}
