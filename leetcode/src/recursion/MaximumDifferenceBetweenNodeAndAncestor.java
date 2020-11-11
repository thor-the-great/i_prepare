package recursion;

import trees.TreeNode;

/**
 *  Maximum Difference Between Node and Ancestor
 *
 * Solution
 * Given the root of a binary tree, find the maximum value V for which there exist different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
 *
 * A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation: We have various ancestor-node differences, some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 * Example 2:
 *
 *
 * Input: root = [1,null,2,null,0,3]
 * Output: 3
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 5000].
 * 0 <= Node.val <= 105
 *
 * https://leetcode.com/problems/maximum-difference-between-node-and-ancestor
 */
public class MaximumDifferenceBetweenNodeAndAncestor {

    int res = 0;

    /**
     * Answer depends on min and max of each subtree, count them as we go.
     * O(n) time
     * O(n) space
     * @param root
     * @return
     */
    public int maxAncestorDiff(TreeNode root) {
        helper(root, root.val, root.val);
        return res;
    }

    void helper(TreeNode n, int curMin, int curMax) {
        if (n == null)
            return;

        res = Math.max(res, Math.max(
                Math.abs(curMin - n.val), Math.abs(curMax - n.val)));
        int newMin = Math.min(n.val, curMin), newMax =  Math.max(n.val, curMax);
        helper(n.left, newMin, newMax);
        helper(n.right, newMin, newMax);
    }
}
