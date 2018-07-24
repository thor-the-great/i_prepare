package arrays;

import utils.StringUtils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {

    /**
     * This problem was asked by Uber.
     *
     * Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.
     *
     * For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].
     *
     * Follow-up: what if you can't use division?
     *
     * @param arr
     * @return
     */
    public int[] productArray(int[] arr) {
        int n = arr.length;
        if (n == 0)
            return arr;
        //left count products of all numbers left to i except for i-th. right same but to the right
        //solution would be to multiply left[i] and right[i] = it will be all to the left * all to the right but not the i-th
        //itself
        int[] left = new int[n], right = new int[n], products = new int[n];
        //init array
        left[0] = 1;
        right[n-1] = 1;
        for (int i=1; i<n; i++)
            left[i] = left[i-1] * arr[i - 1];
        for (int i=n-2; i>=0; i--)
            right[i] = right[i+1] * arr[i+1];
        for(int i=0; i<n; i++)
            products[i] = right[i] * left[i];

        return products;
    }

    /**
     * Problem asked by Google
     *
     * Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s), which deserializes the string back into the tree.
     *
     * For example, given the following Node class
     *
     * class Node:
     *     def __init__(self, val, left=None, right=None):
     *         self.val = val
     *         self.left = left
     *         self.right = right
     * The following test should pass:
     *
     * node = Node('root', Node('left', Node('left.left')), Node('right'))
     * assert deserialize(serialize(node)).left.left.val == 'left.left'
     *
     * @param root
     * @return
     */
    String serialize(Node root) {
        if (root == null)
            return "#";
        StringBuilder sb = new StringBuilder();
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        sb.append(root.val + ",");
        while(!q.isEmpty()) {
            Node n = q.poll();
            if (n.left != null) {
                sb.append(n.left.val).append(",");
                q.add(n.left);
            }
            else
                sb.append("null").append(",");
            if (n.right != null) {
                sb.append(n.right.val).append(",");
                q.add(n.right);
            }
            else
                sb.append("null").append(",");
        }
        return sb.toString();
    }

    Node deserialize(String s) {
        if (s.equals("#"))
            return null;
        String[] indStrings = s.split(",");
        Node root = new Node(indStrings[0], null, null);
        Queue<Node> stack = new LinkedList<>();
        stack.add(root);
        int pointer = 0;
        while (pointer < indStrings.length - 1) {
            Node node = stack.poll();
            pointer++;
            String nextVal = indStrings[pointer];
            if (!nextVal.equals("null")) {
                Node left = new Node(nextVal, null, null);
                node.left = left;
                stack.add(left);
            }
            pointer++;
            nextVal = indStrings[pointer];
            if (!nextVal.equals("null")) {
                Node right = new Node(nextVal, null, null);
                node.right = right;
                stack.add(right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();

        /*int[] prodArray = obj.productArray(new int[]{5, 7, 3, 4});
        System.out.println(StringUtils.intArrayToString(prodArray));

        prodArray = obj.productArray(new int[]{10, 3, 5, 6, 2});
        System.out.println(StringUtils.intArrayToString(prodArray));
        */

        Node root = new Node("root", new Node("left", new Node("left.left", null, null), new Node("left.right", null, new Node("left.right.left", null,null))), new Node("right", null, new Node("right.right", null, null)));
        String serString = obj.serialize(root);
        System.out.println(serString);

        Node desRoot = obj.deserialize(serString);
        System.out.println(desRoot);
    }

    static class Node {
        String val;
        Node left;
        Node right;
        Node(String val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
