package linked_list;

/**
 * 369. Plus One Linked List
 * Medium
 * Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
 *
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example :
 *
 * Input: [1,2,3]
 * Output: [1,2,4]
 */
public class PlusOneReversed {

    /**
     * Idea - reverse the list, do easy add 1 then reverse back
     * @param head
     * @return
     */
    public ListNode plusOne(ListNode head) {
        ListNode reversed = reverse(head);
        int cary = 1;
        ListNode n = reversed;
        while(n != null) {
            int val = n.val + cary;
            n.val = val % 10;
            cary = val / 10;
            if (n.next == null && cary > 0) {
                ListNode caryNode = new ListNode(cary);
                n.next = caryNode;
                break;
            }
            n = n.next;
        }
        ListNode normal = reverse(reversed);
        return normal;
    }

    ListNode reverse(ListNode head) {
        if (head.next == null)
            return head;
        ListNode c = null;
        ListNode n = head;
        while (n != null) {
            ListNode x = n.next;
            n.next = c;
            c = n;
            n = x;
        }
        return c;
    }
}
