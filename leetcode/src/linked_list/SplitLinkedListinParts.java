package linked_list;

import linked_list.Solution.ListNode;

/**
 * 725. Split Linked List in Parts
Medium

Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.

The length of each part should be as equal as possible: no two parts should have a size differing by more than one. This may lead to some parts being null.

The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal to parts occurring later.

Return an array of the k parts.

 

Example 1:


Input: head = [1,2,3], k = 5
Output: [[1],[2],[3],[],[]]
Explanation:
The first element output[0] has output[0].val = 1, output[0].next = null.
The last element output[4] is null, but its string representation as a ListNode is [].
Example 2:


Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
Output: [[1,2,3,4],[5,6,7],[8,9,10]]
Explanation:
The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
 

Constraints:

The number of nodes in the list is in the range [0, 1000].
0 <= Node.val <= 1000
1 <= k <= 50
 * 
 * https://leetcode.com/problems/split-linked-list-in-parts/
 */
public class SplitLinkedListinParts {
    
    /**
     * In each group there will be size/k + size%k for some first ones
     * Then iterate over the list and cut after each group has enough of elements
     * 
     * O(n) time, O(k) space (for final array)
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        int size = 0;
        ListNode[] res = new ListNode[k];
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        
        int smallSize = size / k;
        int numOfExtra = size % k;
        
        int idx = 0;
        cur = head;
        while (idx < k && cur != null) {
            ListNode nextElementHead = cur;
            int curSize = smallSize;
            if (numOfExtra > 0) {
                curSize++;
                numOfExtra--;
            }
            for (int i = 1; i < curSize; i++) {
                cur = cur.next;
            }
            ListNode t = cur.next;
            cur.next = null;
            cur = t;
            res[idx++] =  nextElementHead;
        }
        return res;
    }
}
