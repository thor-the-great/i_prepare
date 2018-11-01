package trees;

import diff_problems.TreeNode;

import java.util.Stack;

public class ValidateBST {

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        //do in-order traversal
        //check every element in place
        Stack<TreeNode> stack = new Stack();
        TreeNode curr = root;
        long num = Long.MIN_VALUE;
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (curr.val <= num)
                return false;
            else
                num = curr.val;
            curr = curr.right;
        }
        return true;
    }
}
