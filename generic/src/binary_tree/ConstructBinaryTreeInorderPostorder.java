package binary_tree;

import trees.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeInorderPostorder {
    int[] inorder;
    int[] postorder;
    Map<Integer, Integer> idxMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length)
            return null;

        idxMap = new HashMap();
        for (int i =0; i < inorder.length; i++) {
            idxMap.put(inorder[i], i);
        }
        this.inorder = inorder;
        this.postorder = postorder;
        return helper(0, inorder.length - 1, 0, postorder.length - 1);
    }

    TreeNode helper(int is, int ie, int ps, int pe) {
        if (ps > pe || is > ie)
            return null;

        TreeNode root = new TreeNode(postorder[pe]);
        int rootIdx = idxMap.get(root.val);

        root.left = helper(is, rootIdx - 1, ps, ps + rootIdx - is - 1);

        root.right = helper(rootIdx + 1, ie, ps + rootIdx - is, pe - 1);

        return root;
    }
}
