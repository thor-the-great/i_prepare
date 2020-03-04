package grooking_coding_patterns.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
};

/**
 * Tree Boundary (hard) #
 * Given a binary tree, return an array containing all the boundary nodes of the tree in an
 * anti-clockwise direction.
 *
 * The boundary of a tree contains all nodes in the left view, all leaves, and all nodes in the
 * right view. Please note that there should not be any duplicate nodes. For example, the root is
 * only included in the left view; similarly, if a level has only one node we should include it
 * in the left view.
 *
 *  Example 1
 *     1
 *     2
 *     3
 *     4
 *     5
 *     8
 *     9
 *     10
 *     6
 *     7
 *     11
 *     12
 *     13
 *  Tree boundary: [1, 2, 4, 8, 9, 10, 11, 12,13, 7, 3]
 *  Exampe 2
 *     12
 *     7
 *     1
 *     4
 *     3
 *     9
 *     15
 *     10
 *     5
 *     6
 *  Tree boundary: [12, 7, 4, 9, 15, 10, 6, 5, 1]
 *  Exampe 3
 *     12
 *     1
 *     10
 *     5
 *  Tree boundary: [12, 1, 10, 5]
 *  Please note that whenever we've only one node on a level, we include it in the left view.
 */
public class TreeBoundary {

  /**
   * Do in segments - do the left border, then all leafs using dfs and after that - right border
   * @param root
   * @return
   */
  public static List<TreeNode> findBoundary(TreeNode root) {
    List<TreeNode>result = new ArrayList<>();
    if (root == null)
      return result;

    //adding left border starting from root
    TreeNode n = root;
    while(n != null) {
      result.add(n);
      n = (n.left != null) ? n.left : n.right;
    }
    result.remove(result.size() - 1);
    //now adding all leafs using dfs
    Stack<TreeNode> stack = new Stack();
    stack.push(root);
    while(!stack.isEmpty()) {
      n = stack.pop();
      if (n.left == null && n.right == null)
        result.add(n);
      if (n.right != null)
        stack.push(n.right);

      if(n.left != null)
        stack.push(n.left);
    }
    //now adding all right border excluding last leaf and root
    stack = new Stack();
    n = root.right;
    while(n != null) {
      stack.push(n);
      n = (n.right != null) ? n.right : n.left;
    }
    if (!stack.isEmpty())
      stack.pop();

    while(!stack.isEmpty()) {
      result.add(stack.pop());
    }
    return result;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(4);
    root.left.left.left = new TreeNode(9);
    root.left.right = new TreeNode(3);
    root.left.right.left = new TreeNode(15);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    root.right.right.left = new TreeNode(6);
    List<TreeNode> result = TreeBoundary.findBoundary(root);
    for (TreeNode node : result) {
      System.out.print(node.val + " ");
    }
  }
}

