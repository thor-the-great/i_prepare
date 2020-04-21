package binary_search;

import trees.TreeNode;

/**
 * Construct Binary Search Tree from Preorder Traversal
 *
 * Return the root node of a binary search tree that matches the given preorder traversal.
 *
 * (Recall that a binary search tree is a binary tree where for every node, any descendant of
 * node.left has a value < node.val, and any descendant of node.right has a value > node.val.
 * Also recall that a preorder traversal displays the value of the node first, then traverses
 * node.left, then traverses node.right.)
 *
 * Example 1:
 *
 * Input: [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 *
 * Note:
 *
 * 1 <= preorder.length <= 100
 * The values of preorder are distinct.
 */
public class ConstructBinarySearchTreePreorderTraversal {

  int pos = 0;

  /**
   * Use max limit for the value, if next array element is > then the limit this element
   * can't be used.
   * increment pointer for array globally, finish when pointer reaches length of the array
   * @param preorder
   * @return
   */
  public TreeNode bstFromPreorder(int[] preorder) {
    return helper(preorder, Integer.MAX_VALUE);
  }

  TreeNode helper(int[] preorder, int limit) {
    if (pos >= preorder.length || preorder[pos] > limit) {
      return null;
    }

    TreeNode n = new TreeNode(preorder[pos++]);
    n.left = helper(preorder, n.val);
    n.right = helper(preorder, limit);
    return n;
  }
}
