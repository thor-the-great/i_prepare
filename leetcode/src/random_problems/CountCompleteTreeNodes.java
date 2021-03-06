package random_problems;

import trees.TreeNode;

/**
 * 222. Count Complete Tree Nodes
Medium

785

97

Favorite

Share
Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely 
filled, and all nodes in the last level are as far left as possible. It can 
have between 1 and 2h nodes inclusive at the last level h.

Example:

Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6

 */
public class CountCompleteTreeNodes {

    /**
     * Idea: count height in left and right, if they are equal - height is 2^h - 1. Otherwise count number of nodes
     * recursively
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }    
        int leftH = getHeight(root, true);
        int rightH = getHeight(root, false);

        if (leftH == rightH)
            return (2<<leftH) - 1;
        else {
            return getHeight(root);
        }
    }

    int getHeight(TreeNode n, boolean left) {
        if (n == null)
            return 0;
        
        return getHeight(left ? n.left : n.right, left) + 1;
    }

    int getHeight(TreeNode n) {
        if (n == null)
            return 0;

        return getHeight(n.left) + getHeight(n.right) + 1;
    }

    /**
     * This is O(logn*logn) solution. Go to the right and to the left subtree at the same time but only there, count height.
     * If tree is complete then right and left ends at the same time and num of nodes is 2^d - 1.
     * Else collect the same for right and left children
     * @param root
     * @return
     */
    public int countNodesRec(TreeNode root) {
        if (root == null)
            return 0;
        TreeNode left = root, right = root;
        int height = 0;
        while (right != null) {
            left = left.left;
            right = right.right;
            height++;
        }
        if (left == null)
            return (1 << height) - 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public static void main(String[] args) {
        CountCompleteTreeNodes obj = new  CountCompleteTreeNodes();
        System.out.println(obj.countNodes(new TreeNode(1)));
    }
}
