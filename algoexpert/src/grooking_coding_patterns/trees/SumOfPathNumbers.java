package grooking_coding_patterns.trees;

/**
 * Sum of Path Numbers (medium)
 * We'll cover the following
 * Problem Statement
 * Try it yourself
 * Solution
 * Code
 * Time complexity
 * Space complexity
 * Problem Statement #
 * Given a binary tree where each node can only have a digit (0-9) value, each root-to-leaf path
 * will represent a number. Find the total sum of all the numbers represented by all paths.
 *
 *  Example 1:
 *  Output: 408 Explaination: The sume of all path numbers: 17 + 192 + 199
 *     1
 *     7
 *     9
 *     2
 *     9
 *  Example 2:
 *     1
 *     0
 *     1
 *     1
 *     6
 *     5
 */
public class SumOfPathNumbers {

  /**
   * do dfs
   * @param root
   * @return
   */
  public static int findSumOfPathNumbers(TreeNode root) {
    return dfs(0, root);
  }

  static int dfs(int curNum, TreeNode n) {
    if (n == null)
      return 0;

    curNum = (curNum*10) + n.val;
    if (n.left == null && n.right == null)
      return curNum;

    return dfs(curNum, n.left) + dfs(curNum, n.right);
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(0);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(1);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(5);
    System.out.println("Total Sum of Path Numbers: " + SumOfPathNumbers.findSumOfPathNumbers(root));
  }
}

