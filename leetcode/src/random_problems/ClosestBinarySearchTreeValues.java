package random_problems;

import trees.TreeNode;

import java.util.*;

public class ClosestBinarySearchTreeValues {

    /**
     * The solution that really make stack valuable is the idea of using two stacks as two iterators (smaller and
     * larger), the smaller stack will pop the next available largest element that is smaller then target, and larger
     * stack will pop the next available smallest element that is larger or equal to the target. Those two stacks will
     * not need to store all the nodes, but only number of nodes proportional to the height of the tree... an idea
     * similar to Leetcode 173...if the tree is balanced, the time cost will be amortized O(k), and the space cost will
     * be O(logN), but if the tree is not balanced, the worst space cost will be O(N), I post the solution below...
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<TreeNode> smaller = new Stack<>();
        Stack<TreeNode> larger = new Stack<>();
        pushSmaller(root, target, smaller);
        pushLarger(root, target, larger);

        List<Integer> res = new ArrayList<>();
        TreeNode cur;
        while (res.size() < k) {
            if (smaller.isEmpty() || (!larger.isEmpty() && larger.peek().val - target < target - smaller.peek().val)) {
                cur = larger.pop();
                res.add(cur.val);
                pushLarger(cur.right, target, larger);
            } else {
                cur = smaller.pop();
                res.add(cur.val);
                pushSmaller(cur.left, target, smaller);
            }
        }
        return res;
    }

    private void pushSmaller(TreeNode node, double target,  Stack<TreeNode> stack) {
        while (node != null) {
            if (node.val < target) {
                stack.push(node);
                node = node.right;
            } else {
                node = node.left;
            }
        }
    }

    private void pushLarger(TreeNode node, double target, Stack<TreeNode> stack) {
        while (node != null) {
            if (node.val >= target) {
                stack.push(node);
                node = node.left;
            } else {
                node = node.right;
            }
        }
    }
}
