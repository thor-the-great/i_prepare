package linked_list;

import list.ListNode;

/**
 * 328. Odd Even Linked List
 * Medium
 *
 * 1267
 *
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please
 * note here we are talking about the node number and not the value in the nodes.
 *
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes)
 * time complexity.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * Example 2:
 *
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 * Note:
 *
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 */
public class OddEvenLinkedList {

  /**
   * Create pointers to even and odd nodes. Start a loop and advance both pointers at the same
   * time. For the even (we count from 0-index so head is even) we can simplify things. For odd
   * node we keep the fist odd one, when we done with everything we assign last even node's next to
   * the head of odd nodes.
   * @param head
   * @return
   */
  public ListNode oddEvenList(ListNode head) {
    if (head == null || head.next == null)
      return head;

    ListNode preOdd = new ListNode(-1);
    preOdd.next = head.next;

    ListNode even = head;
    ListNode odd = preOdd.next;
    ListNode prevEven = head;
    while (even != null) {
      ListNode cur = null;
      prevEven = even;
      if (odd != null) {
        cur = odd.next;
      }
      even.next = cur;
      even = cur;

      cur = null;
      if (even != null) {
        cur = even.next;
      }
      if (odd != null) {
        odd.next = cur;
        odd = cur;
      }
    }

    prevEven.next = preOdd.next;
    return head;
  }
}
