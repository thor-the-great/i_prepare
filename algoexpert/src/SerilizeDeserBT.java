import java.util.LinkedList;
import java.util.Queue;
import trees.TreeNode;

public class SerilizeDeserBT {

  public String serializeTree(TreeNode root){
    StringBuilder sb = new StringBuilder();
    Queue<TreeNode> q = new LinkedList();
    q.add(root);

    while(!q.isEmpty()) {
      TreeNode n = q.poll();
      if (n == null) {
        sb.append("*,");
        continue;
      }
      sb.append(n.val).append(",");
      q.add(n.left);
      q.add(n.right);
    }
    return sb.toString();
  }

  public TreeNode restoreTree(String str){
    if (str == null || str.length() == 0)
      return null;
    String[] vals = str.split(",");
    TreeNode root = parseNode(vals[0]);

    Queue<TreeNode> q = new LinkedList();
    q.add(root);
    int i = 1;
    while (i < vals.length) {
      TreeNode n = q.poll();
      if (n != null) {
        TreeNode left = parseNode(vals[i++]);
        n.left = left;
        if (left != null)
          q.add(left);
        TreeNode right = parseNode(vals[i++]);
        n.right = right;
        if (right != null)
          q.add(right);
      }
    }

    return root;
  }

  private TreeNode parseNode(String val) {
    return "*".equals(val) ? null : new TreeNode(Integer.parseInt(val));
  }
}
