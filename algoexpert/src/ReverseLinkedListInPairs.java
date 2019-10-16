import list.ListNode;

/**
 * Reverse a Linked List in Pairs Fast Travel
 *     Linked Lists Recursion
  * Given a singly-linked list, reverse the list in pairs.
 * Example:
 * Given 1->2->3->4,
 * reverseInPairs(1) ==> 2->1->4->3
 */
public class ReverseLinkedListInPairs {

    /**
     * REverse in pairs, code in obvious
     * @param head
     * @return
     */
    public ListNode reverseInPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode next = head.next;
        ListNode newNext = next.next;

        next.next = head;
        head.next = reverseInPairs(newNext);

        return next;
    }

    public ListNode reverseInPairs2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode next = head.next;
        ListNode nnext = next.next;
        next.next = head;
        head.next = nnext;
        head.next = reverseInPairs(nnext);
        return next;
    }
}
