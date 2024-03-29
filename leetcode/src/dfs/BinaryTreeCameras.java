/**
 * 968. Binary Tree Cameras
Hard

Given a binary tree, we install cameras on the nodes of the tree. 

Each camera at a node can monitor its parent, itself, and its immediate children.

Calculate the minimum number of cameras needed to monitor all nodes of the tree.

 

Example 1:

Input: [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.

Example 2:

Input: [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.


Note:

    The number of nodes in the given tree will be in the range [1, 1000].
    Every node has value 0.

https://leetcode.com/problems/binary-tree-cameras/

 */
public class BinaryTreeCameras {
    int res = 0;
    
    /**
     * Idea - working down up in greedy manner. Nodes are counted as not covered if :
     * - at least one of children is not covered
     * - if node has no parent and is not covered
     * reuse the tree itselt to keep track of covered nodes
     * 
     * O(n) time
     * O(lgn) space (if tree is balanced)
     */
    public int minCameraCover(TreeNode root) {
        helper(root, null);
        return res;
    }
    
    void helper(TreeNode cur, TreeNode par) {
        if (cur == null) {
            return;
        }
        
        helper(cur.left, cur);
        helper(cur.right, cur);
        
        if (par == null && cur.val == 0 
            || (cur.left != null && cur.left.val == 0) 
            || (cur.right != null && cur.right.val == 0)) {
            ++res;
            cur.val = 1;
            if (cur.left != null)
                cur.left.val = 1;
            if (cur.right != null)
                cur.right.val = 1;
            if (par != null)
                par.val = 1;
        }
    }    
}
