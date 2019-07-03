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
        if (root == null)
            return 0;
        res = Integer.MIN_VALUE;
        res = Math.max(helper(root), res);
        return res;
    }

    int helper(TreeNode n) {
        if (n.left == null && n.right == null) {
            res = Math.max(res, n.val);
            return n.val;
        }

        int leftPath = 0;
        if (n.left != null)
            leftPath = helper(n.left);
        int rightPath = 0;
        if (n.right != null)
            rightPath = helper(n.right);
        //count max path of this sub-tree
        //this one we'll return to the parent - it doesn't include this node + right + left
        int thisNodeMax = Math.max(n.val, Math.max(n.val + leftPath, n.val + rightPath));
        //this one only possible for this sub-tree, we can't return it to the parent
        int thisNodeMaxExt = Math.max( n.val + leftPath + rightPath, thisNodeMax);
        res = Math.max(res, thisNodeMaxExt);

        return thisNodeMax;
    }
}
