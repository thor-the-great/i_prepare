package trees;

/**
 * 951. Flip Equivalent Binary Trees
 * Medium
 *
 * For a binary tree T, we can define a flip operation as follows: choose any node, and swap the
 * left and right child subtrees.
 *
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y
 * after some number of flip operations.
 *
 * Write a function that determines whether two binary trees are flip equivalent.  The trees are
 * given by root nodes root1 and root2.
 *
 *
 *
 * Example 1:
 *
 * Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,
 * null,8,7]
 * Output: true
 * Explanation: We flipped at nodes with values 1, 3, and 5.
 * Flipped Trees Diagram
 *
 *
 * Note:
 *
 * Each tree will have at most 100 nodes.
 * Each value in each tree will be a unique integer in the range [0, 99].
 */
public class FlipEquivalentBinaryTrees {

    /**
     * Invariant is - for same root tree both children will be the same, just in case of flip
     * they will be flipped to different sides. Thus at each level of recursion check
     * if both children are same - recurse left with left and right with right, if
     * flipped - recurse with left-right and right-left.
     * Base case - both of only one of nodes are null, or node values are different.
     * @param root1
     * @param root2
     * @return
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null)
            return false;

        if (root1.val != root2.val)
            return false;

        if (sameNodes(root1.left, root2.left) && sameNodes(root1.right, root2.right))
            return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
        else if (sameNodes(root1.left, root2.right) && sameNodes(root1.right, root2.left))
            return flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
        else
            return false;
    }

    boolean sameNodes(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null)
            return true;
        if (n1 == null || n2 == null)
            return false;
        return n1.val == n2.val;
    }
}
