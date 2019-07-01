package random_problems;

import trees.TreeNode;

/**
 * 958. Check Completeness of a Binary Tree
 * Difficulty: Medium
 * Given a binary tree, determine if it is a complete binary tree.
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last
 * level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: [1,2,3,4,5,6]
 * Output: true
 * Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the
 * last level ({4, 5, 6}) are as far left as possible.
 * Example 2:
 *
 *
 *
 * Input: [1,2,3,4,5,null,7]
 * Output: false
 * Explanation: The node with value 7 isn't as far left as possible.
 *
 * Note:
 *
 * The tree will have between 1 and 100 nodes.
 */
public class CheckBTComplete {

    /**
     * Count nodes first, then start iterating level-based and start counting from 0. If some node is missing then
     * final count will be >= then initial count
     * @param root
     * @return
     */
    public boolean isCompleteTree(TreeNode root) {
        if (root == null)
            return true;
        int countOfNodes = countNodes(root);

        return check(root, 0, countOfNodes);
    }

    int countNodes(TreeNode n) {
        if (n == null)
            return 0;
        return 1 + countNodes(n.left) + countNodes(n.right);
    }

    boolean check(TreeNode n, int i, int numOfNodes) {
        if (n == null)
            return true;
        if (i >= numOfNodes)
            return false;
        return check(n.left, 2*i + 1, numOfNodes) && check(n.right, 2*i + 2, numOfNodes);
    }

    public static void main(String[] args) {
        CheckBTComplete obj = new CheckBTComplete();
        TreeNode root = null;
        root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)),
                new TreeNode(3,
                        new TreeNode(6),
                        null)
        );
        System.out.println(obj.isCompleteTree(root));

        root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(5),
                        null),
                new TreeNode(3,
                        new TreeNode(7),
                        new TreeNode(8))
        );
        System.out.println(obj.isCompleteTree(root));
    }
}
