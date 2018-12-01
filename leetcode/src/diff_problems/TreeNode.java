package diff_problems;

  public class TreeNode {
      public int val;
      public TreeNode left;
      public TreeNode right;
      public TreeNode parent;
      public TreeNode(int x) { val = x; }
      public TreeNode(int x, TreeNode parent) { val = x; this.parent = parent;}
      public TreeNode(int x, TreeNode left, TreeNode right) { val = x; this.left = left; this.right = right; }

      @Override
      public String toString() {
          return Integer.toString(this.val);
      }
  }
