package trees;

/**
 * 1343. Maximum Product of Splitted Binary Tree
 *
 * Difficulty: Medium
 * Given a binary tree root. Split the binary tree into two subtrees by removing 1 edge such that the product of the
 * sums of the subtrees are maximized.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: 110
 * Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
 * Example 2:
 *
 *
 *
 * Input: root = [1,null,2,3,4,null,null,5,6]
 * Output: 90
 * Explanation:  Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
 * Example 3:
 *
 * Input: root = [2,3,9,10,7,8,6,5,4,11,1]
 * Output: 1025
 * Example 4:
 *
 * Input: root = [1,1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * Each tree has at most 50000 nodes and at least 2 nodes.
 * Each node's value is between [1, 10000].
 */
public class MaximumProductOfSplittedBinaryTree {

    long res = 0;

    /**
     * We count total sum of all nodes in one tree iteration. Then for every subtree we take it's sum and make
     * max(cur, sub_tree_sum*(total_sum - sub_tree_sum))
     * @param root
     * @return
     */
    public int maxProduct(TreeNode root) {
        //first pass count the total sum, save it in total
        long total = sum(root, 0);
        //this pass actually count max product of sub-tree sums
        sum(root, total);
        return (int)(res%(1_000_000_007));
    }

    long sum(TreeNode n, long total) {
        //base case - return sum = 0
        if (n == null)
            return 0L;
        //for this node it's sub-tree sum will be node_value + sum of left subtree + sum of right subtree
        long s = sum(n.left, total) + sum(n.right, total) + n.val;
        //compute product of mult for the tree in case one of the subtrees will start at this node
        //this subtree sum is s, so the rest of tree has sum = (total - s)
        res = Math.max(res, (total - s)*s);
        return s;
    }
}
