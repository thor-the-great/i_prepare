package path.linkedin;

import trees.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * Medium
 *
 *
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then
 * right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 */
public class BinaryTreeZigZagTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if (root == null)
            return res;
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        boolean direct = true;
        List<Integer> level = null;
        while(!q.isEmpty()) {
            level = new ArrayList();
            int N = q.size();
            for (int i = 0; i < N; i++) {
                TreeNode n = q.poll();
                //main catch - in case of backward direction emulate stack with add(0,..)
                if (direct)
                    level.add(n.val);
                else
                    level.add(0, n.val);
                if (n.left != null)
                    q.add(n.left);
                if (n.right != null)
                    q.add(n.right);
            }
            res.add(level);
            direct = !direct;
        }
        return res;
    }
}
