package recursion;

import diff_problems.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. Unique Binary Search Trees II
 * Medium
 *
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 *
 * Example:
 *
 * Input: 3
 * Output:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 */
public class UniqueBinarySearchTreesII {

    /**
     * Idea: go recursively, divide array into halves, take pivot as a root and generate sub-trees from left and
     * right parts respectively
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new ArrayList<>();

        return helper(1, n);
    }

    List<TreeNode> helper(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
            return res;
        }

        for (int c = start; c <= end; c++) {
            List<TreeNode> leftSub = helper(start, c - 1);
            List<TreeNode> rightSub = helper(c + 1, end);

            for (TreeNode l : leftSub) {
                for (TreeNode r : rightSub) {
                    TreeNode root = new TreeNode(c);
                    root.right = r;
                    root.left = l;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
