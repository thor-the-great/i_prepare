package linked_list;

import linked_list.Solution.ListNode;

/**
 * 234. Palindrome Linked List
Easy

Given the head of a singly linked list, return true if it is a palindrome.

 

Example 1:

Input: head = [1,2,2,1]
Output: true

Example 2:

Input: head = [1,2]
Output: false

 

Constraints:

    The number of nodes in the list is in the range [1, 105].
    0 <= Node.val <= 9

 
Follow up: Could you do it in O(n) time and O(1) space?

https://leetcode.com/problems/palindrome-linked-list/
 */
public class PalindromeLinkedList {
    
    /**
     * We do two pointers - slow and fast. At the end slow will point to the middle of the list.
     * After that we reverse the second half starting from the slow
     * Then we start comparing reversed half with the first untouched half (pinted by head)
     * 
     * O(n) time
     * O(1) space
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return false;
        
        ListNode fast = head, slow = head, cur = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        
        slow = reverse(fast != null ? slow.next : slow);
        fast = head;
        
        while (slow != null) {
            if (slow.val != fast.val)
                return false;
            slow = slow.next;
            fast = fast.next;
        }
        
        return true;
    }
    
    ListNode reverse(ListNode cur) {
        ListNode prev = null;
        while (cur != null) {
            ListNode t = cur.next;
            cur.next = prev;
            prev = cur;
            cur = t;
        }
        return prev;
    }
}
