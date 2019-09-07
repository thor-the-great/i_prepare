package random_problems;

import java.util.ArrayList;
import java.util.List;
import trees.TreeNode;

/**
 * 1161. Maximum Level Sum of a Binary Tree
 * Medium
 *
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2,
 * and so on.
 *
 * Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 *
 *
 * Note:
 *
 * The number of nodes in the given tree is between 1 and 10^4.
 * -10^5 <= node.val <= 10^5
 */

public class BTLevelMaxSum {

  /**
   * Traverse level-based recursively, keep sum of each level in the arraylist. Then compare sums
   * starting from the lowest node
   * @param root
   * @return
   */
  public int maxLevelSum(TreeNode root) {
    if ( root == null)
      return 0;
    //list to store level sums
    List<Integer> levels = new ArrayList();
    //start recursion
    helper(root, 1, levels);
    //init maxes for iterations
    int max = Integer.MIN_VALUE, maxL = 0;
    //find max sum starting from the lowest level. If same sum
    //met several times we'll keep one with @ smallest level
    int N = levels.size() - 1;
    for (int i = N; i >= 0; i--) {
      if (levels.get(i) >= max) {
        max = levels.get(i);
        maxL = i + 1;
      }
    }
    return maxL;
  }

  void helper(TreeNode n, int l, List<Integer> levels) {
    //if we haven't met this level before - add it's sum as 0
    if (levels.size() < l )
      levels.add(0);
    //update sum with current node values
    levels.set(l - 1, levels.get(l - 1) + n.val);
    //recurse to left and right sub-trees. Increase level number
    if (n.left != null)
      helper(n.left, l + 1, levels);
    if (n.right != null)
      helper(n.right, l + 1, levels);
  }
}
