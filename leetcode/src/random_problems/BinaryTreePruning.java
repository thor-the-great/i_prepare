package random_problems;

import trees.TreeNode;

/**
 * 814. Binary Tree Pruning
 * Medium
 *
 * Share
 * We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.
 *
 * Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
 *
 * (Recall that the subtree of a node X is X, plus every node that is a descendant of X.)
 *
 * Example 1:
 * Input: [1,null,0,0,1]
 * Output: [1,null,0,null,1]
 *
 * Explanation:
 * Only the red nodes satisfy the property "every subtree not containing a 1".
 * The diagram on the right represents the answer.
 *
 *
 * Example 2:
 * Input: [1,0,1,0,0,0,1]
 * Output: [1,null,1,null,1]
 *
 *
 *
 * Example 3:
 * Input: [1,1,0,1,1,0,1,0]
 * Output: [1,1,0,1,1,null,1]
 *
 *
 *
 * Note:
 *
 * The binary tree will have at most 100 nodes.
 * The value of each node will only be 0 or 1.
 */
public class BinaryTreePruning {
    /**
     * Idea - simple recursion - just prune subtrees if needed and then prune root if both sub-trees are
     * pruned (or null)
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        helper(root);
        return root;
    }

    boolean helper(TreeNode n) {
        boolean pruneLeft = true;
        if (n.left != null ) {
            pruneLeft = helper(n.left);
            if (pruneLeft)
                n.left = null;
        }
        boolean pruneRight = true;
        if (n.right != null ) {
            pruneRight = helper(n.right);
            if (pruneRight)
                n.right = null;
        }
        return (n.val == 0) && pruneLeft && pruneRight;
    }
}
