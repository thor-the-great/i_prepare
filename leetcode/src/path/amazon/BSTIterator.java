package path.amazon;

import trees.TreeNode;

import java.util.Stack;

/**
 * Binary Search Tree Iterator
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 *
 * Note: next() and hasNext() should run in average O(1) time and uses O(helper2) memory, where helper2 is the height of the tree.
 */
public class BSTIterator {

    Stack<TreeNode> s;
    TreeNode cur;

    public BSTIterator(TreeNode root) {
        s = new Stack();
        cur = root;
    }

    /** @return the next smallest number */
    public int next() {
        if (!s.isEmpty() || cur != null) {
            while(cur != null) {
                s.push(cur);
                cur = cur.left;
            }
            TreeNode n = s.pop();
            cur = n.right;
            return n.val;
        }
        return 0;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !s.isEmpty() || cur != null;
    }
}
