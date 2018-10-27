import trees.BST;
import trees.BSTNode;

import java.util.*;

public class TreeTraversalAlgos {

    public void inOrderTraversal(BSTNode node) {
        if (node == null)
            return;
        inOrderTraversal(node.left);
        visitNode(node);
        inOrderTraversal(node.right);
    }

    public void inOrderTraversalIterative(BSTNode node) {
        if (node == null)
            return;

        Stack<BSTNode> s = new Stack<>();
        BSTNode curr = node;

        while(!s.isEmpty() || curr != null) {
            while(curr != null) {
                s.push(curr);
                curr = curr.left;
            }
            curr = s.pop();
            visitNode(curr);
            curr = curr.right;
        }
    }

    public void levelOrderTraversalIterative(BSTNode node) {
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
    public void preOrderTraversalIterative(BSTNode node) {
        if (node == null)
            return;
        Stack<BSTNode> s = new Stack<>();
        s.push(node);
        while (!s.isEmpty()) {
            BSTNode n = s.pop();
            visitNode(n);
            if (n.right != null) s.push(n.right);
            if (n.left != null) s.push(n.left);
        }
    }

    public void postOrderTraversal(BSTNode node) {
        if (node == null)
            return;
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        visitNode(node);
    }

    public void postOrderTraversalIterative(BSTNode node) {
        if (node == null)
            return;
        Stack<BSTNode> s = new Stack<>();
        s.push(node);
        BSTNode prev = null;
        while (!s.isEmpty()) {
            BSTNode current = s.peek();
            if (prev == null || prev.left == current || prev.right == current) {
                if (current.left != null)
                    s.push(current.left);
                else if (current.right != null)
                    s.push(current.right);
                else {
                    s.pop();
                    visitNode(current);
                }
            } else if (current.left == prev) {
                if (current.right != null) s.push(current.right);
                else {
                    s.pop();
                    visitNode(current);
                }
            } else if (current.right == prev) {
                s.pop();
                visitNode(current);
            }
            prev = current;
        }
    }

    public void levelOrderTraversal2(BSTNode node) {
        Queue<BSTNode> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            BSTNode n = q.poll();
            visitNode(n);
            if (n.left != null) q.add(n.left);
            if (n.right != null) q.add(n.right);
        }
    }

    public void inorderOrderTraversal2(BSTNode node) {
        Stack<BSTNode> s = new Stack<>();
        BSTNode curr = node;
        while(!s.isEmpty() || curr != null) {
            while (curr != null) {
                s.push(curr);
                curr = curr.left;
            }
            curr = s.pop();
            visitNode(curr);
            curr = curr.right;
        }
    }

    public void preOrderTraversal2(BSTNode node) {
        Stack<BSTNode> s = new Stack<>();
        s.push(node);
        while (!s.isEmpty()) {
            BSTNode n = s.pop();
            visitNode(n);
            if (n.right != null) s.push(n.right);
            if (n.left != null) s.push(n.left);
        }
    }

    public void postOrderTraversal2(BSTNode node) {
        //this implementation uses extra set to store data for visit. Alternatively we can modify the tree and
        //remove unwanted children
        Stack<BSTNode> s = new Stack<>();
        s.push(node);
        Set<BSTNode> peeked = new HashSet();
        while(!s.isEmpty()) {
            BSTNode n = s.peek();
            if ((n.right == null && n.left == null) || peeked.contains(n)) {
                visitNode(n);
                s.pop();
            } else {
                if (n.right != null) {
                    s.push(n.right);
                    //n.right = null;
                }
                if (n.left != null){
                    s.push(n.left);
                    //n.left = null;
                }
                peeked.add(n);
            }
        }
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

        /*System.out.println("In-order traversal : ");
        obj.inOrderTraversal(root);
        System.out.println("\n");
        obj.inorderOrderTraversal2(root3);*/

        /*System.out.println("\nLevel-order traversal : ");
        obj.levelOrderTraversalIterative(root);
        System.out.println("\n new implementation");
        obj.levelOrderTraversal2(root);

        obj.levelOrderTraversalIterative(root2);
        System.out.println("\nnew implementation");
        obj.levelOrderTraversal2(root2);*/

        /*System.out.println("\nPre-order traversal tree 1: ");
        obj.preOrderTraversal(root);
        System.out.println("\nPre-order traversal tree 2: ");
        obj.preOrderTraversal(root2);
        System.out.println("\nPre-order traversal tree 3: ");
        obj.preOrderTraversal(root3);
        */
        /*System.out.println("\nPre-order traversal iterative tree 1: ");
        obj.preOrderTraversalIterative(root);
        System.out.println("\nPre-order traversal new: ");
        obj.preOrderTraversal2(root);
        System.out.println("\nPre-order traversal iterative tree 2: ");
        obj.preOrderTraversalIterative(root2);
        System.out.println("\nPre-order traversal new: ");
        obj.preOrderTraversal2(root2);
        System.out.println("\nPre-order traversal iterative tree 3: ");
        obj.preOrderTraversalIterative(root3);
        System.out.println("\nPre-order traversal new: ");
        obj.preOrderTraversal2(root3);*/

        System.out.println("\nPost-order traversal tree 1: ");
        obj.postOrderTraversal(root);
        System.out.println("\n post order new implementation");
        obj.postOrderTraversal2(root);
        System.out.println("\nPost-order traversal tree 2: ");
        obj.postOrderTraversal(root2);
        System.out.println("\n post order new implementation");
        obj.postOrderTraversal2(root2);
        System.out.println("\nPost-order traversal tree 3: ");
        obj.postOrderTraversal(root3);
        System.out.println("\n post order new implementation");
        obj.postOrderTraversal2(root3);
        /*
        System.out.println("\nPost-order traversal iterative tree 1: ");
        //obj.postOrderTraversalIterative(root);
        System.out.println("\nPost-order traversal iterative tree 2: ");
        obj.postOrderTraversalIterative(root2);
        System.out.println("\nPost-order traversal iterative tree 3: ");
        obj.postOrderTraversalIterative(root3);

        System.out.println("\nIn-order traversal iterative tree 1: ");
        obj.inOrderTraversalIterative(root);
        System.out.println("\nIn-order traversal iterative tree 2: ");
        obj.inOrderTraversalIterative(root2);*/
    }
}
