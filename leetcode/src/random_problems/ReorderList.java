package random_problems;

import list.ListNode;
import list.StringUtils;

public class ReorderList {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;
        //create fake head node to refer head later
        ListNode fake = new ListNode(-1);
        fake.next = head;

        //count number of nodes
        int len = 0;
        ListNode n = fake;
        while (n != null) {
            len++;
            n = n.next;
        }

        //pointer for the mid element, last in the first half
        int mid = (len % 2 == 0) ? len / 2 : (len / 2) + 1;
        n = fake;
        while (mid > 0) {
            n = n.next;
            mid--;
        }
        //n points to mid. halfHead is head of mid, unlink the first half
        ListNode halfHead = n.next;
        n.next = null;
        //reuse n as temp node
        n = null;
        //revert second half of the list
        while (halfHead != null) {
            ListNode next = halfHead.next;
            halfHead.next = n;
            n = halfHead;
            halfHead = next;
        }
        //n points to the last node from second half now do the final reorder
        ListNode nn = fake.next;
        while (n != null) {
            ListNode next = nn.next;
            nn.next = n;
            n = n.next;
            nn.next.next = next;
            nn = next;
        }
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ReorderList obj = new ReorderList();
        obj.reorderList(root);

        System.out.println(StringUtils.singlyListNodeToString(root));
    }
}
