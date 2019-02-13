package random_problems;

import linked_list.ListNode;
import linked_list.StringUtils;

public class ReorderList {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode n = head;
        ListNode slow = null;
        while (n!= null ) {
            n = n.next;
            if (n != null)
                n = n.next;
            slow = slow == null ? head : slow.next;
        }

        //unchain end of slow
        ListNode tailStart = slow.next;
        slow.next = null;

        //revert tail part
        ListNode prev = null;
        while (tailStart != null) {
            n = tailStart.next;
            tailStart.next = prev;
            prev = tailStart;
            tailStart = n;
        }
        //now prev points to a ahead of reverted tail
        //need to merge two lists
        n = head;
        ListNode nextN = null;
        while (prev != null) {
            nextN = n.next;
            n.next = prev;
            prev = prev.next;
            n.next.next = nextN;
            n = nextN;
        }
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ReorderList obj = new ReorderList();
        obj.reorderList(root);

        System.out.println(StringUtils.singlyListNodeToString(root));
    }
}
