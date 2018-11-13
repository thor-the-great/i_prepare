package path.amazon;

import diff_problems.TreeNode;
import trees.TreeUtils;

/**
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 *
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * Construct the maximum tree by the given array and output the root node of this tree.
 *
 * Example 1:
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 *
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 * Note:
 * The size of the given array will be in the range [1,1000].
 */
public class MaxBinaryTree {

    int[] arr;

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        arr = nums;
        TreeNode root = helper(0, arr.length - 1);
        return root;
    }

    TreeNode helper(int l, int r) {
        if (l > r) return null;
        int maxIndex = getMaxIndex(l, r);
        TreeNode n = new TreeNode(arr[maxIndex]);
        n.left = helper(l, maxIndex - 1);
        n.right = helper(maxIndex + 1, r);
        return n;
    }

    int getMaxIndex(int l, int r) {
        int maxIndex = l;
        for (int i = l + 1; i <= r; i++)
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        return maxIndex;
    }

    public static void main(String[] args) {
        MaxBinaryTree obj = new MaxBinaryTree();
        TreeNode maxBT = obj.constructMaximumBinaryTree(new int[] {3,2,1,6,0,5});
        System.out.println(TreeUtils.binaryTreeToString(maxBT));
    }
}
