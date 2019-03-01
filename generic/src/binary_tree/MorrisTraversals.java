package binary_tree;

import trees.BSTNode;


public class MorrisTraversals {

    public void inorderTraversal(BSTNode root) {
        if (root == null)
            return;

        while (root != null) {
            //if no left children = visit this node and go to the right subtree
            if (root.left == null) {
                visitNode(root);
                root = root.right;
            } else {
                //find rightmost leaf of root left node
                BSTNode pre = root.left;
                while (pre.right != null && pre.right != root) {
                    pre = pre.right;
                }

                //pre.right has been set, go to the current node
                if (pre.right == root) {
                    //this means current node pointer by the left right most child
                    //left substree has been visited
                    pre.right = null;
                    visitNode(root);
                    root = root.right;
                } else {
                    //first time visit pre node, make its right chil point to the
                    //current node (save the link, will remove it later)
                    pre.right = root;
                    root = root.left;
                }
            }
        }
    }

    private void visitNode(BSTNode node) {
        System.out.print(node + ", ");
    }

    public static void main(String[] args) {
        BSTNode root3 = new BSTNode(1,
                new BSTNode(2,
                        new BSTNode(4,null,null),
                        new BSTNode(5,null,null)),
                new BSTNode(3,
                        new BSTNode(6, null, null),
                        null));

        MorrisTraversals obj = new MorrisTraversals();
        obj.inorderTraversal(root3);
    }
}
