package linked_list;

import list.ListNode;

/**
 * 142. Linked List Cycle II
 * Medium
 *
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * To represent a cycle in the given linked list, we use an integer pos which represents the
 * position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no
 * cycle in the linked list.
 *
 * Note: Do not modify the linked list.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 *
 * Example 2:
 *
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 *
 *
 * Example 3:
 *
 * Input: head = [1], pos = -1
 * Output: no cycle
 * Explanation: There is no cycle in the linked list.
 *
 *
 *
 *
 * Follow-up:
 * Can you solve it without using extra space?
 */
public class DetectCycle {

  /**
   * Create two pointers, slow and fast. Move fast two steps and slow one step at a time
   * If they meet - there is a cycle. To know the position - start another pointer from the
   * list's head node. Keep advancing head pointer and that pointer that found the cycle. When
   * they met this will be the position of the cycle start
   * O(1) space, O(n) time
   * @param head
   * @return
   */
  public ListNode detectCycle(ListNode head) {
    if (head == null || head.next == null)
      return null;

    ListNode preHead = new ListNode(-1);
    preHead.next = head;

    ListNode slow = head, fast = head;

    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;

      if (slow == fast) {
        //we found a cycle
        ListNode cur = preHead.next;
        while (cur != slow) {
          cur = cur.next;
          slow = slow.next;
        }
        return cur;
      }
    }

    return null;
  }
}
