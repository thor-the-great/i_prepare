package recursion;

/**
 * 331. Verify Preorder Serialization of a Binary Tree
Medium

One way to serialize a binary tree is to use preorder traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as '#'.

For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where '#' represents a null node.

Given a string of comma-separated values preorder, return true if it is a correct preorder traversal serialization of a binary tree.

It is guaranteed that each comma-separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid.

    For example, it could never contain two consecutive commas, such as "1,,3".

Note: You are not allowed to reconstruct the tree.

 

Example 1:

Input: preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#"
Output: true

Example 2:

Input: preorder = "1,#"
Output: false

Example 3:

Input: preorder = "9,#,#,1"
Output: false

 

Constraints:

    1 <= preorder.length <= 104
    preorder consist of integers in the range [0, 100] and '#' separated by commas ','.

https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
 */
public class VerifyPreorderSerialization {
    int idx = 0;
    
    /**
     * Idea - simulate building of the tree. Base case - index reached
     * end of array but we haven't done yet - it's false;
     * next check if token is "#", if yes - return true. For node if both
     * children are true return true to parent as this subtree is valid
     * At the end check if returned value is true and index is exactly at the last
     * position
     * 
     * O(n) time - check every element
     * O(n) space - for call stack
     */
    public boolean isValidSerialization(String preorder) {
        String[] tokens = preorder.split(",");
        return check(tokens) && idx == tokens.length;
    }
    
    boolean check(String[] tokens) {
        if (idx >= tokens.length) {
            return false;
        }
        String next = tokens[idx++];
        if (next.equals("#")) {
            return true;
        }
        if (check(tokens) && check(tokens)) {
            return true;
        }
        return false;
    }
}
