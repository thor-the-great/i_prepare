import trees.TreeNode;

/**
 * Delete Node from the BST keeping the BST property
 */
public class DeleteNodeFromBST {

  /**
   * Handle four cases:
   * - no children - just delete the node.
   * - one child - return another non-null child instead of current
   * - both children - this is tricky. From the right sub-tree get the left most child. Replace the
   * value of current node by that leftmost child value. Delete that leftmost child.
   * @param root
   * @param data
   * @return
   */
  public TreeNode delete(TreeNode root, int data) {
    if (root == null)
      return null;

    if (root.val == data) {
      if (root.left == null && root.right == null)
        return null;

      if (root.left == null || root.right == null) {
        if (root.left == null)
          return root.right;
        else
          return root.left;
      }
      //this is the 4-th case
      //find that smallest - leftmost in the right sub-tree
      TreeNode smallest = root.right;
      while(smallest.left != null) {
        smallest = smallest.left;
      }
      //delete it
      delete(root, smallest.val);
      //set value of smallest to the current and return
      root.val = smallest.val;
    } else if (data > root.val) {
      root.right = delete(root.right, data);
    } else
      root.left = delete(root.left, data);
    return root;
  }
}
