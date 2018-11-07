package mock_sessions.amazon;

import diff_problems.TreeNode;

import java.util.Stack;

/**
 * Binary Search Tree Iterator
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 *
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */
public class BSTIterator {

    Stack<TreeNode> s;

    /**
     * Idea is to do inorder traversal in steps. At each step you can add max h (height of the BST) elements
     *
     * @param root
     */
    public BSTIterator(TreeNode root) {
        s = new Stack();
        TreeNode curr = root;
        while (curr != null) {
            s.push(curr);
            curr = curr.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !s.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode n = s.pop();
        int next = n.val;
        if (n.right != null) {
            n = n.right;
            while ( n != null) {
                s.push(n);
                n = n.left;
            }
        }
        return next;
    }
}
