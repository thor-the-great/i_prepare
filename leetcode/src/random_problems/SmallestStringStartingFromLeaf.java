package random_problems;

import diff_problems.TreeNode;

/**
 * 988. Smallest String Starting From Leaf
 * Medium
 *
 * Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value
 * of 0 represents 'a', a value of 1 represents 'b', and so on.
 *
 * Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
 *
 * (As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically
 * smaller than "aba".  A leaf of a node is a node that has no children.)
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [0,1,2,3,4,3,4]
 * Output: "dba"
 * Example 2:
 *
 *
 *
 * Input: [25,1,3,1,3,0,2]
 * Output: "adz"
 * Example 3:
 *
 *
 *
 * Input: [2,2,1,null,1,0,null,0]
 * Output: "abc"
 *
 *
 * Note:
 *
 * The number of nodes in the given tree will be between 1 and 1000.
 * Each node in the tree will have a value between 0 and 25.
 *
 */
public class SmallestStringStartingFromLeaf {

    StringBuilder sb;
    String res;

    /**
     * Idea: do the dfs with backtracking, comparing Strings wit running minimum.
     * @param root
     * @return
     */
    public String smallestFromLeaf(TreeNode root) {
        sb = new StringBuilder();
        dfs(root);
        return res;
    }

    void dfs(TreeNode n) {
        if (n == null)
            return;

        char next = (char) (n.val + 'a');
        sb.insert(0, next);

        if (n.left == null && n.right == null) {
            String s = sb.toString();
            if (res == null || res.compareTo(s) > 0)
                res = s;
        }

        if (n.right != null)
            dfs(n.right);
        if (n.left != null)
            dfs(n.left);

        sb.delete(0, 1);
    }
}
