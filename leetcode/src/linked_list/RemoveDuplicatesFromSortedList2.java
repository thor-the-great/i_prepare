package linked_list;

import linked_list.Solution.ListNode;

/**
 * 82. Remove Duplicates from Sorted List II
Medium

Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers
from the original list. Return the linked list sorted as well.

Example 1:


Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]
Example 2:


Input: head = [1,1,1,2,3]
Output: [2,3]
 

Constraints:

The number of nodes in the list is in the range [0, 300].
-100 <= Node.val <= 100
The list is guaranteed to be sorted in ascending order.

 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 */
public class RemoveDuplicatesFromSortedList2 {
    /**
     * Keep 3 nodes - previous, current and next. On each iteration check if
     * current == next, if so - keep iterating untill they became different.
     * When so, assign next to the previous.next, this way to skip all equal nodes.
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode prev = fakeHead;
        ListNode cur = head;
        
        while (cur != null) {
            ListNode next = cur.next;
            if (next != null && next.val == cur.val) {
                //this is case when we have at least 2 repeating elements
                while (next != null && next.val == cur.val) {
                    next = next.next;
                }
                //here next either is null or points to the next non-repeating element
                prev.next = next;
                cur = next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        
        return fakeHead.next;
    }
    
}
