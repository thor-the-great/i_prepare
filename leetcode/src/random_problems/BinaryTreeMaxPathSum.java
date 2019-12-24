package random_problems;

import trees.TreeNode;

/**
 * 124. Binary Tree Maximum Path Sum
 * Hard
 *
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along
 * the parent-child connections. The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 * Example 2:
 *
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 */
public class BinaryTreeMaxPathSum {

    int res = Integer.MIN_VALUE;

    /**
     * idea - similar to longest path (diameter) but we need to count more cases.
     * For every node max can be one of:
     * this node value or this node + left or this node + right, or this node + left + right
     * we can only pass to the parent max of this node value or this node + left or this node + right
     * catch: for the leaf we need to check for the max as well, node itself can be a path
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        res = Integer.MIN_VALUE;
        return Math.max(helper(root), res);
    }

    int helper(TreeNode n) {
        if (n == null)
            return 0;
        //left and right paths - we can take part if it's greater than 0 or leave it otherwise
        int l = Math.max(helper(n.left), 0);
        int r = Math.max(helper(n.right), 0);
        res = Math.max(res, l + r + n.val);
        return Math.max(l, r) + n.val;
    }
}
