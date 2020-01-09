package path.amazon;

import trees.TreeNode;

import java.util.*;

/**
 * Two Sum IV - Input is a BST
 *   
 * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their
 * sum is equal to the given target.
 *
 * Example 1:
 * Input:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 9
 *
 * Output: True
 * Example 2:
 * Input:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 28
 *
 * Output: False
 */
public class TwoSumBST {

    Set<Integer> set = new HashSet();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null)
            return false;

        if (set.contains(k - root.val))
            return true;

        set.add(root.val);

        return findTarget(root.left, k) || findTarget(root.right, k);
    }

    public boolean findTargetInOrderTraverseOrdered(TreeNode root, int k) {
        List<Integer> nums = new ArrayList();
        //do inorder traverse, list will be sorted (asc), then do pointers left/right to find K in one scan
        Stack<TreeNode> stack = new Stack();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            nums.add(curr.val);
            curr = curr.right;
        }

        int l = 0, r = nums.size() - 1;
        while (l < r) {
            int sum = nums.get(l) + nums.get(r);
            if (sum == k) return true;
            if (sum > k)
                r--;
            else
                l++;
        }
        return false;
    }
}
