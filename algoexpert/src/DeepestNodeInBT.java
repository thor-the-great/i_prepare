import java.util.LinkedList;
import java.util.Queue;
import trees.TreeNode;

/**
 * Find the deepest node in a tree
 */
public class DeepestNodeInBT {

  TreeNode res;
  int d = 0;


  public TreeNode findDeepest(TreeNode root) {
    return iterative(root);
  }

  public TreeNode iterative(TreeNode root) {
      TreeNode cur = null;

      Queue<TreeNode> q = new LinkedList();
      if (root != null)
        q.add(root);

      while(!q.isEmpty()) {
        TreeNode n = q.poll();
        cur = n;
        if (n.left != null)
          q.add(n.left);

        if (n.right != null)
          q.add(n.right);
      }

      return cur;
  }

  public TreeNode recursive(TreeNode root) {
    if (root == null)
      return null;
    helper(root, 0);
    return res;
  }

  void helper(TreeNode n, int dd) {
    if (n == null)
      return;
    dd++;
    if (n.left == null && n.right == null) {
      if (dd >= d) {
        d = dd;
        res = n;
      }
      return;
    }
    if (n.left != null)
      helper(n.left, dd);
    if (n.right != null)
      helper(n.right, dd);
  }
}
