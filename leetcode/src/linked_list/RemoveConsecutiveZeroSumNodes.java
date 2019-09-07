package linked_list;

import list.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1171. Remove Zero Sum Consecutive Nodes from Linked List
 * Medium
 *
 * Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are
 * no such sequences.
 *
 * After doing so, return the head of the final linked list.  You may return any such answer.
 *
 *
 *
 * (Note that in the examples below, all sequences are serializations of ListNode objects.)
 *
 * Example 1:
 *
 * Input: head = [1,2,-3,3,1]
 * Output: [3,1]
 * Note: The answer [1,2,1] would also be accepted.
 * Example 2:
 *
 * Input: head = [1,2,3,-3,4]
 * Output: [1,2,4]
 * Example 3:
 *
 * Input: head = [1,2,3,-3,-2]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The given linked list will contain between 1 and 1000 nodes.
 * Each node in the linked list has -1000 <= node.val <= 1000.
 */
public class RemoveConsecutiveZeroSumNodes {

    /**
     * 1. How to find subsequences sum to 0 - count prefix sum, create map for every element. If we met the same
     * element one more time - the interval between this one and one saves in map is sum to 0.
     *
     * 2. Iterate over this map, save node, when found - starting from saved one start removing from the map
     *
     * @param head
     * @return
     */
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode fake = new ListNode(0);
        fake.next = head;
        ListNode node = fake;

        Map<Integer, ListNode> m = new HashMap();
        //prefix sum
        int cur = 0;
        while(node != null) {
            cur += node.val;
            //if we had this sum before - the subsequence found
            if (m.containsKey(cur)) {
                ListNode prev = m.get(cur);
                node = prev.next;
                int sum = cur + node.val;
                while (sum != cur) {
                    m.remove(sum);
                    node = node.next;
                    sum += node.val;
                }
                prev.next = node.next;
            } else {
                m.put(cur, node);
            }
            node = node.next;
        }

        return fake.next;
    }
}
