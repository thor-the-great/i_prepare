package diff_problems;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 *
 * Idea is to keep a global Queue of traversal, compute it till the last required element in constructor
 * Then on each insert keep traversing queue using last element
 */
class CBTInserter {
    TreeNode root;
    Queue<TreeNode> nodesToProcess = new LinkedList<>();

    public CBTInserter(TreeNode root) {
        nodesToProcess.clear();
        this.root = root;
        nodesToProcess.add(root);
        while (!nodesToProcess.isEmpty()) {
            TreeNode actualNode = nodesToProcess.peek();
            if (actualNode.left == null || actualNode.right == null) {
                if (actualNode.left != null )
                    nodesToProcess.add(actualNode.left);
                break;
            }
            else {
                actualNode = nodesToProcess.poll();
                nodesToProcess.add(actualNode.left);
                nodesToProcess.add(actualNode.right);
            }
        }
    }

    public int insert(int v) {
        TreeNode actualNode = nodesToProcess.peek();
        // Left child has precedence over right one
        if (actualNode.left == null) {
            actualNode.left = new TreeNode(v);
            nodesToProcess.add(actualNode.left);
            return actualNode.val;
        }
        if (actualNode.right == null) {
            actualNode = nodesToProcess.poll();
            actualNode.right = new TreeNode(v);
            nodesToProcess.add(actualNode.right);
            return actualNode.val;
        }
        return 0;
    }

    public TreeNode get_root() {
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        root.left = two;
        //root.right = three;
        //two.left = four;
        //two.right = five;

        CBTInserter obj = new CBTInserter(root);
        //obj.insert(6);
        obj.insert(3);
        obj.insert(4);

    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(v);
 * TreeNode param_2 = obj.get_root();
 */