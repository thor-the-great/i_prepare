
package random_problems;

import trees.TreeNode;
import trees.TreeUtils;

/**
 * 1038. Binary Search Tree to Greater Sum Tree
 * Medium
 *
 * 137
 *
 * 14
 *
 * Favorite
 *
 * Share
 * Given the root of a binary search tree with distinct values, modify it so that every node has
 * a new value equal to the sum of the values of the original tree that are greater than or equal
 * to node.val.
 *
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 *
 *
 * Note:
 *
 * The number of nodes in the tree is between 1 and 100.
 * Each node will have value between 0 and 100.
 * The given tree is a binary search tree.
 */
public class BSTtoGreaterSumTree {

  int prevSum = 0;

  /**
   * Idea - If we go in reversed inorder then we just need to add sum of previous ones to the
   * current.
   * The sum prevSum can be accumulated in a global variable
   * @param root
   * @return
   */
  public TreeNode bstToGst(TreeNode root) {
    if (root.right != null)
      bstToGst(root.right);

    root.val += prevSum;
    prevSum = root.val;

    if (root.left != null)
      bstToGst(root.left);

    return root;
  }

  public static void main(String[] args) {
    BSTtoGreaterSumTree obj = new BSTtoGreaterSumTree();
    //[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
    TreeNode root1 = new TreeNode(4,
        new TreeNode(1,
            new TreeNode(0),
            new TreeNode(2,
                null,
                new TreeNode(3))),
        new TreeNode(6,
            new TreeNode(5),
            new TreeNode(7,
                null,
                new TreeNode(8))));
    TreeNode changed = obj.bstToGst(root1);
    System.out.println(TreeUtils.binaryTreeToString(changed));
  }
}
