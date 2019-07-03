package path.google;

import trees.TreeNode;

/**
 * Longest Univalue Path
 * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path
 * may or may not pass through the root.
 *
 * Note: The length of path between two nodes is represented by the number of edges between them. *
 * Example 1:
 * Input:
 *
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 * Output:
 * 2
 * Example 2:
 * Input:
 *
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * Output:
 * 2
 * Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 */
public class LongestUnivaluePath {

    int max = 0;
    public int longestUnivaluePath(TreeNode root) {
        max = 0;
        helper(root);
        return max;
    }

    int helper(TreeNode n) {
        if (n == null) {
            return 0;
        }
        int leftLength = helper(n.left);
        int leftPath = 0;
        if (n.left != null && n.left.val == n.val) {
            leftPath += leftLength + 1;
        }
        int rightLength = helper(n.right);
        int rightPath = 0;
        if (n.right != null && n.right.val == n.val) {
            rightPath += rightLength + 1;
        }
        max = Math.max(max, leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }
}
