import list.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Remove duplicate nodes (by val value) from the singly linked list
 */
public class RemoveDuplicateNodes {

    /**
     * Iterate and keep adding unique values to the set. If we met same value again -remove current node
     * @param head
     * @return
     */
    public ListNode removeDuplicates(ListNode head) {

        if (head == null || head.next == null)
            return head;

        Set<Integer> counts = new HashSet();

        ListNode n = head;
        ListNode prev = null;

        while(n != null) {
            if (!counts.contains(n.val)) {
                counts.add(n.val);
                prev = n;
                n = n.next;
            }
            //found a duplicate
            else {
                prev.next = n.next;
                n = n.next;
            }
        }

        return head;
    }
}
