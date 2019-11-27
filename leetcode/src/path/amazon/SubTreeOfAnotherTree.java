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

    /**
     * For each t we can potentially have match (isEq) or not - in this case move to next of s and start over.
     * IsEq must check if both nulls - true, if only one null - false, otherwise check if nodes are equals and
     * call recursively for left and right of each tree.
     * Traverse method must check current s and t for equals but move to s.left and s.right and start the check
     * for each of those and t.
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return s!= null && (isEq(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t));
    }

    boolean isEq(TreeNode s, TreeNode t) {
        if (s == null && t == null)
            return true;

        if (s == null || t == null)
            return false;


        return (s.val == t.val) && isEq(s.left, t.left) && isEq(s.right, t.right);
    }
}
