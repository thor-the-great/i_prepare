package grooking_coding_patterns.trees;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Count Paths for a Sum (medium)
 * We'll cover the following
 * Problem Statement
 * Try it yourself
 * Solution
 * Code
 * Time complexity
 * Space complexity
 * Problem Statement #
 * Given a binary tree and a number ‘S’, find all paths in the tree such that the sum of all the
 * node values of each path equals ‘S’. Please note that the paths can start or end at any node
 * but all paths must follow direction from parent to child (top to bottom).
 *
 *  Example 1:
 *     1
 *     7
 *     9
 *     6
 *     5
 *     2
 *     3
 *  S: 12Output: 3Explaination: There are three paths with sum '12':7 -> 5, 1 -> 9 -> 2, and 9 ->
 *  3
 *  Example 2:
 *     12
 *     7
 *     1
 *     4
 *     10
 *     5
 */
public class CountAllPathSum {

  /**
   * iterate top-down in DFS manner with the running list of values, at each node
   * check values accumulated so far if we have sum == S
   *
   * O(n^2) time,
   * O(n) space
   *
   * @param root
   * @param S
   * @return
   */
  public static int countPaths(TreeNode root, int S) {
    return helper(root, new LinkedList(), S);
  }

  static int helper(TreeNode n, LinkedList cur, int S) {
    if (n == null) {
      return 0;
    }
    cur.add(n.val);
    ListIterator<Integer> it = cur.listIterator(cur.size());
    int sum = 0, res = 0;
    while(it.hasPrevious()) {
      sum+=it.previous();
      if (sum == S)
        ++res;
    }
    res += helper(n.left, cur, S) + helper(n.right, cur, S);
    cur.remove(cur.size() - 1);
    return res;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(4);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    System.out.println("Tree has path: " + CountAllPathSum.countPaths(root, 11));
  }
}

