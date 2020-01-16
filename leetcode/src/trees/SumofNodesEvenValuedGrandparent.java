package trees;

/**
 * 1315. Sum of Nodes with Even-Valued Grandparent
 * Medium
 *
 * Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A
 * grandparent of a node is the parent of its parent, if it exists.)
 *
 * If there are no nodes with an even-valued grandparent, return 0.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 18
 * Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are
 * the even-value grandparents.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is between 1 and 10^4.
 * The value of nodes is between 1 and 100.
 */
public class SumofNodesEvenValuedGrandparent {

    /**
     * Do recursion, for each call pass parent and grandparent. On each next call current node
     * became parent, parent became grandparent
     * @param root
     * @return
     */
    public int sumEvenGrandparent(TreeNode root) {
        return helper(root, null, null);
    }

    int helper(TreeNode cur, TreeNode par, TreeNode grand) {
        //base case - if we reach the end of the branch - return 0 and terminate
        if (cur == null)
            return 0;
        int res = 0;
        //if there is a grandparent and it's even
        if (grand != null && grand.val % 2 == 0) {
            res += cur.val;
        }
        //visit children, for each child current node became a parent, it's parent became
        //a grandparent
        res+=helper(cur.left, cur, par);
        res+=helper(cur.right, cur, par);
        return res;
    }
}
