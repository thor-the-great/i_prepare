import trees.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Reverse Level Order Traversal New
 *     Trees Stacks Queues
 * Traverse a given binary tree in the Reverse Level Order. Mark a node as visited by adding its data to an
 * ArrayList which will be returned.
 *
 * Example:
 *      1
 *     / \
 *    2   3
 *   / \ / \
 *  4  5 6  7
 *
 * Output => 4567231
 */
public class ReverseLevelOrderTraversal {

    /**
     * Do normal iterative level-order with the catch of adding right and left. Add nodes to stack, then pop elements
     * from stack to resulting list
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> levelorderRev(TreeNode root) {

        Stack<TreeNode> s = new Stack();
        Queue<TreeNode> q = new LinkedList();
        ArrayList<Integer> res = new ArrayList();
        if (root != null) {
            q.add(root);
        }

        while(!q.isEmpty()) {
            TreeNode n = q.poll();
            s.push(n);
            if (n.right != null) {
                q.add(n.right);
            }
            if (n.left != null) {
                q.add(n.left);
            }
        }

        while(!s.isEmpty()) {
            res.add(s.pop().val);
        }

        return res;
    }
}
