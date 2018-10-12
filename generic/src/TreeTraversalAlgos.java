import trees.BSTNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeTraversalAlgos {

    public void inOrderTraversal(BSTNode node) {
        if (node == null)
            return;
        inOrderTraversal(node.left);
        visitNode(node);
        inOrderTraversal(node.right);
    }

    public void levelOrderTraversalNonRecursive(BSTNode node) {
        Queue<BSTNode> q = new LinkedList<>();
        Queue<Integer> levels = new LinkedList<>();
        q.add(node);
        levels.add(1);
        while(!q.isEmpty()) {
            BSTNode n = q.poll();
            int level = levels.poll();
            visitNodeLevel(n, level);
            if (n.left != null) {
                q.add(n.left);
                levels.add(level + 1);
            }
            if (n.right != null) {
                q.add(n.right);
                levels.add(level + 1);
            }
        }
    }

    public void preOrderTraversal(BSTNode node) {
        if (node == null)
            return;
        visitNode(node);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    public void postOrderTraversal(BSTNode node) {
        if (node == null)
            return;
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        visitNode(node);
    }

    private void visitNode(BSTNode node) {
        System.out.print(node + ", ");
    }

    private void visitNodeLevel(BSTNode node, int l) {
        System.out.print(node + " ("+ l +"), ");
    }


    public static void main(String[] args) {
        TreeTraversalAlgos obj = new TreeTraversalAlgos();
        /*
        *               1
        *              /  \
        *            2      3
        *          /  \     / \
        *        4     5   6   7
        *         \    /\      /\
        *          8  9  10  11  12
        *                /    \
        *               13    14
         */

        BSTNode root = new BSTNode(1,
                new BSTNode(2,
                        new BSTNode(4,
                                null,
                                new BSTNode(8, null, null)),
                        new BSTNode(5,
                                new BSTNode(9, null, null),
                                new BSTNode(10, new
                                        BSTNode(13, null, null), null))),
                new BSTNode(3,
                        new BSTNode(6, null, null),
                        new BSTNode(7,
                                new BSTNode(11,
                                        null, new BSTNode(14, null, null)),
                                new BSTNode(12, null, null))));

        /*
         *               1
         *              /  \
         *            2      3
         *             \       \
         *              4       5
         *                      /
         *                     6
         */
        BSTNode root2 = new BSTNode(1,
                new BSTNode(2,
                        null,
                        new BSTNode(4,null,null)),
                new BSTNode(3,
                        null,
                        new BSTNode(5,
                                new BSTNode(6, null, null),
                                null)));

        /*
         *               1
         *              /  \
         *            2      3
         *           / \
         *          4   5
         */
        BSTNode root3 = new BSTNode(1,
                new BSTNode(2,
                        new BSTNode(4,null,null),
                        new BSTNode(5,null,null)),
                new BSTNode(3, null, null));

        System.out.println("In-order traversal : ");
        obj.inOrderTraversal(root);

        System.out.println("\nLevel-order traversal : ");
        obj.levelOrderTraversalNonRecursive(root);

        System.out.println("\nPre-order traversal tree 1: ");
        obj.preOrderTraversal(root);
        System.out.println("\nPre-order traversal tree 2: ");
        obj.preOrderTraversal(root2);
        System.out.println("\nPre-order traversal tree 3: ");
        obj.preOrderTraversal(root3);

        System.out.println("\nPost-order traversal tree 1: ");
        obj.postOrderTraversal(root);
        System.out.println("\nPost-order traversal tree 2: ");
        obj.postOrderTraversal(root2);
        System.out.println("\nPost-order traversal tree 3: ");
        obj.postOrderTraversal(root3);
    }
}
