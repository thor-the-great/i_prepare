package path.linkedin;

import trees.TreeNode;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * Medium
 *
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 *         _______3______
 *        /              \
 *     ___5__          ___1__
 *    /      \        /      \
 *    6      _2       0       8
 *          /  \
 *          7   4
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself
 *              according to the LCA definition.
 * Note:
 *
 * All of the nodes' values will be unique.
 * p and q are different and both values will exist in the binary tree.
 *
 */
public class LowestCommonAncestorBT {
    TreeNode p;
    TreeNode q;
    TreeNode ans = null;

    /**
     * Idea is to recursively iterate over every node and track if we met p or q or node is one of those itself.
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.p = p;
        this.q = q;
        helper(root);
        return ans;
    }

    boolean helper(TreeNode n) {
        if (n == null)
            return false;
        int match = (n.val == p.val || n.val == q.val) ? 1 : 0;
        int left = helper(n.left) ? 1 : 0;
        int right = helper(n.right) ? 1 : 0;
        int res = (match + left + right);
        if (res >= 2 && ans == null)
            ans = n;
        return res > 0;
    }
}
