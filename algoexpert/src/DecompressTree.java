import java.util.LinkedList;
import java.util.Queue;
import trees.TreeNode;

/**
 * Full Tree Decompression New
 *     Trees Strings Queues
 * Given a String that represents a Binary Tree, write a method - decompressTree that
 * decompresses that tree (reconstructs the tree) and returns the root TreeNode. The compression
 * algorithm included traversing the tree level by level, from the left to the right. The
 * TreeNode's data values were appended to the String, delimited by commas. Also, null TreeNodes
 * were denoted by appending an asterisk - *. The input String denotes the structure of a Full
 * Binary Tree - i.e. a tree that is structurally balanced. However, the reconstructed tree may
 * not be a full tree as the String included * characters, which represent null TreeNodes.
 *
 * Note:
 * You can assume that if a Binary Tree contains k levels, the compressed String will contain
 * 2k-1 elements - either numbers or *.
 *
 * Examples:
 *
 * Compressed String : "1,2,3"
 * Output Tree:
 *      1
 *     / \
 *    2   3
 *
 * Compressed String : "1,2,3,4,*,6,*"
 * Output Tree:
 *      1
 *     / \
 *    2   3
 *   /   /
 *  4   6
 *
 * Compressed String : "1,*,2,*,*,*,3"
 * Output Tree:
 *      1
 *       \
 *        2
 *         \
 *          3
 */
public class DecompressTree {

  /**
   * Preorder traversal done via queue. So create a queue of tree nodes and iterate over the array
   * filling up nodes
   * @param str
   * @return
   */
  public TreeNode decompressTree(String str){
    if (str == null || str.length() == 0)
      return null;
    String[] vals = str.split(",");
    TreeNode root = "*".equals(vals[0]) ? null : new TreeNode(Integer.parseInt(vals[0]));
    int p = 1;
    Queue<TreeNode> q = new LinkedList();
    q.add(root);
    while (p < vals.length) {
      TreeNode n = q.poll();
      String next = vals[p++];
      TreeNode left = "*".equals(next) ? null : new TreeNode(Integer.parseInt(next));
      next = vals[p++];
      TreeNode right = "*".equals(next) ? null : new TreeNode(Integer.parseInt(next));
      n.left = left;
      n.right = right;
      if (left != null)
        q.add(left);
      if (right != null)
        q.add(right);
    }
    return root;
  }
}
