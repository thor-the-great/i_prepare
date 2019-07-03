package random_problems;

import trees.TreeNode;

import java.util.*;

/**
 * 987. Vertical Order Traversal of a Binary Tree
 * Medium
 *
 * Given a binary tree, return the vertical order traversal of its nodes values.
 *
 * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and
 * (X+1, Y-1).
 *
 * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we
 * report the values of the nodes in order from top to bottom (decreasing Y coordinates).
 *
 * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 *
 * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Without loss of generality, we can assume the root node is at position (0, 0):
 * Then, the node with value 9 occurs at position (-1, -1);
 * The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
 * The node with value 20 occurs at position (1, -1);
 * The node with value 7 occurs at position (2, -2).
 * Example 2:
 *
 *
 *
 * Input: [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * The node with value 5 and the node with value 6 have the same position according to the given scheme.
 * However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 *
 *
 * Note:
 *
 * The tree will have between 1 and 1000 nodes.
 * Each node's value will be between 0 and 1000.
 *
 */
public class VerticalOrderOfBinaryTree {

    Map<Integer, List<NodeObject>> map;
    int min = 0;
    int max = 0;

    /**
     * Idea: do the DFS, keep track of X and Y. Save list of same X-s to a map (key is the X coordinate). Then sort
     * all values with same X as per custom comparator
     * @param root
     * @return
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if (root == null)
            return res;
        map = new HashMap();
        dfs(root, 0, 0);
        Comparator<NodeObject> comp = (NodeObject o1, NodeObject o2)-> {
            if (o1.Y == o2.Y) {
                return Integer.compare(o1.val, o2.val);
            } else {
                return Integer.compare(o1.Y, o2.Y);
            }
        };

        for (int i = min; i <=max; i++) {
            List<NodeObject> column = map.get(i);
            Collections.sort(column, comp);

            List<Integer> columnRes = new ArrayList();
            for (NodeObject obj : column) {
                columnRes.add(obj.val);
            }
            res.add(columnRes);
        }
        return res;
    }

    void dfs(TreeNode n, int X, int Y) {

        if (!map.containsKey(X)) {
            map.put(X, new ArrayList());
        }

        NodeObject obj = new NodeObject(Y, n.val);
        map.get(X).add(obj);

        if (n.left != null) {
            min = Math.min(min, X - 1);
            dfs(n.left, X - 1, Y + 1);
        }

        if (n.right != null) {
            max = Math.max(max, X + 1);
            dfs(n.right, X + 1, Y + 1);
        }
    }

    class NodeObject {
        int Y = 0, val = 0;

        NodeObject (int Y, int val) {
            this.Y = Y;
            this.val = val;
        }
    }
}
