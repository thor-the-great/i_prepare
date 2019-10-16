import java.util.ArrayList;
import trees.TreeNode;

public class BSTNodesInRange {

  public ArrayList<Integer> rangeList = new ArrayList<Integer>();

  public void printRange(TreeNode root, int a, int b) {
    if (root == null || a > b)
      return;

    printRange(root.left, a, root.val);

    if (root.val >= a && root.val <= b) {
      rangeList.add(root.val);
    }

    printRange(root.right, root.val, b);
  }

  public static void main(String[] args) {
    BSTNodesInRange obj = new BSTNodesInRange();
    TreeNode root = new TreeNode(5,
        new TreeNode(3,
            new TreeNode(2),
            new TreeNode(4)),
        new TreeNode(8,
            new TreeNode(7),
            new TreeNode(10)));

    obj.printRange(root, 4, 8);
    System.out.println(obj.rangeList);
  }
}
