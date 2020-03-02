package trees;

/**
 * 669. Trim a Binary Search Tree
 * Easy
 *
 * 1475
 *
 * 151
 *
 * Add to List
 *
 * Share
 * Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so
 * that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree,
 * so the result should return the new root of the trimmed binary search tree.
 *
 * Example 1:
 * Input:
 *     1
 *    / \
 *   0   2
 *
 *   L = 1
 *   R = 2
 *
 * Output:
 *     1
 *       \
 *        2
 * Example 2:
 * Input:
 *     3
 *    / \
 *   0   4
 *    \
 *     2
 *    /
 *   1
 *
 *   L = 1
 *   R = 3
 *
 * Output:
 *       3
 *      /
 *    2
 *   /
 *  1
 */
public class TrimBST {

  /**
   * Recursive approach, if value is out of bound - don't add it to result but keep traversing,
   * just advance on one of the branches
   * If value is within boundaries - return the node itself with both children. For children do
   * recursive call first
   * @param root
   * @param L
   * @param R
   * @return
   */
  public TreeNode trimBST(TreeNode root, int L, int R) {
    if (root == null)
      return null;
    if (root.val < L) {
      return trimBST(root.right, L, R);
    } else if (root.val > R) {
      return trimBST(root.left, L, R);
    }
    root.left = trimBST(root.left, L, R);
    root.right = trimBST(root.right, L, R);
    return root;
  }
}
