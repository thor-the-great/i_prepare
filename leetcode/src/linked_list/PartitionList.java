package linked_list;

/**
 * 86. Partition List
 * Medium
 *
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than
 * or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example:
 *
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 *
 */
public class PartitionList {

    /**
     * Idea is to do one pass on list and do sorting on nodes based on value. To keep result we use two pointers -
     * one for values less than x and another for equals or greater.
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        //catch - use dummy nodes, this helps to avoid many not null checks in code
        ListNode less = new ListNode(-1);
        ListNode greater = new ListNode(-1);

        ListNode lessN = less;
        ListNode greaterN = greater;
        while (head != null) {
            if (head.val < x) {
                lessN.next = head;
                lessN = lessN.next;
            } else {
                greaterN.next = head;
                greaterN = greaterN.next;
            }
            head = head.next;
        }
        //need to disconnect last element - in greater it must be null, otherwise cycles are possible
        greaterN.next = null;
        //in less it must point to greater.next;
        lessN.next = greater.next;
        return less.next;
    }
}
