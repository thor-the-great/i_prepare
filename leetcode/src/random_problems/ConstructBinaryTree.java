package random_problems;

import trees.TreeNode;

import java.util.Arrays;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Medium
 *
 * Share
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class ConstructBinaryTree {

    /**
     * Idea - from preorder take root of sub-tree, then find node in in-order. Root splits in-order into two halves
     * which are left and right sub-trees respectively. Pass those array halves recursively and continue until done
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int N = preorder.length;
        if (N == 0)
            return null;
        TreeNode root = new TreeNode(preorder[0]);
        int rootIdx = findElement(inorder, preorder[0]);
        helper(root, Arrays.copyOfRange(preorder, 1, N),
                Arrays.copyOfRange(inorder, 0, rootIdx),
                Arrays.copyOfRange(inorder, rootIdx + 1, N));
        return root;
    }

    void helper(TreeNode root, int[] preorder, int[] inorderLeft, int[] inorderRight) {
        if (inorderLeft.length > 0) {
            TreeNode left = new TreeNode(preorder[0]);
            int idx = findElement(inorderLeft, preorder[0]);
            helper(left,
                    Arrays.copyOfRange(preorder, 1, preorder.length - inorderRight.length),
                    Arrays.copyOfRange(inorderLeft, 0, idx),
                    Arrays.copyOfRange(inorderLeft, idx + 1, inorderLeft.length)
            );
            root.left = left;
        }
        if (inorderRight.length > 0) {
            int rightRootIndex = inorderLeft.length;
            TreeNode right = new TreeNode(preorder[rightRootIndex]);
            int idx = findElement(inorderRight, preorder[rightRootIndex]);
            helper(right,
                    Arrays.copyOfRange(preorder, inorderLeft.length + 1, preorder.length),
                    Arrays.copyOfRange(inorderRight, 0, idx),
                    Arrays.copyOfRange(inorderRight, idx + 1, inorderRight.length)
            );
            root.right = right;
        }
    }

    int findElement(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        ConstructBinaryTree obj = new ConstructBinaryTree();
        TreeNode node = obj.buildTree(
                new int[] {3,9,20,15,7},
                new int[] {9,3,15,20,7});
        System.out.println(node);
    }
}
