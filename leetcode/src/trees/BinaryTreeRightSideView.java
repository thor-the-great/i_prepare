package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. Binary Tree Right Side View
 * Medium
 *
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can
 * see ordered from top to bottom.
 *
 * Example:
 *
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 */
public class BinaryTreeRightSideView {

    List<Integer> res;

    /**
     * Do recursive DFS starting from the rightmost node. Keep index of the current depth, use it as index (size) in
     * resulting list
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        res = new ArrayList();
        if (root == null)
            return res;
        helper(root, 0);
        return res;
    }

    void helper(TreeNode n, int d) {
        d++;
        if (res.size() < d) {
            res.add(n.val);
        }

        if (n.right != null)
            helper(n.right, d);
        if (n.left != null)
            helper(n.left, d);
    }

    public List<Integer> rightSideViewQueue(TreeNode root) {
        List<Integer> res = new ArrayList();

        Queue<TreeNode> q = new LinkedList();
        if (root != null)
            q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            int val = -1;
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                val = n.val;
                if (n.left != null)
                    q.add(n.left);
                if (n.right != null)
                    q.add(n.right);
            }
            res.add(val);
        }

        return res;
    }
}
