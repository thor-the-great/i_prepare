package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 637. Average of Levels in Binary Tree
 * Easy
 *
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form
 * of an array.
 * Example 1:
 * Input:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: [3, 14.5, 11]
 * Explanation:
 * The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence
 * return [3, 14.5, 11].
 * Note:
 * The range of node's value is in the range of 32-bit signed integer.
 */
public class AverageOfLevelsInBinaryTree {

  /**
   * Do BFS via queue, for each level accumulate sum and then get the average
   * @param root
   * @return
   */
  public List<Double> averageOfLevels(TreeNode root) {
    List<Double> res = new ArrayList();
    Queue<TreeNode> queue = new LinkedList();
    if (root != null)
      queue.add(root);
    while(!queue.isEmpty()) {
      int size = queue.size();
      long sum = 0;
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        sum += node.val;
        if (node.left != null)
          queue.add(node.left);
        if(node.right != null)
          queue.add(node.right);
      }
      res.add((double) sum/size);
    }
    return res;
  }
}
