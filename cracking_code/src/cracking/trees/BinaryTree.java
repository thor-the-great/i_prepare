package cracking.trees;

import sun.reflect.generics.tree.Tree;

/**
 * Created by thor on 12/10/16.
 */
public class BinaryTree {

    static class TreeNode {
        String data; // data inside node

        TreeNode left; // left child
        TreeNode right; // right child
        TreeNode parent;

        TreeNode(String data, TreeNode parent) {
            this.data = data;
            this.parent = parent;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        void print() {
            System.out.println(data);
        }
        void debug() {
            System.out.println(" " + left.data + "-" + data + "-" + right.data);
        }
    }

    TreeNode root;

    public BinaryTree() {
    }

    void inOrder(TreeNode subTree) {
        if (subTree !=  null) {
            inOrder(subTree.left);
            subTree.print();
            inOrder(subTree.right);
        }
    }

    void preOrder(TreeNode subTree) {
        if (subTree !=  null) {
            subTree.print();
            preOrder(subTree.left);
            preOrder(subTree.right);
        }
    }

    void postOrder(TreeNode subTree) {
        if (subTree !=  null) {
            postOrder(subTree.left);
            postOrder(subTree.right);
            subTree.print();
        }
    }


    TreeNode successor(TreeNode n) {
        //if there is a right child for this node
        if (n.right != null)
            return getMinNode(n.right);

        TreeNode parentNode = n.parent;
        while (parentNode != null && n == parentNode.right) {
            n = parentNode;
            parentNode = parentNode.parent;
        }
        return parentNode;
    }

    TreeNode predecessor(TreeNode n) {
        if (n.left != null)
            return getMaxNode(n.left);

        TreeNode parentNode = n.parent;
        while (parentNode != null && n == parentNode.left) {
            n = parentNode;
            parentNode = parentNode.parent;
        }
        return parentNode;
    }

    TreeNode getMinNode(TreeNode n) {
        TreeNode tmpNode = n;
        while (tmpNode.left != null) {
            tmpNode = tmpNode.left;
        }
        return tmpNode;
    }

    TreeNode getMaxNode(TreeNode n) {
        TreeNode tmpNode = n;
        while (tmpNode.right != null) {
            tmpNode = tmpNode.right;
        }
        return tmpNode;
    }

    static BinaryTree createSampleTree() {
        // creates tree in video in step D and returns it
        BinaryTree t = new BinaryTree();
        TreeNode root = new TreeNode("Les", null);
        t.root = root;

        TreeNode cathy = new TreeNode("Cathy", root);
        root.setLeft(cathy);
        TreeNode sam = new TreeNode("Sam", root);
        root.setRight(sam);

        TreeNode alex = new TreeNode("Alex", cathy);
        cathy.setLeft(alex);
        TreeNode frank = new TreeNode("Frank", cathy);
        cathy.setRight(frank);
        //
        TreeNode nancy = new TreeNode("Nancy", sam);
        sam.setLeft(nancy);
        TreeNode violet = new TreeNode("Violet", sam);
        sam.setRight(violet);

        TreeNode tony = new TreeNode("Tony", violet);
        violet.setLeft(tony);
        TreeNode wendy = new TreeNode("Wendy", violet);
        violet.setRight(wendy);
        return t;
    }

    public static void main(String[] args) {
        BinaryTree tree = BinaryTree.createSampleTree();
        System.out.println("In-order traversal ");
        tree.inOrder(tree.root);

        System.out.println("Pre-order traversal ");
        tree.preOrder(tree.root);

        System.out.println("Post-order traversal ");
        tree.postOrder(tree.root);

        TreeNode nodeToSearch = tree.root;
        System.out.println("Successor of " + nodeToSearch.data + " is " + tree.successor(nodeToSearch).data);

        nodeToSearch = tree.root.getRight().getRight();
        System.out.println("Successor of " + nodeToSearch.data + " is " + tree.successor(nodeToSearch).data);

        nodeToSearch = tree.root;
        System.out.println("Predecessor of " + nodeToSearch.data + " is " + tree.predecessor(nodeToSearch).data);

        nodeToSearch = tree.root.getRight().getRight();
        System.out.println("Predecessor of " + nodeToSearch.data + " is " + tree.predecessor(nodeToSearch).data);

    }
}
