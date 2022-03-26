package map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import trees.TreeNode;

/**
 * 2196. Create Binary Tree From Descriptions
Medium

You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,

If isLefti == 1, then childi is the left child of parenti.
If isLefti == 0, then childi is the right child of parenti.
Construct the binary tree described by descriptions and return its root.

The test cases will be generated such that the binary tree is valid.

 

Example 1:


Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
Output: [50,20,80,15,17,19]
Explanation: The root node is the node with value 50 since it has no parent.
The resulting binary tree is shown in the diagram.
Example 2:


Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
Output: [1,2,null,null,3,4]
Explanation: The root node is the node with value 1 since it has no parent.
The resulting binary tree is shown in the diagram.
 

Constraints:

1 <= descriptions.length <= 104
descriptions[i].length == 3
1 <= parenti, childi <= 105
0 <= isLefti <= 1
The binary tree described by descriptions is valid.

https://leetcode.com/problems/create-binary-tree-from-descriptions/
 */
public class CreateBinaryTreeFromDescriptions {
    
    public TreeNode createBinaryTree(int[][] descriptions) {
        Set<Integer> children = new HashSet();
        Map<Integer, TreeNode> nodes = new HashMap();
        
        for (int[] description : descriptions) {
            int parent = description[0], child = description[1];
            boolean isLeft = description[2] == 1;
            
            if (!nodes.containsKey(parent)) {
                nodes.put(parent, new TreeNode(parent));
            }
            
            TreeNode parentNode = nodes.get(parent);
            
            if (!nodes.containsKey(child)) {
                nodes.put(child, new TreeNode(child));
            }
            
            TreeNode childNode = nodes.get(child);
            
            if (isLeft) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }
            
            children.add(child);
        }
        
        
        for (int[] description : descriptions) {
            if (!children.contains(description[0])) {
                return nodes.get(description[0]);
            }
        }
        
        return null;
    }
}
