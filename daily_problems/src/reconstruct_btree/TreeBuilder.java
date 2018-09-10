package reconstruct_btree;

/**
 * This problem was asked by Google.
 *
 * Given pre-order and in-order traversals of a binary tree, write a function to reconstruct the tree.
 *
 * For example, given the following preorder traversal:
 *
 * [a, b, d, e, c, f, g]
 *
 * And the following inorder traversal:
 *
 * [d, b, e, a, f, c, g]
 *
 * You should return the following tree:
 *
 *     a
 *    / \
 *   b   c
 *  / \ / \
 * d  e f  g
 */
class Node {
    char val;
    Node[] children = new Node[2];
}

public class TreeBuilder {

    int prePointer = 0;

    Node getBinaryTree(char[] preorder, char[] inorder) {
        //roots of subtree are located in preorder array sequentially, so iterating by preorder incrementing pointer gives
        //us roots. Then we locate root of subtree in inorder and divide array into two halves. This is root for this subtree
        //and recursively call the same method for each sub-array we can build our tree.
        //edge (or base) cases -
        // - increment preorder pointer globally
        // - if start == end of sub-array search - just return node
        // - if start > end - return null
        prePointer = 0;
        return searchInternal(preorder, inorder, 0, inorder.length - 1);
    }

    Node searchInternal(char[] preorder, char[] inorder, int inOrderLeft, int inOrderRight) {
        if (inOrderLeft > inOrderRight) {
            return null;
        }
        prePointer++;
        Node node = new Node();
        node.val = preorder[prePointer];
        if (inOrderLeft == inOrderRight) {
            return node;
        }
        int inIndex = search(inorder, inOrderLeft, inOrderRight, node.val);
        node.children[0] = searchInternal(preorder, inorder, inOrderLeft, inIndex - 1);
        node.children[1] = searchInternal(preorder, inorder, inIndex + 1, inOrderRight);
        return node;
    }

    int search(char[] inorder, int start, int end, int val) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }

    /* This funtcion is here just to test buildTree() */
    void printInorder(Node node)
    {
        if (node == null)
            return;
        /* first recur on left child */
        printInorder(node.children[0]);
        /* then print the data of node */
        System.out.print(node.val + " ");
        /* now recur on right child */
        printInorder(node.children[1]);
    }

    public static void main (String[] args) {
        TreeBuilder obj = new TreeBuilder();

        char[] preorder = new char[] {'a', 'b', 'd', 'e', 'c', 'f', 'g'};
        char[] inorder = new char[] {'d', 'b', 'e','a', 'f', 'c', 'g'};
        Node bTree = null;
        bTree = obj.getBinaryTree(preorder, inorder);
        System.out.println("\n--- in order traversal -----");
        obj.printInorder(bTree);

        preorder = new char[] {'A', 'B', 'D', 'E', 'C', 'F'};
        inorder = new char[] {'D', 'B' ,'E' ,'A', 'F', 'C'};

        bTree = obj.getBinaryTree(preorder, inorder);
        System.out.println("\n--- in order traversal -----");
        obj.printInorder(bTree);
    }
}