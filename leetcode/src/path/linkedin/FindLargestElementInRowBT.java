package path.linkedin;

import trees.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 515. Find Largest Value in Each Tree Row
 * Medium
 *
 * You need to find the largest value in each row of a binary tree.
 *
 * Example:
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 *
 * Output: [1, 3, 9]
 */
public class FindLargestElementInRowBT {
    List<Integer> res;

    /**
     * Idea - do the level traversal and keep count of largest element. Elements are stored in the list as per
     * level index, so on every new node at level - add new element or compare and replace if new element is larger
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        res = new ArrayList();
        if (root == null)
            return res;
        helper(root, 0);
        return res;
    }

    void helper(TreeNode nextNode, int l) {
        //check if we have max element for the level, add this one if not
        if (res.size() <= l) {
            res.add(nextNode.val);
        } else {
            if (res.get(l) < nextNode.val)
                res.set(l, nextNode.val);
        }
        if (nextNode.left != null) {
            helper(nextNode.left, l + 1);
        }
        if (nextNode.right != null) {
            helper(nextNode.right, l + 1);
        }
    }
}
