package trees;

/**
 * 993. Cousins in Binary Tree
 * Easy
 *
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth
 * k+1.
 *
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 *
 * We are given the root of a binary tree with unique values, and the values x and y of two
 * different nodes in the tree.
 *
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4], x = 4, y = 3
 * Output: false
 * Example 2:
 *
 *
 * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
 * Output: true
 * Example 3:
 *
 *
 *
 * Input: root = [1,2,3,null,4], x = 2, y = 3
 * Output: false
 *
 *
 * Note:
 *
 * The number of nodes in the tree will be between 2 and 100.
 * Each node has a unique integer value from 1 to 100.
 */
public class CousinsInBinaryTree {

    TreeNode xPar = null, yPar = null;
    int dX = -1, dY = -1;

    /**
     * Iterate and look for both nodes, keep their parents and depth. At the end compare
     * parents and depth
     * @param root
     * @param x
     * @param y
     * @return
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        helper(root, null, x, y, 0);
        return dX == dY && xPar != yPar;
    }

    void helper(TreeNode n, TreeNode par, int x, int y, int d) {
        if (n == null)
            return;

        if (n.val == x) {
            xPar = par;
            dX = d;
        } else if (n.val == y) {
            yPar = par;
            dY = d;
        }
        helper(n.left, n, x, y, d + 1);
        helper(n.right, n, x, y, d + 1);
    }
}
