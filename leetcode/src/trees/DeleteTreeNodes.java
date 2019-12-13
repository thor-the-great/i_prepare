package trees;

/**
 * 1273. Delete Tree Nodes
 * Medium
 *
 * A tree rooted at node 0 is given as follows:
 *
 * The number of nodes is nodes;
 * The value of the i-th node is value[i];
 * The parent of the i-th node is parent[i].
 * Remove every subtree whose sum of values of nodes is zero.
 *
 * After doing so, return the number of nodes remaining in the tree.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nodes <= 10^4
 * -10^5 <= value[i] <= 10^5
 * parent.length == nodes
 * parent[0] == -1 which indicates that 0 is the root.
 */
public class DeleteTreeNodes {

    /**
     * Going from the leafs (revert DFS) and count sum of each sub-tree, save values in original value array.
     * Create new array of nodes that we met that are not sum to 0. Increment count of nodes, if 0 met - reset it to 0
     * @param nodes
     * @param parent
     * @param value
     * @return
     */
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        int[] n = new int[nodes];

        for (int i = value.length - 1; i > 0; i--) {
            value[parent[i]] += value[i];
            if (value[i] != 0) {
                n[parent[i]] += (n[i] + 1);
            }
        }
        if (value[0] == 0)
            return 0;
        else
            return n[0] + 1;
    }
}
