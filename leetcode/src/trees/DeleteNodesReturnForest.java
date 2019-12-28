package trees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1110. Delete Nodes And Return Forest
 * Medium
 *
 * Given the root of a binary tree, each node in the tree has a distinct value.
 *
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 *
 * Return the roots of the trees in the remaining forest.  You may return the result in any order.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 *
 *
 * Constraints:
 *
 * The number of nodes in the given tree is at most 1000.
 * Each node has a distinct value between 1 and 1000.
 * to_delete.length <= 1000
 * to_delete contains distinct values between 1 and 1000.
 */
public class DeleteNodesReturnForest {

    Set<Integer> toDel;
    List<TreeNode> res;

    /**
     * Doing DFS recursively, keeping previous and current nodes, if need to delete - remove current node from
     * previous, keep the left and right children to the list.
     * @param root
     * @param to_delete
     * @return
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        toDel = new HashSet();
        for (int n : to_delete)
            toDel.add(n);
        res = new ArrayList();

        if (root == null)
            return res;

        if (!toDel.contains(root.val))
            res.add(root);

        helper(null, root, true);

        return res;
    }

    void helper(TreeNode prev, TreeNode cur, boolean left) {
        if (cur == null)
            return;
        //delete current node
        boolean deleted = false;
        if (toDel.contains(cur.val)) {
            if (prev != null) {
                if (left)
                    prev.left = null;
                else
                    prev.right = null;
            }

            if (cur.left != null && !toDel.contains(cur.left.val)) {
                res.add(cur.left);
            }
            if (cur.right != null && !toDel.contains(cur.right.val)) {
                res.add(cur.right);
            }
            deleted = true;
       }

        helper(deleted ? null : cur, cur.left, true);
        helper(deleted ? null : cur, cur.right, false);
    }
}
