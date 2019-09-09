package trees;

/**
 * 538. Convert BST to Greater Tree
 * Easy
 *
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed
 * to the original key plus sum of all keys greater than the original key in BST.
 *
 * Example:
 *
 * Input: The root of a Binary Search Tree like this:
 *               5
 *             /   \
 *            2     13
 *
 * Output: The root of a Greater Tree like this:
 *              18
 *             /   \
 *           20     13
 */
public class ConvertBSTToGreaterTree {

    int sum = 0;

    /**
     * We can get correct sum if we do reversed inorder traversal - visit right child node, then node and then visit
     * left child. When we visit the node we accumulate the sum and use it on next step. To make code cleaner we can
     * do it in recursive way.
     * Catch is to add node value to the sum before we changed it.
     *
     * O(n) time because we need to visit every node. O(lgn) for space - because we do recursion and space required
     * for the call stack, max is the height of the tree.
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        //base case
        if (root == null)
            return null;
        //do the inversive inorder traversal - check right, center and left nodes
        convertBST(root.right);

        //increase this node by accumulated sum so far, then add this node to the sum
        int t = root.val;
        root.val+=sum;
        sum += t;

        convertBST(root.left);
        return root;
    }
}
