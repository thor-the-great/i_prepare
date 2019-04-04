package random_problems;

import diff_problems.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 894. All Possible Full Binary Trees
 * Medium
 *
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 *
 * Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one
 * possible tree.
 *
 * Each node of each tree in the answer must have node.val = 0.
 *
 * You may return the final list of trees in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: 7
 * Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,
 * null,0,0],[0,0,0,0,0,null,null,0,0]]
 * Explanation:
 *
 * Note:
 *
 * 1 <= N <= 20
 */
public class AllPossibleFullBinaryTree {

    Map<Integer, List<TreeNode>> m = new HashMap();

    /**
     * Idea: recursively calculate each subtree depends on N value, cashing prev values
     * @param N
     * @return
     */
    public List<TreeNode> allPossibleFBT(int N) {
        if (!m.containsKey(N)) {
            List<TreeNode> res = new ArrayList();
            if (N == 1) {
                res.add(new TreeNode(0));
            } else if (N % 2 == 1) {
                for (int i = 0; i < N; i++) {
                    int j = N - 1 - i;
                    List<TreeNode> leftSub = allPossibleFBT(i);
                    List<TreeNode> rightSub = allPossibleFBT(j);
                    for (TreeNode l : leftSub) {
                        for (TreeNode r : rightSub) {
                            TreeNode subTreeRoot = new TreeNode(0);
                            subTreeRoot.left = l;
                            subTreeRoot.right = r;
                            res.add(subTreeRoot);
                        }
                    }
                }
            }
            m.put(N, res);
        }
        return m.get(N);
    }
}
