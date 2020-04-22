package trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 662. Maximum Width of Binary Tree
 * Medium
 *
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of
 * a tree is the maximum width among all levels. The binary tree has the same structure as a full
 * binary tree, but some nodes are null.
 *
 * The width of one level is defined as the length between the end-nodes (the leftmost and right
 * most non-null nodes in the level, where the null nodes between the end-nodes are also counted
 * into the length calculation.
 *
 * Example 1:
 *
 * Input:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 * Example 2:
 *
 * Input:
 *
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 *
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 * Example 3:
 *
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 *
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 * Example 4:
 *
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * Output: 8
 * Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,
 * null,null,null,null,7).
 *
 *
 * Note: Answer will in the range of 32-bit signed integer.
 */
public class MaximumWidthOfBinaryTree {

  /**
   * Main point - this tree is a full tree, meaning that every node should have at least one
   * child.
   * Second idea - if we put tree to array then for node with index x the left child will be
   * in 2*x and right child will be at 2*x + 1.
   * So we do level-order traversal and collect the index numbers. Then for children we store
   * calculated numbers in the map (node->index).
   *
   * O(n) time - check every node once
   * O(n) space - for map and queue we need extra space
   *
   * @param root
   * @return
   */
  public int widthOfBinaryTree(TreeNode root) {
    if (root == null)
      return 0;

    Queue<TreeNode> q = new LinkedList();
    q.add(root);
    Map<TreeNode, Integer> map = new HashMap();
    map.put(root, 1);
    int max = 0;
    while(!q.isEmpty()) {
      int size = q.size(), start = 0, end = 0;
      for (int i = 0; i < size; i++) {
        TreeNode n = q.poll();
        if (i == 0) {
          start = map.get(n);
        }
        if (i == size - 1) {
          end = map.get(n);
        }
        if (n.left != null) {
          q.add(n.left);
          map.put(n.left, 2*map.get(n));
        }
        if (n.right != null) {
          q.add(n.right);
          map.put(n.right, 2*map.get(n) + 1);
        }
      }
      max = Math.max(max, end - start + 1);
    }
    return max;
  }
}
