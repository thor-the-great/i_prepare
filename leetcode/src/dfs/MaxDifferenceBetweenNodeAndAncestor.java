package dfs;

import trees.TreeNode;

/**
 * 1026. Maximum Difference Between Node and Ancestor
 * Medium
 *
 * Given the root of a binary tree, find the maximum value V for which there exists different
 * nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
 *
 * (A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an
 * ancestor of B.)
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation:
 * We have various ancestor-node differences, some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 */
public class MaxDifferenceBetweenNodeAndAncestor {

  int res = 0;
  long mod = (1<<17);

  /**
   * Recursively check nodes, at each step check min/max from left and right child
   * and check diff with current node. Return to the top new min and max
   * @param root
   * @return
   */
  public int maxAncestorDiff(TreeNode root) {
    helper(root);
    return res;
  }

  long helper(TreeNode n) {
    //base case - return n.val as min and max
    if (n.left == null && n.right == null) {
      return (n.val<<17) + n.val;
    }
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    //get min/max from left child
    if (n.left != null) {
      long l = helper(n.left);
      min = Math.min(min, (int) (l % mod));
      max = Math.max(max, (int)(l>>17));
    }
    //and right child
    if (n.right != null) {
      long r = helper(n.right);
      min = Math.min(min, (int)(r % mod));
      max = Math.max(max, (int)(r>>17));
    }
    //update result if needed, possible new res if n.val - max or
    //n.val - min or old res
    res = Math.max(res, Math.max(n.val - min, max - n.val));
    //pass new max/min to the parent
    return (long)(Math.max(max, n.val) <<17) + Math.min(n.val, min);
  }
}
