package mock_sessions.amazon;

import linked_list.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * Merge k Sorted Lists
 *   Go to Discuss
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.val));
        IntStream.range(0, lists.length).forEach(i-> {
            if (lists[i] != null)
                pq.add(lists[i]);
        });
        if (pq.isEmpty())
            return null;
        ListNode head = pq.poll();
        ListNode curr = head;
        if (curr.next != null) pq.add(curr.next);
        while(!pq.isEmpty()) {
            ListNode n = pq.poll();
            curr.next = n;
            curr = curr.next;
            if (n.next != null)
                pq.add(n.next);
        }
        return head;
    }
}
