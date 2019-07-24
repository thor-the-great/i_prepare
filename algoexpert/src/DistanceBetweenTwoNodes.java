import trees.TreeNode;

/**
 * Distance between two nodes in a Binary Tree New
 *     Trees Strings Queues
 *
 *   Your solution is incorrect. Click me for details.
 * Given the root of a Binary Tree  and 2 integers that represent the data values of any two
 * TreeNodes present in the tree, write a method - getNodeDistance that returns the distance
 * between the nodes. You can assume that the given keys exist in the tree. The distance between
 * two nodes is defined as the minimum number of edges that must be traversed to travel between
 * the two nodes.
 *
 * Example:
 *        1
 *       / \
 *      2   3
 *       \   \
 *        4   5
 *
 * getNodeDistance(2,5) => 3
 */
public class DistanceBetweenTwoNodes {

  /**
   *
   * @param root
   * @param n1
   * @param n2
   * @return
   */
  public int getNodeDistance(TreeNode root, int n1, int n2) {
    int lenRoot1 = getPath(root, n1);
    int lenRoot2 = getPath(root, n2);
    int lenRootLCA = getPath(root, lca(root, n1, n2).val);

    return lenRoot1 + lenRoot2 - (2*lenRootLCA);
  }

  TreeNode lca(TreeNode root, int n1, int n2) {
    if (root == null)
      return null;

    if (root.val == n1 || root.val == n2) {
      return root;
    }

    TreeNode left = lca(root.left, n1, n2);
    TreeNode right = lca(root.right, n1, n2);

    if (left != null && right != null)
      return root;

    return left == null ? right : left;
  }

  int getPath(TreeNode root, int n) {
    if (root == null)
      return -1;

    if (root.val == n)
      return 0;

    int left = getPath(root.left, n);
    int right = getPath(root.right, n);
    if (left == -1 && right == - 1)
      return -1;
    else
      return (left == -1 ? right : left) + 1;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1,
        new TreeNode(2,
            new TreeNode(4),
            new TreeNode(5,
                new TreeNode(8),
                null)),
        new TreeNode(3,
            new TreeNode(6,
                new TreeNode(9),
                null),
            new TreeNode(7,
                new TreeNode(10),
                null)));
    DistanceBetweenTwoNodes obj = new DistanceBetweenTwoNodes();
    System.out.println(obj.getNodeDistance(root, 1, 9));
  }
}
