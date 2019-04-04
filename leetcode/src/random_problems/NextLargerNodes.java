package random_problems;

import linked_list.ListNode;
import utils.StringUtils;

import java.util.*;

/**
 * 1019. Next Greater Node In Linked List
 * Medium
 *
 * We are given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2,
 * node_3, ... etc.
 *
 * Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i,
 * node_j.val > node_i.val, and j is the smallest possible choice.  If such a j does not exist, the next larger
 * value is 0.
 *
 * Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).
 *
 * Note that in the example inputs (not outputs) below, arrays such as [2,1,5] represent the serialization of a
 * linked list with a head node value of 2, second node value of 1, and third node value of 5.
 *
 *
 *
 * Example 1:
 *
 * Input: [2,1,5]
 * Output: [5,5,0]
 * Example 2:
 *
 * Input: [2,7,4,3,5]
 * Output: [7,0,5,5,0]
 * Example 3:
 *
 * Input: [1,7,5,1,9,2,5,1]
 * Output: [7,9,9,9,0,5,0,0]
 *
 *
 * Note:
 *
 * 1 <= node.val <= 10^9 for each node in the linked list.
 * The given list has length in the range [0, 10000].
 */
public class NextLargerNodes {

    public int[] nextLargerNodes(ListNode head) {
        if (head == null)
            return new int[0];

        List<Integer> list = new ArrayList();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        Stack<Integer> s = new Stack();
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            int num = list.get(i);
            while(!s.isEmpty() && list.get(s.peek()) < num ) {
                res[s.pop()] = num;
            }
            s.push(i);
        }

        return res;
    }

    public static void main(String[] args) {
        NextLargerNodes obj = new NextLargerNodes();
        ListNode root = new ListNode(2,
                new ListNode(7,
                        new ListNode(3)));

        int[] res;
        res = obj.nextLargerNodes(root);
        System.out.println(StringUtils.intArrayToString(res));

        root = new ListNode(2,
                new ListNode(7,
                        new ListNode(4, new ListNode(3, new ListNode(5)))));

        res = obj.nextLargerNodes(root);
        System.out.println(StringUtils.intArrayToString(res));

        root = new ListNode(1, new ListNode(7, new ListNode(5, new ListNode(1, new ListNode(9, new ListNode(2, new ListNode(5, new ListNode(1))))))));
        //[1,7,5,1,9,2,5,1]

        res = obj.nextLargerNodes(root);
        System.out.println(StringUtils.intArrayToString(res)); //[7,9,9,9,0,5,0,0]

        root = new ListNode(3, new ListNode(3));
        //[3,3]

        res = obj.nextLargerNodes(root);
        System.out.println(StringUtils.intArrayToString(res)); //[0,0]
    }
}
