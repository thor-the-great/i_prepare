package grooking_coding_patterns.trees;

/**
 * Problem Challenge 1
 * We'll cover the following
 * Tree Diameter (medium)
 * Try it yourself
 * Tree Diameter (medium) #
 * Given a binary tree, find the length of its diameter. The diameter of a tree is the number of
 * nodes on the longest path between any two leaf nodes. The diameter of a tree may or may not
 * pass through the root.
 *
 * Note: You can always assume that there are at least two leaf nodes in the given tree.
 *
 *  Example 1:
 *     1
 *     2
 *     3
 *     4
 *     5
 *     6
 *  Output: 5Explaination: The diameter of the tree is: [4, 2, 1, 3, 6]
 *  Example 2:
 *     1
 *     2
 *     3
 *     5
 *     6
 *     7
 *     8
 *     10
 *     9
 *     11
 */
public class TreeDiameter {

  static int res = 0;

  /**
   * similar to greatest path, just simpler
   * @param root
   * @return
   */
  public static int findDiameter(TreeNode root) {
    return Math.max(dfs(root), res);
  }

  static int dfs(TreeNode n) {
    if (n == null)
      return 0;

    int l = dfs(n.left), r = dfs(n.right);
    res = Math.max(1 + l + r, res);

    return 1 + Math.max(l, r);
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.right.left = new TreeNode(5);
    root.right.right = new TreeNode(6);
    System.out.println("Tree Diameter: " + TreeDiameter.findDiameter(root));
    root.left.left = null;
    root.right.left.left = new TreeNode(7);
    root.right.left.right = new TreeNode(8);
    root.right.right.left = new TreeNode(9);
    root.right.left.right.left = new TreeNode(10);
    root.right.right.left.left = new TreeNode(11);
    System.out.println("Tree Diameter: " + TreeDiameter.findDiameter(root));
  }
}
