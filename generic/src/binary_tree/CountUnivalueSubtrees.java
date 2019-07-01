package binary_tree;

import trees.TreeNode;

/**
 * 250. Count Univalue Subtrees
 * Medium
 *
 * Given a binary tree, count the number of uni-value subtrees.
 *
 * A Uni-value subtree means all nodes of the subtree have the same value.
 *
 * Example :
 *
 * Input:  root = [5,1,5,5,5,null,5]
 *
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 *
 * Output: 4
 */
public class CountUnivalueSubtrees {

    int res;

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null)
            return 0;

        res = 0;

        helper(root);

        return res;
    }

    Integer helper(TreeNode n) {
        if (n.left == null && n.right == null) {
            res++;
            return n.val;
        }

        if (n.left == null) {
            Integer i = helper(n.right);
            if ( i != null && n.val == i) {
                res++;
                return n.val;
            }
        } else if (n.right == null) {
            Integer i = helper(n.left);
            if (i != null && n.val == i) {
                res++;
            }
        } else {
            Integer l = helper(n.left);
            Integer r = helper(n.right);
            if (l != null && r != null && n.val == l && n.val == r) {
                res++;
                return n.val;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        CountUnivalueSubtrees obj = new CountUnivalueSubtrees();
        TreeNode root1 = new TreeNode(5,
                new TreeNode(5,
                            new TreeNode(5),
                            new TreeNode(5)),
                new TreeNode(5,
                        null,
                            new TreeNode(5)));

        System.out.println(obj.countUnivalSubtrees(root1));
    }
}
