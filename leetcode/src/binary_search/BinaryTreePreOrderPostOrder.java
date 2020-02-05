package binary_search;

import trees.TreeNode;

import java.util.Arrays;

/**
 * 889. Construct Binary Tree from Preorder and Postorder Traversal
 * Medium
 *
 * Return any binary tree that matches the given preorder and postorder traversals.
 *
 * Values in the traversals pre and post are distinct positive integers.
 *
 *
 *
 * Example 1:
 *
 * Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
 * Output: [1,2,3,4,5,6,7]
 *
 *
 * Note:
 *
 * 1 <= pre.length == post.length <= 30
 * pre[] and post[] are both permutations of 1, 2, ..., pre.length.
 * It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
 */
public class BinaryTreePreOrderPostOrder {

    /**
     * In preorder root of any subtree is always first, then next one is root of its left subtree. Postorder
     * is mirrored image - the root of right subtree is the second from the end. Using this info we can
     * seach for left and right subtrees in every traversal array, get tree root and construct it's subtree
     * recursively from the cuts of each array.
     * Base case = length == 0 - return null
     * length == 1 - return root itself, nothing else
     * @param pre
     * @param post
     * @return
     */
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre.length == 0)
            return null;
        TreeNode root = new TreeNode(pre[0]);
        if (pre.length == 1)
            return root;

        int left = 0;
        for (int i = 0; i < pre.length; i++) {
            if (pre[1] == post[i]) {
                left = i;
                break;
            }
        }
        //left points to the root of left subtree in the post
        root.left = constructFromPrePost(
                Arrays.copyOfRange(pre, 1, left + 2),
                Arrays.copyOfRange(post, 0, left + 1));

        root.right = constructFromPrePost(
                Arrays.copyOfRange(pre, left + 2, pre.length),
                Arrays.copyOfRange(post, left + 1, pre.length - 1));
        return root;

    }
}
