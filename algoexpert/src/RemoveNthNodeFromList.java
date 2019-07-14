import list.ListNode;

/**
 * Remove the "Nth from the end" Node from a Singly-Linked List New
 *
 * Linked Lists
 *
 * Given a singly-linked list, remove its Nth from the end node.
 * Examples:
 * 1->2->3->4->5, n=3 ==> 1->2->4->5
 * 1->2->3->4->5, n=1 ==> 1->2->3->4
 * 1->2->3->4->5, n=5 ==> 2->3->4->5
 */
public class RemoveNthNodeFromList {

    /**
     * Use two pointers. First move fast point N positions forward. Then start moving two pointers one step at the
     * time. When fast reached end of list the first one points to the N-th from the end
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null || n <= 0)
            return head;

        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;

        int count = 1;
        while(head != null && count < n) {
            head = head.next;
            count++;
        }
        //this means we don't have enought nodes
        if (head == null)
            return fakeHead.next;

        ListNode slow = fakeHead;

        while( head.next != null) {
            head = head.next;
            slow = slow.next;
        }

        if (slow.next != null) {
            slow.next = slow.next.next;
        }

        return fakeHead.next;
    }
}
