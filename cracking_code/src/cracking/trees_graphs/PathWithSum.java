package cracking.trees_graphs;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by thor on 1/18/17.
 */
public class PathWithSum {

    int checkPathForSum(BinarySearchTree<Integer, Integer> tree, int sum) {
        int pathCount = 0;
        Stack<BinarySearchTree.Node> stack = new Stack<>();
        stack.push(tree.root);
        while (!stack.isEmpty()) {
            BinarySearchTree.Node tmpNode = stack.pop();
            pathCount = pathCount + getPathCountForNode(sum, tmpNode);
            if (tmpNode.left != null)
                stack.push(tmpNode.left);
            if (tmpNode.right != null)
                stack.push(tmpNode.right);
        }
        return pathCount;
    }

    private int getPathCountForNode(int sum, BinarySearchTree.Node node) {
        ArrayList<Integer> pathCount = new ArrayList<>();
        pathCount.add(0);
        visitNode(node, 0, sum, pathCount);
        return pathCount.get(0);
    }

    void visitNode(BinarySearchTree<Integer, Integer>.Node node, int pathSum, int pathToCheck, ArrayList<Integer> pathCount) {
        int newPathSum = pathSum + node.value;
        if (newPathSum == pathToCheck) {
            pathCount.set(0, new Integer(pathCount.get(0) + 1));
        }
        if (node.left != null) {
            visitNode(node.left, newPathSum, pathToCheck, pathCount);
        }
        if (node.right != null) {
            visitNode(node.right, newPathSum, pathToCheck, pathCount);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
        tree.put(5, 5);
        tree.put(1, 1);
        tree.put(8, 8);
        tree.put(-2, -2);
        tree.put(4, 4);
        tree.put(10, 10);
        tree.put(9, 9);
        tree.root.left.right.left = tree.new Node(-6, -6);

        tree.root.right.left = tree.new Node(-4, -4);


        PathWithSum obj = new PathWithSum();
        System.out.println(tree);
        System.out.println("Sum of path for " + 4 + " is " + obj.checkPathForSum(tree, 4));
    }
}
