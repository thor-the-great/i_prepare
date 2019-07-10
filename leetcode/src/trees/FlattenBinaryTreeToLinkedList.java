/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package trees;

import com.sun.source.tree.Tree;
import path.google.TrappedRainWater;
import utils.StringUtils;

/**
 * 114. Flatten Binary Tree to Linked List
 * Medium
 *
 * 1562
 *
 * 201
 *
 * Favorite
 *
 * Share
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example, given the following tree:
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class FlattenBinaryTreeToLinkedList {

  public void flatten(TreeNode root) {
    if (root == null)
      return;

    helper(root, null);
  }

  TreeNode helper(TreeNode node, TreeNode prev) {
    if (node == null) {
      return prev;
    }
    prev = helper(node.right, prev);
    prev = helper(node.left, prev);
    node.right = prev;
    node.left = null;
    return node;
  }

  public static void main(String[] args) {
    FlattenBinaryTreeToLinkedList obj = new FlattenBinaryTreeToLinkedList();
    TreeNode root = new TreeNode(1,
        new TreeNode(2,
            new TreeNode(3),
            new TreeNode(4)),
        new TreeNode(5,
            null,
            new TreeNode(6)));
    obj.flatten(root);
    System.out.println(TreeUtils.binaryTreeToString(root));
  }
}
