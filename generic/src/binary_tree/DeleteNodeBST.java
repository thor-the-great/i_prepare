package binary_tree;

import trees.TreeNode;

/**
 * 450. Delete NaryTreeNode in a BST
 * Medium
 *
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node
 * reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 *
 * Search for a node to remove.
 * If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 *
 * Example:
 *
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 *
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 *
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 *
 * Another valid answer is [5,2,6,null,4,null,7].
 *
 *     5
 *    / \
 *   2   6
 *    \   \
 *     4   7
 */
public class DeleteNodeBST {

    /**
     * Deletion in a BST - Introduction
     * Deletion is more complicated than the two operations we mentioned before. There are also many different
     * strategies for deletion. We are going to introduce one of them which minimizes the changes. Our solution is to
     * replace the target node with a proper child. According to the number of its children, we should consider three
     * different cases:
     *
     * 1. If the target node has no child, we can simply remove the node.
     * 2. If the target node has one child, we can use its child to replace itself.
     * 3. If the target node has two children, replace the node with its in-order successor or predecessor node and
     * delete that node.
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return root;

        if (root.val == key) {

            if (root.left == null)
                return root.right;

            if (root.right == null)
                return root.left;

            //find proper node to be a replacement
            TreeNode r = root.right;
            while (r != null && r.left != null) {
                r = r.left;
            }

            root.val = r.val;
            root.right = deleteNode(root.right, r.val);
            return root;

        } else if (key > root.val)
            root.right = deleteNode(root.right, key);
        else
            root.left = deleteNode(root.left, key);

        return root;
    }
}
