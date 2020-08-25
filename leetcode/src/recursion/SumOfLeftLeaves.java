package recursion;

/**
 * Sum of Left Leaves

Solution
Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumOfLeftLeaves {
    
    /**
     * Check if node is leaf and it's left - return it's sum on top, recurse for both child,
     * set the flag only for left child
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeaves(root, false);
    }
    
    public int sumOfLeftLeaves(TreeNode root, boolean isLeft) {
        if (root == null)
            return 0;
        
        if (root.left == null && root.right == null && isLeft)
            return root.val;
        
        return sumOfLeftLeaves(root.left, true) + sumOfLeftLeaves(root.right, false);
    }
}