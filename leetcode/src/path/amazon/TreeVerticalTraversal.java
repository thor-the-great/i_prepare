package path.amazon;

import diff_problems.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Binary Tree Vertical Order Traversal
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by
 * column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Examples 1:
 *
 * Input: [3,9,20,null,null,15,7]
 *
 *    3
 *   /\
 *  /  \
 *  9  20
 *     /\
 *    /  \
 *   15   7
 *
 * Output:
 *
 * [
 *   [9],
 *   [3,15],
 *   [20],
 *   [7]
 * ]
 * Examples 2:
 *
 * Input: [3,9,8,4,0,1,7]
 *
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *
 * Output:
 *
 * [
 *   [4],
 *   [9],
 *   [3,0,1],
 *   [8],
 *   [7]
 * ]
 * Examples 3:
 *
 * Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
 *
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *     /\
 *    /  \
 *    5   2
 *
 * Output:
 *
 * [
 *   [4],
 *   [9,5],
 *   [3,0,1],
 *   [8,2],
 *   [7]
 * ]
 *
 */
public class TreeVerticalTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if (root == null)
            return res;
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        Queue<Integer> cols = new LinkedList();
        cols.add(0);
        int minIndex = 0;
        res.add(new ArrayList());
        while(!q.isEmpty()) {
            TreeNode n = q.poll();
            int col = cols.poll();
            int listIndex;
            if (minIndex > col) {
                res.add(0, new ArrayList());
                minIndex = col;
                listIndex = 0;
            }
            else {
                listIndex = Math.abs(minIndex) + col;
                if (listIndex >= res.size())
                    res.add(new ArrayList());
            }
            res.get(listIndex).add(n.val);
            if (n.left != null) {
                q.add(n.left);
                cols.add(col - 1);
            }
            if (n.right != null) {
                q.add(n.right);
                cols.add(col + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {

        TreeVerticalTraversal obj = new TreeVerticalTraversal();
        TreeNode root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15), new TreeNode(7)));
        List<List<Integer>> res = obj.verticalOrder(root);
        for(List<Integer> onelevel : res) {
            onelevel.forEach(i-> System.out.print(i + " "));
            System.out.print(";\n");
        }
    }
}
