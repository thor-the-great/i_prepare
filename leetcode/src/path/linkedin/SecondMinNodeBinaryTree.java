package path.linkedin;

import diff_problems.TreeNode;

/**
 * 671. Second Minimum Node In a Binary Tree
 * Easy
 *
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.
 *
 * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
 *
 * If no such second minimum value exists, output -1 instead.
 *
 * Example 1:
 * Input:
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 *
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 * Example 2:
 * Input:
 *     2
 *    / \
 *   2   2
 *
 * Output: -1
 * Explanation: The smallest value is 2, but there isn't any second smallest value.
 */
public class SecondMinNodeBinaryTree {

    long res = Long.MAX_VALUE;
    int rootVal = -1;

    /**
     * Idea - iterate over all levels, check nodes if it's root is > than rootVal
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null)
            return -1;
        rootVal = root.val;
        if (root.left == null)
            return -1;
        helper(root);
        if (res == Long.MAX_VALUE)
            res = -1;
        return (int)res;
    }

    void helper(TreeNode n) {
        if (n.val > rootVal && n.val < res)
            res = n.val;
        else if (n.val == rootVal) {
            if (n.left != null)
                helper(n.left);
            if (n.right != null)
                helper(n.right);
        }
    }
}
