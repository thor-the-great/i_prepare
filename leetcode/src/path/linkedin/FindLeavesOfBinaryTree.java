package path.linkedin;

import trees.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 366. Find Leaves of Binary Tree
 * Medium
 *
 * Share
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until
 * the tree is empty.
 *
 *
 *
 * Example:
 *
 * Input: [1,2,3,4,5]
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *
 * Output: [[4,5,3],[2],[1]]
 *
 *
 * Explanation:
 *
 * 1. Removing the leaves [4,5,3] would result in this tree:
 *
 *           1
 *          /
 *         2
 *
 *
 * 2. Now removing the leaf [2] would result in this tree:
 *
 *           1
 *
 *
 * 3. Now removing the leaf [1] would result in the empty tree:
 *
 *           []
 */
public class FindLeavesOfBinaryTree {

    /**
     * Idea is simple - go from bottom to the top, counting leaves and add them as we go.
     * Actual removal is optional with this approach
     */
    List<List<Integer>> res;
    public List<List<Integer>> findLeaves(TreeNode root) {
        res = new LinkedList();
        helper(root);
        return res;
    }

    int helper(TreeNode root) {
        if (root == null)
            return -1;
        int level = 1 + Math.max(helper(root.left), helper(root.right));
        root.left = root.right = null;//remove actual node if it's needed
        if (res.size() < level + 1)
            res.add(new LinkedList());
        res.get(level).add(root.val);
        return level;
    }

    /**
     * This is different - here we go up-down and remove leaves. Then restart tree again after
     * @param root
     * @return
     */
    public List<List<Integer>> findLeavesRecursiveOnlyRemoval(TreeNode root) {
        List<List<Integer>> res = new LinkedList();
        while (root != null) {
            List<Integer> l = new LinkedList();
            if (helper(root, l))
                root = null;
            res.add(l);
        }
        return res;
    }

    boolean helper(TreeNode root, List<Integer> l) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null) {
            l.add(root.val);
            return true;
        }
        if (helper(root.left, l))
            root.left = null;
        if (helper(root.right, l))
            root.right = null;
        return false;
    }
}
