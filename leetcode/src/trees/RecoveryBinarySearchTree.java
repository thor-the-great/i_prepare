package trees;
/**
 *     /**
     * 99. Recover Binary Search Tree
Medium

You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.

 

Example 1:


Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
Example 2:


Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 

Constraints:

The number of nodes in the tree is in the range [2, 1000].
-231 <= Node.val <= 231 - 1
 

Follow up: A solution using O(n) space is pretty straight-forward. Could you devise a constant O(1) space solution?
 * https://leetcode.com/problems/recover-binary-search-tree/
 */
public class RecoveryBinarySearchTree {
    
    /**
     * Idea - if we do inorder traversal we can spot nodes to switch by checking their values with previous node, 
     * if it has been switched values will be desc (normally - asc)
     * 
     * We can do inorder traversal in O(n) and O(1) space with Morris algorithm.
     * 
     */
    public void recoverTree(TreeNode root) {
        TreeNode first = null, second = null;
        TreeNode prev = null;
        
        while (root != null) {
            if (root.left == null) {
                //visit
                if (prev != null && prev.val > root.val) {
                    if (first == null) {
                        first = prev;
                        second = root;
                    } else {
                        second = root;
                    }
                }
                prev = root;
                
                root = root.right;
            } else {
                TreeNode predec = root.left;
                while (predec.right != null && predec.right != root) {
                    predec = predec.right;
                }
                //visited path
                if (predec.right == root) {
                    predec.right = null;
                    //visit
                    if (prev != null && prev.val > root.val) {
                        if (first == null) {
                            first = prev;
                            second = root;
                        } else {
                            second = root;
                        }
                    }
                    prev = root;
                    
                    root = root.right;
                } else {
                    predec.right = root;
                    root = root.left;
                }
            }
        }
        
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
