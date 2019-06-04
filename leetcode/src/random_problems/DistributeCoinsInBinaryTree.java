package random_problems;

import trees.TreeNode;

/**
 * 979. Distribute Coins in Binary Tree
 * Medium
 *
 * Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.
 *
 * In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from
 * parent to child, or from child to parent.)
 *
 * Return the number of moves required to make every node have exactly one coin.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [3,0,0]
 * Output: 2
 * Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
 * Example 2:
 *
 *
 *
 * Input: [0,3,0]
 * Output: 3
 * Explanation: From the left child of the root, we move two coins to the root [taking two moves].  Then, we move one
 * coin from the root of the tree to the right child.
 * Example 3:
 *
 *
 *
 * Input: [1,0,2]
 * Output: 2
 * Example 4:
 *
 *
 *
 * Input: [1,0,0,null,3]
 * Output: 4
 *
 *
 * Note:
 *
 * 1<= N <= 100
 * 0 <= node.val <= N
 */
public class DistributeCoinsInBinaryTree {

    int res;

    /**
     * Idea - for each sub-tree num of moves is leftSubMoves + rightSubMoves + n.val - 1. Go recursively on the tree
     * collecting information. Base case - for null return 0.
     * @param root
     * @return
     */
    public int distributeCoins(TreeNode root) {
        res = 0;
        helper(root);
        return res;
    }

    int helper(TreeNode n) {
        if (n == null)
            return 0;

        int leftMoves = helper(n.left);
        int rightMoves = helper(n.right);

        res += Math.abs(leftMoves) + Math.abs(rightMoves);

        return n.val - 1 + leftMoves + rightMoves;
    }
}
