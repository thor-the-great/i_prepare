package binary_tree;

import trees.BSTNode;


public class MorrisTraversals {

    public void inorderTraversal(BSTNode root) {
        if (root == null)
            return;

        BSTNode node = root;
        while (node != null) {
            //if no left children = visit this node and go to the right subtree
            if (node.left == null) {
                visitNode(node);
                node = node.right;
            } else {
                //find rightmost leaf of root left node
                BSTNode pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }

                //pre.right has been set, go to the current node
                if (pre.right == node) {
                    //this means the left most node's right points to the current node, so
                    //we have reached the temporary link that we've established before in else condition
                    //and left substree has been already visited
                    pre.right = null;
                    visitNode(node);
                    node = node.right;
                } else {
                    //first time visit pre node, make its right child point to the
                    //current node (save the link, will remove it later)
                    pre.right = node;
                    node = node.left;
                }
            }
        }
    }

    void inorderTraversal2(BSTNode root) {
        if (root == null) {
            return;
        }

        BSTNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                visitNode(cur);
                cur = cur.right;
            } else {
                //looking for predecessor node - take the right most of the left child of the current node
                BSTNode predecessor = cur.left;
                while (predecessor.right != null && predecessor.right != cur) {
                    predecessor = predecessor.right;
                }
                //checking if we stoped at the the current node, if :
                // - yes : we have visited this path in the past, need to break the temporary link
                // - no  : we haven't visited the path, need to build temporary link from predecessor to the current node to be able to come back later
                if (predecessor.right == cur) {
                    predecessor.right = null;
                    visitNode(cur);
                    cur = cur.right;
                } else {
                    predecessor.right = cur;
                    cur = cur.left;
                }
            }
        }
     }

    private void visitNode(BSTNode node) {
        System.out.print(node + ", ");
    }

    public static void main(String[] args) {
        BSTNode root = new BSTNode(4,
                new BSTNode(2,
                        new BSTNode(1,null,null),
                        new BSTNode(3,null,null)),
                new BSTNode(6,
                        new BSTNode(5, null, null),
                        null));

        MorrisTraversals obj = new MorrisTraversals();
        obj.inorderTraversal2(root);
    }
}
