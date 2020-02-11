package trees;

/**
 * 865. Smallest Subtree with all the Deepest Nodes
 * Medium
 * <p>
 * Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.
 * <p>
 * A node is deepest if it has the largest depth possible among any node in the entire tree.
 * <p>
 * The subtree of a node is that node, plus the set of all descendants of that node.
 * <p>
 * Return the node with the largest depth such that it contains all the deepest nodes in its subtree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [3,5,1,6,2,0,8,null,null,7,4]
 * Output: [2,7,4]
 * Explanation:
 * <p>
 * <p>
 * <p>
 * We return the node with value 2, colored in yellow in the diagram.
 * The nodes colored in blue are the deepest nodes of the tree.
 * The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
 * The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
 * Both the input and output have TreeNode type.
 * <p>
 * <p>
 * Note:
 * <p>
 * The number of nodes in the tree will be between 1 and 500.
 * The values of each node are unique.
 */
public class SmallestSubtreeWithAllDeepestNodes {

    /**
     * Recursive approach
     * @param root
     * @return
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null)
            return null;

        return helper(root, 0).n;
    }

    Res helper(TreeNode n, int d) {
        if (n == null)
            return new Res(null, 0);

        Res left = helper(n.left, d + 1);
        Res right = helper(n.right, d + 1);

        if (left.d > right.d)
            return new Res(left.n, left.d + 1);
        if (left.d < right.d)
            return new Res(right.n, right.d + 1);
        return new Res(n, left.d + 1);
    }
}

class Res {
    TreeNode n;
    int d = 0;

    Res(TreeNode node, int d) {
        this.n = node;
        this.d = d;
    }
}
