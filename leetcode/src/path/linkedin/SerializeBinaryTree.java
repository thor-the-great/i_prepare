package path.linkedin;

import diff_problems.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 297. Serialize and Deserialize Binary Tree
 * Hard
 *
 *
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized
 * to a string and this string can be deserialized to the original tree structure.
 *
 * Example:
 *
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need
 * to follow this format, so please be creative and come up with different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 *
 */
public class SerializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //use level-order traversal
        Queue<TreeNode> q = new LinkedList();
        StringBuilder sb = new StringBuilder();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            if (n == null) {
                sb.append("n");
            } else {
                sb.append(n.val);
                q.add(n.left);
                q.add(n.right);
            }
            sb.append(",");
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty())
            return null;
        String[] vals = data.split(",");
        int idx = 0;
        TreeNode root = getNode(vals[idx]);
        idx++;
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        while (idx < vals.length) {
            TreeNode n = q.poll();
            TreeNode left = getNode(vals[idx++]);
            TreeNode right = getNode(vals[idx++]);
            if (left != null) {
                n.left = left;
                q.add(left);
            }
            if (right != null) {
                n.right = right;
                q.add(right);
            }
        }
        return root;
    }

    private TreeNode getNode(String val) {
        if (val.equals("n"))
            return null;
        TreeNode n = new TreeNode(Integer.parseInt(val));
        return n;
    }
}
