package random_problems;

import diff_problems.TreeNode;

/**
 * 108. Convert Sorted Array to Binary Search Tree
 * Easy
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees
 * of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class ConvertArrayToBST {
    int[] nums;

    /**
     * Just use binary search - inorder traversal will give sorted array, mid of the array is the root. This holds
     * for every array divided in halves
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        int l = 0, r = nums.length - 1;
        return helper(l, r);
    }

    TreeNode helper(int l, int r) {
        if (l > r)
            return null;
        int m = (r + l) /2;
        TreeNode mNode = new TreeNode(nums[m]);
        mNode.left = helper(l, m - 1);
        mNode.right = helper(m + 1, r);
        return mNode;
    }
}
