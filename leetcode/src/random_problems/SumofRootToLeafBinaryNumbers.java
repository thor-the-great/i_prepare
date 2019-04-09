package random_problems;

import diff_problems.TreeNode;

/**
 * 1022. Sum of Root To Leaf Binary Numbers
 * Easy
 *
 * Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with
 * the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in
 * binary, which is 13.
 *
 * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
 *
 * Return the sum of these numbers.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [1,0,1,0,1,0,1]
 * Output: 22
 * Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 *
 *
 * Note:
 *
 * The number of nodes in the tree is between 1 and 1000.
 * node.val is 0 or 1.
 * The answer will not exceed 2^31 - 1.
 */
public class SumofRootToLeafBinaryNumbers {

    long res;
    int MOD = 1_000_000_007;

    public int sumRootToLeaf(TreeNode root) {
        if (root == null)
            return 0;
        res = 0;
        helper(root, 0);
        return (int)(res % MOD);
    }

    void helper(TreeNode n, long num) {
        //make left shift (account previous digit position in binary form) and add current node value
        num = (num<< 1) + n.val;
        num %= MOD;
        //if this is the leaf - add to result sum and return from recursion
        if (n.left == null && n.right == null) {
            res += num;
            return;
        }
        //go into recursion for left and right subtrees
        if (n.left != null)
            helper(n.left, num);

        if (n.right != null)
            helper(n.right, num);
    }
}
