package dfs;

import trees.TreeNode;

import java.util.*;

/**
 * 863. All Nodes Distance K in Binary Tree
 * Medium
 * <p>
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 * <p>
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned
 * in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * <p>
 * Output: [7,4,1]
 * <p>
 * Explanation:
 * The nodes that are a distance 2 from the target node (with value 5)
 * have values 7, 4, and 1.
 * <p>
 * <p>
 * <p>
 * Note that the inputs "root" and "target" are actually TreeNodes.
 * The descriptions of the inputs above are just serializations of these objects.
 * <p>
 * <p>
 * Note:
 * <p>
 * The given tree is non-empty.
 * Each node in the tree has unique values 0 <= node.val <= 500.
 * The target node is a node in the tree.
 * 0 <= K <= 1000.
 */
public class AllNodesInBinaryTreeDistanceK {

    Map<TreeNode, TreeNode> map;

    /**
     * If we know the parent of every node x, we know all nodes that are distance 1 from x. We can then perform a
     * breadth first search from the target node to find the answer.
     *
     * We first do a depth first search where we annotate every node with information about it's parent.
     *
     * After, we do a breadth first search to find all nodes a distance K from the target.
     * @param root
     * @param target
     * @param K
     * @return
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList();
        if (root == null || target == null || K < 0)
            return res;
        map = new HashMap();
        buildChildParentMap(root, null);

        Queue<TreeNode> q = new LinkedList();
        q.add(null);
        q.add(target);

        Set<TreeNode> seen = new HashSet();
        seen.add(target);
        seen.add(null);

        int d = 0;

        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            if (n == null) {
                if (d == K) {
                    while (!q.isEmpty()) {
                        res.add(q.poll().val);
                    }
                    break;
                }
                q.add(null);
                d++;
            } else {
                if (!seen.contains(n.left)) {
                    seen.add(n.left);
                    q.add(n.left);
                }
                if (!seen.contains(n.right)) {
                    seen.add(n.right);
                    q.add(n.right);
                }
                TreeNode par = map.get(n);
                if (!seen.contains(par)) {
                    seen.add(par);
                    q.add(par);
                }
            }
        }

        return res;
    }

    void buildChildParentMap(TreeNode child, TreeNode parent) {
        if (child != null) {
            map.put(child, parent);
            buildChildParentMap(child.left, child);
            buildChildParentMap(child.right, child);
        }
    }
}
