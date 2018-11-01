package trees;

import diff_problems.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Symmetric Tree
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following [1,2,2,null,3,null,3] is not:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 */
public class IsBSTSymmetrical {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> qNode = new LinkedList();
        qNode.add(root);
        qNode.add(root);
        while(!qNode.isEmpty()) {
            TreeNode n1 = qNode.poll();
            TreeNode n2 = qNode.poll();
            if (n1 == null && n2 == null)
                continue;
            if (n1 == null || n2 == null)
                return false;
            if (n1.val != n2.val)
                return false;
            qNode.add(n1.left);
            qNode.add(n2.right);
            qNode.add(n1.right);
            qNode.add(n2.left);
        }
        return true;
    }

    public boolean isSymmetricSlow(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> qNode = new LinkedList();
        Queue<Integer> qLevel = new LinkedList();
        qNode.add(root);
        qLevel.add(1);
        List<Integer> levelElements = new ArrayList();
        int l = 1;
        while(!qNode.isEmpty()) {
            TreeNode n = qNode.poll();
            int level = qLevel.poll();
            if (level != l && level > 1) {
                //check the symetry
                if (levelElements.size() % 2 == 1)
                    return false;
                for (int i = 0; i < levelElements.size() / 2; i++) {
                    if (levelElements.get(i) != levelElements.get(levelElements.size() - 1 - i)) {
                        return false;
                    }
                }
                l = level;
                levelElements.clear();
            }
            if (n.left != null) {
                qNode.add(n.left);
                qLevel.add(level + 1);
                levelElements.add(n.left.val);
            } else {
                levelElements.add(null);
            }
            if (n.right != null) {
                qNode.add(n.right);
                qLevel.add(level + 1);
                levelElements.add(n.right.val);
            } else {
                levelElements.add(null);
            }
        }
        return true;
    }
}
