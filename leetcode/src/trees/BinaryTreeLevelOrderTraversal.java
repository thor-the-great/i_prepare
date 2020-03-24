package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. Binary Tree Level Order Traversal
 * Medium
 *
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to
 * right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class BinaryTreeLevelOrderTraversal {

  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList();
    if (root == null)
      return res;

    Queue<TreeNode> q = new LinkedList();
    q.add(root);

    while (!q.isEmpty()) {
      List<Integer> levelList = new ArrayList();
      int size = q.size();
      for (int i = 0; i < size; i++) {
        TreeNode n = q.poll();
        levelList.add(n.val);

        if (n.left != null) q.add(n.left);
        if (n.right != null) q.add(n.right);
      }
      res.add(levelList);
    }

    return res;
  }
}
