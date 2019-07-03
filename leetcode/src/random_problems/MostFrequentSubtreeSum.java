package random_problems;

import trees.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 508. Most Frequent Subtree Sum
 * Medium
 *
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined
 * as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what
 * is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any
 * order.
 *
 * Examples 1
 * Input:
 *
 *   5
 *  /  \
 * 2   -3
 * return [2, -3, 4], since all the values happen only once, return all of them in any order.
 * Examples 2
 * Input:
 *
 *   5
 *  /  \
 * 2   -5
 * return [2], since 2 happens twice, however -5 only occur once.
 * Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 *
 */
public class MostFrequentSubtreeSum {

    Map<Integer, Integer> counts = new HashMap();
    int max = 0;
    int count = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null)
            return new int[] {};
        counts.clear();
        helper(root);
        int[] res = new int[count];
        int i = 0;
        for (int val : counts.keySet()) {
            if (counts.get(val) == max)
                res[i++] = val;
        }
        return res;
    }

    int helper(TreeNode n) {
        if (n == null)
            return 0;
        if (n.left == null && n.right == null) {
            addValue(n.val);
            return n.val;
        }
        int sum = n.val + helper(n.left) + helper(n.right);
        addValue(sum);

        return sum;
    }

    private void addValue(int val) {
        int newCount = 1;
        if (counts.containsKey(val))
            newCount = counts.get(val) + 1;
        if (newCount == max) {
            count++;
        } else if (newCount > max) {
            max = newCount;
            count = 1;
        }
        counts.put(val, newCount);
    }
}
