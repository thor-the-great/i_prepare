package random_problems;

import diff_problems.TreeNode;

/**
 * 543. Diameter of Binary Tree
 * Easy
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is
 * the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class DiameterOfBinaryTree {

    int res = 0;

    /**
     * Idea - every path must pass the root of sub-tree. For every node count it's sub-tree path as sum of
     * right and left, and then pass path to the parent
     * catch: for parent path would be max between left and right because path can only go over over side or
     * another.
     * catch: for root need to calculate it's path separately
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        res = 0;
        res = Math.max(helper(root), res);
        return res;
    }

    int helper(TreeNode n) {
        if (n.left == null && n.right == null)
            return 0;

        int leftPath = 0;
        if (n.left != null)
            leftPath = 1 + helper(n.left);
        int rightPath = 0;
        if (n.right != null)
            rightPath = 1 + helper(n.right);
        //count max path of this sub-tree
        res = Math.max(res, leftPath + rightPath);
        //pass max between left and right so parent can count it to it's path
        return Math.max(leftPath, rightPath);
    }
}
