package path.top_asked;

import diff_problems.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 337. House Robber III
 * Medium
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the
 * "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that
 * "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked
 * houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 *
 * Input: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 *
 */
public class HouseRobberIII {

    public int rob(TreeNode root) {
        return robDPUpDown(root);
    }

    public int robFastRecusion(TreeNode root) {
        int[] res = helperFastRec(root);
        return Math.max(res[0], res[1]);
    }

    int[] helperFastRec(TreeNode n) {
        if (n == null)
            return new int[2];
        //0 - if node robbed
        //1 - if root not robbed
        int[] left = helperFastRec(n.left);
        int[] right = helperFastRec(n.right);

        int[] ret = new int[2];

        //if node robbed - we can't sum it value. but we are free to choose children
        //robbed or not robbed, just take max of two
        ret[0] = Math.max(left[1], left[0]) + Math.max(right[1], right[0]);
        //if we not rob the root - sum up node value plus values of children if nodes are
        //not robbed
        ret[1] = n.val + left[0] + right[0];

        return ret;
    }

    Map<TreeNode, Integer> m;

    public int robDPUpDown(TreeNode root) {
        m = new HashMap();
        return helperDPUpDown(root);
    }

    int helperDPUpDown(TreeNode n) {
        if (n == null)
            return 0;
        if (m.containsKey(n))
            return m.get(n);
        int x = n.val;
        int y = 0;
        if(n.left != null) {
            x += helperDPUpDown(n.left.left) + helperDPUpDown(n.left.right);
            y += helperDPUpDown(n.left);
        }
        if (n.right != null) {
            x += helperDPUpDown(n.right.left) + helperDPUpDown(n.right.right);
            y += helperDPUpDown(n.right);
        }
        int res = Math.max(x, y);
        m.put(n, res);
        return res;
    }
}
