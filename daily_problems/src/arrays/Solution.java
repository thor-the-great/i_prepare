package arrays;

import utils.StringUtils;

import java.util.LinkedList;
import java.util.Queue;

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

    String serialize(Node root) {
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


    public static void main(String[] args) {
        Solution obj = new Solution();

        /*int[] prodArray = obj.productArray(new int[]{5, 7, 3, 4});
        System.out.println(StringUtils.intArrayToString(prodArray));

        prodArray = obj.productArray(new int[]{10, 3, 5, 6, 2});
        System.out.println(StringUtils.intArrayToString(prodArray));
        */

        Node root = new Node("root", new Node("left", new Node("left.left", null, null), null), new Node("right", null, null));

        System.out.println(obj.serialize(root));
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
