package trees;

/**
 * 1123. Lowest Common Ancestor of Deepest Leaves
 * Medium
 *
 * Given a rooted binary tree, return the lowest common ancestor of its deepest leaves.
 *
 * Recall that:
 *
 * The node of a binary tree is a leaf if and only if it has no children
 * The depth of the root of the tree is 0, and if the depth of a node is d, the depth of each of its children is d+1.
 * The lowest common ancestor of a set S of nodes is the node A with the largest depth such that every node in S is in the subtree with root A.
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3]
 * Output: [1,2,3]
 * Explanation:
 * The deepest leaves are the nodes with values 2 and 3.
 * The lowest common ancestor of these leaves is the node with value 1.
 * The answer returned is a TreeNode object (not an array) with serialization "[1,2,3]".
 * Example 2:
 *
 * Input: root = [1,2,3,4]
 * Output: [4]
 * Example 3:
 *
 * Input: root = [1,2,3,4,5]
 * Output: [2,4,5]
 *
 *
 * Constraints:
 *
 * The given tree will have between 1 and 1000 nodes.
 * Each node of the tree will have a distinct value between 1 and 1000.
 */
public class LowestCommonAncestorOfDeepestLeaves {

    /**
     * Get the max depth of the tree. Start recursion with count of the current depth. If depth is max - return the node
     * For each node compute left and right result, if both are not null - we can return current node as common ancestor
     * @param root
     * @return
     */
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return helper(root, depth(root));
    }

    int depth(TreeNode n) {
        if (n == null)
            return 0;
        return Math.max(depth(n.left), depth(n.right)) + 1;
    }

    TreeNode helper(TreeNode n, int d) {
        //base cases
        //we reach the down of recursion but depth is not 1 - return null
        if (n == null)
            return null;
        //if depth is 1 - this is the deepest leaf
        if (d == 1)
            return n;

        //get deepest leaves for left and right
        TreeNode l = helper(n.left, d - 1);
        TreeNode r = helper(n.right, d - 1);
        //if both are not null - our current node is the LCA
        if (l != null && r != null)
            return n;
        //if at least one of left and right is null - return it, current node is not the answer
        return l != null ? l : r;
    }
}
