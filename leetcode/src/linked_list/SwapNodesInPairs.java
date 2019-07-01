package linked_list;

import list.ListNode;
import list.StringUtils;

public class SwapNodesInPairs {

    /**
     * Do swap in blocks of 4 elements.
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null)
            return head;
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode p = dummyHead;
        while (p != null && p.next != null) {
            ListNode n1 = p.next;
            ListNode n2 = n1.next;
            ListNode n = n2 == null ? null : n2.next;
            if (n2 != null)
                n2.next = n1;
            n1.next = n;
            if (n1 != null && n2 == null)
                p.next = n1;
            else
                p.next = n2;

            p = n1;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        SwapNodesInPairs obj = new SwapNodesInPairs();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode swapped = obj.swapPairs(head);
        System.out.println(StringUtils.singlyListNodeToString(swapped));

        head = new ListNode(1, new ListNode(2, new ListNode(3)));
        swapped = obj.swapPairs(head);
        System.out.println(StringUtils.singlyListNodeToString(swapped));
    }
}
