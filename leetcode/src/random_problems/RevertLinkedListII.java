package random_problems;

import linked_list.ListNode;

public class RevertLinkedListII {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode f = new ListNode(-1);
        f.next = head;
        ListNode nNode = head;
        int i = n - m;
        while (i > 0) {
            nNode = nNode.next;
            i--;
        }
        i = 1;
        ListNode mNode = head;
        ListNode prevMNode = null;
        while (i < m) {
            prevMNode = mNode;
            mNode = mNode.next;
            nNode = nNode.next;
            i++;
        }
        ListNode p = nNode.next;
        nNode.next = null;
        while (mNode != null) {
            ListNode t = mNode.next;
            mNode.next = p;
            p = mNode;
            mNode = t;
        }
        if (prevMNode != null)
            prevMNode.next = p;
        return f.next;
    }

    public static void main(String[] args) {
        RevertLinkedListII obj = new RevertLinkedListII();
        ListNode l = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        obj.reverseBetween(l ,2, 4);
    }
}
