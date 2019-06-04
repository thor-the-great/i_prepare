package path.linkedin;

import trees.TreeNode;

/**
 * 156. Binary Tree Upside Down
 * Medium
 *
 * Share
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the
 * same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into
 * left leaf nodes. Return the new root.
 *
 * Example:
 *
 * Input: [1,2,3,4,5]
 *
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 *
 * Output: return the root of the binary tree [4,5,2,#,#,3,1]
 *
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1
 * Clarification:
 *
 * Confused what [4,5,2,#,#,3,1] means? Read more below on how binary tree is serialized on OJ.
 *
 * The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no
 * node exists below.
 *
 * Here's an example:
 *
 *    1
 *   / \
 *  2   3
 *     /
 *    4
 *     \
 *      5
 * The above binary tree is serialized as [1,2,3,#,#,4,#,#,5].
 */
public class UpsideDownBinaryTree {

    TreeNode newRoot = null;

    /**
     * Idea - think of the way we need to transform tree
     *
     *       1
     *      / \
     *     2   3
     *    / \
     *   4   5
     *
     *   for every 1 level sub-tree we need to reweave links
     *       1
     *      /
     *     2 - 3
     *    /
     *   4 - 5
     *
     *   then turn the tree so original leftmost node will be new root
     *
     *    4
     *   / \
     *  5   2
     *     / \
     *    3   1
     *
     *  for that left.left should point to original root.right and left.right - to root. Also we need to remove original
     *  left and right links
     *
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null)
            return null;

        helper(root, root.left, root.right, true);
        return newRoot;
    }

    void helper(TreeNode n, TreeNode l, TreeNode r, boolean pruneRoot) {
        if (l == null && r == null) {
            newRoot = n;
            return;
        }

        TreeNode rNext = l.right;
        TreeNode lNext = l.left;
        if (pruneRoot) {
            n.right = null;
            n.left = null;
        }
        l.left = r;
        l.right = n;
        helper(l, lNext, rNext, false);
    }
}
