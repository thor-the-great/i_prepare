package trees;

public class BinaryTreeLongestConsecutiveSequence {

    public int longestConsecutive(TreeNode root) {
        return helper(null, 0, root);
    }

    int helper(TreeNode prev, int len, TreeNode n) {
        //base case
        if (n == null)
            return len;

        //if this is consecutive number (or root) - increment length and visit children
        if (prev == null || n.val == prev.val + 1) {
            return Math.max(
                    helper(n, len + 1, n.left),
                    helper(n, len + 1, n.right));
        }
        //if this is not a consecutive number - break the sequence, return max of the current
        //sequence length and left/right children sequence starting from 1
        return
                Math.max(len,
                        Math.max(helper(n, 1, n.left), helper(n, 1, n.right)));
    }
}
