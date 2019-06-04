package path.uber;

import trees.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 652. Find Duplicate Subtrees
 * Medium
 *
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return
 * the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with same node values.
 *
 * Example 1:
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   2   4
 *        /
 *       4
 * The following are two duplicate subtrees:
 *
 *       2
 *      /
 *     4
 * and
 *
 *     4
 * Therefore, you need to return above trees' root in the form of a list.
 */
public class FindDuplicateSubtrees {
    List<TreeNode> res;
    Map<String, Integer> count;
    Map<TreeNode, String> memo;

    /**
     * Idea: Traverse the tree, create tree key as a concat of node.val + left_sub_tree + right_sub_tree. Save number
     * of times we met this key, if it's 2 - add it to result
     * Catch - to create a key we need to visit every node, but this needs to be done for every node. To avoid
     * O(N^2) complexity we need to memorize the result - similar to DP
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        res = new ArrayList();
        count = new HashMap();
        memo = new HashMap();
        helper(root);
        return res;
    }

    String helper(TreeNode n) {
        if (n == null)
            return "-";

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(n.val).append(",").
                append(helper(n.left)).append(",").
                append(helper(n.right));
        String key = sb.toString();
        memo.put(n, key);

        if (!count.containsKey(key))
            count.put(key, 1);
        else if (count.get(key) == 1) {
            res.add(n);
            count.put(key, 2);
        }
        return key;
    }
}
