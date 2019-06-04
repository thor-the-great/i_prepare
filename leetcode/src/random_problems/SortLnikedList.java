package random_problems;

import list.ListNode;

/**
 * 148. Sort List
 * Medium
 *
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class SortLnikedList {

    /**
     * Do the merge sort - it's O(nlogn). Because it's a list merge works in place
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode mid = getMiddle(head);

        ListNode nextAfterMid = mid.next;
        mid.next = null;

        ListNode merged = merge(sortList(head), sortList(nextAfterMid));

        return merged;
    }

    ListNode getMiddle(ListNode n) {
        if (n == null)
            return null;

        ListNode slow = n;
        ListNode fast = n.next;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
        }
        return slow;
    }

    ListNode merge(ListNode left, ListNode right) {
        if (left == null)
            return right;
        if (right == null)
            return left;
        ListNode head = left;
        if (left.val <= right.val) {
            head.next = merge(left.next, right);
        }
        else {
            head = right;
            head.next = merge(left, right.next);
        }
        return head;
    }
}
