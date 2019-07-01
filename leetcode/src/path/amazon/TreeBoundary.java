package path.amazon;

import trees.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary
 * includes left boundary, leaves, and right boundary in order without duplicate nodes.
 *
 * Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from
 * root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left
 * boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.
 *
 * The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree
 * if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.
 *
 * The right-most node is also defined by the same way with left and right exchanged.
 *
 * Example 1
 * Input:
 *   1
 *    \
 *     2
 *    / \
 *   3   4
 *
 * Ouput:
 * [1, 3, 4, 2]
 *
 * Explanation:
 * The root doesn't have left subtree, so the root itself is left boundary.
 * The leaves are node 3 and 4.
 * The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
 * So order them in anti-clockwise without duplicates and we have [1,3,4,2].
 * Example 2
 * Input:
 *     ____1_____
 *    /          \
 *   2            3
 *  / \          /
 * 4   5        6
 *    / \      / \
 *   7   8    9  10
 *
 * Ouput:
 * [1,2,4,7,8,9,10,6,3]
 *
 * Explanation:
 * The left boundary are node 1,2,4. (4 is the left-most node according to definition)
 * The leaves are node 4,7,8,9,10.
 * The right boundary are node 1,3,6,10. (10 is the right-most node).
 * So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
 *
 */
public class TreeBoundary {
    /**
     * Sol2 based on following:
     * - first get left boundary
     * - get tree leaves to the left
     * - get tree leaves to the right
     * - get right boundary
     *
     * Notes:
     * - start from the second node, so root does not appear twice. Add root separately in the beginning
     * - when traversing left part first add node then to recursive call - this will ensure up-bottom order
     * when traversing right part - do recursion first then add node - this will ensure bottom-up order
     *
     * @param root
     * @return
     */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new LinkedList();
        if (root != null) {
            //add root
            res.add(root.val);
            //get left part (without root)
            getLeftPart(root.left, res);
            //get left part leaves
            getLeaves(root.left, res);
            //get right part leaves
            getLeaves(root.right, res);
            //get right boundary bottom-up without root
            getRightPart(root.right, res);
        }
        return res;
    }

    void getLeftPart(TreeNode n, List<Integer> res) {
        if (n != null) {
            if (n.left != null) {
                res.add(n.val);
                getLeftPart(n.left, res);
            } else if (n.right != null) {
                res.add(n.val);
                getLeftPart(n.right, res);
            }
        }
    }

    void getLeaves(TreeNode n, List<Integer> res) {
        if (n != null) {
            getLeaves(n.left, res);
            //this is how we know it's a leaf
            if (n.right == null && n.left == null)
                res.add(n.val);
            getLeaves(n.right, res);
        }
    }

    void getRightPart(TreeNode n, List<Integer> res) {
        if (n != null) {
            if (n.right != null) {
                getRightPart(n.right, res);
                res.add(n.val);
            } else if (n.left != null) {
                getRightPart(n.left, res);
                res.add(n.val);
            }
        }
    }
}
