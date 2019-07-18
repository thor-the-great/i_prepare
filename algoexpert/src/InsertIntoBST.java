import trees.TreeNode;

/**
 * Insert a noew into BST
 */
public class InsertIntoBST {
    public TreeNode insert(TreeNode root, int data) {
        if (root == null)
            return new TreeNode(data);

        if (data <= root.val) {
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }

        return root;
    }
}
