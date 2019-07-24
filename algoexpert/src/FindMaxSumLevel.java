import java.util.LinkedList;
import java.util.Queue;
import trees.TreeNode;

/**
 * Find the Level that has the Maximum Sum New
 *     Trees Queues
 * Given a binary tree, write a method to return the level that has the maximum sum. In case the
 * tree is empty, return -1
 * Example:
 *        1
 *       / \
 *      2   3
 *     / \ / \
 *    4  5 6  7
 *   /
 *  8
 *
 * Output ==> 2
 * Note: Assume that root is at level 0.
 */
public class FindMaxSumLevel {

  /**
   * Do the level order traversal using 1 queue. Use nulls as delimiter between levels. 
   * @param root
   * @return
   */
  public int findMaxSumLevel(TreeNode root) {
    if (root == null)
      return -1;

    Queue<TreeNode> qNode = new LinkedList();
    qNode.add(root);
    qNode.add(null);
    int l = 0, lev = 0;
    int cur = 0, max = 0;
    while(!qNode.isEmpty()) {
      TreeNode n = qNode.poll();
      //end of this level
      if (n == null) {
        if (cur > max) {
          max = cur;
          lev = l;
        }
        cur = 0;
        l++;
        if (!qNode.isEmpty())
          qNode.add(null);
      } else {
        cur += n.val;
        if (n.left != null)
          qNode.add(n.left);
        if (n.right != null)
          qNode.add(n.right);
      }
    }

    return lev;
  }
}
