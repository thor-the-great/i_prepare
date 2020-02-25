package linked_list;

import list.ListNode;

/**
 * 445. Add Two Numbers II
 * Medium
 *
 * You are given two non-empty linked lists representing two non-negative integers. The most
 * significant digit comes first and each of their nodes contain a single digit. Add the two
 * numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbers2 {

  public ListNode addTwoNumbersWithListReverse(ListNode l1, ListNode l2) {
    if (l1 == null && l2 == null)
      return null;
    if (l1 == null)
      return l2;
    if (l2 == null)
      return l1;

    l1 = reverseList(l1); l2 = reverseList(l2);

    ListNode fakeHead = new ListNode(0);
    ListNode n = fakeHead;
    int c = 0;
    while (l1 != null || l2 != null) {
      int num = c;
      if (l1 != null) {
        num += l1.val;
        l1 = l1.next;
      }
      if (l2 != null) {
        num += l2.val;
        l2 = l2.next;
      }
      ListNode next = new ListNode(num % 10);
      c = num / 10;
      n.next = next;
      n = next;
    }

    if (c > 0)
      n.next = new ListNode(c);

    return reverseList(fakeHead.next);
  }

  ListNode reverseList(ListNode l1) {
    ListNode prev = null;
    while (l1 != null) {
      ListNode next = l1.next;
      l1.next = prev;
      prev = l1;
      l1 = next;
    }
    return prev;
  }
}
