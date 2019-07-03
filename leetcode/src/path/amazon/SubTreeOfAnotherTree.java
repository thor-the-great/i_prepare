package path.amazon;

import trees.TreeNode;

/**
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with
 * a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s
 * could also be considered as a subtree of itself.
 *
 * Example 1:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return true, because t has the same structure and node values with a subtree of s.
 * Example 2:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return false.
 */
public class SubTreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return traverse(s, t);
    }

    boolean traverse(TreeNode sT, TreeNode t) {
        if (sT == null) return false;
        return checkSubTrees(sT, t) || (traverse(sT.left, t) || traverse(sT.right, t));
    }

    boolean checkSubTrees(TreeNode sT, TreeNode t) {
        if (sT == null && t == null) return true;
        if (sT == null || t == null)
            return false;
        return sT.val == t.val && checkSubTrees(sT.left, t.left) && checkSubTrees(sT.right, t.right);
    }
}
