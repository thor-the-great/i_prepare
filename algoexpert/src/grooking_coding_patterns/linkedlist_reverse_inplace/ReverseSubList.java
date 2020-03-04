package grooking_coding_patterns.linkedlist_reverse_inplace;

class ListNode {
  int value = 0;
  ListNode next;

  ListNode(int value) {
    this.value = value;
  }
}

/**
 * Reverse a Sub-list (medium)
 * Given the head of a LinkedList and two positions ‘p’ and ‘q’, reverse the LinkedList from
 * position ‘p’ to ‘q’.
 *
 *  Original List:
 *  Example:
 *  head
 *     1
 *     2
 *     3
 *     4
 *     5
 *     null
 *     1
 *     4
 *     3
 *     2
 *     5
 *     null
 */
public class ReverseSubList {

  /**
   * Find and reverse the part between p and q, then re-connect new head a nd tail to the
   * proper nodes in original list - before p and after q
   * @param head
   * @param p
   * @param q
   * @return
   */
  public static ListNode reverse(ListNode head, int p, int q) {
    if (p == q)
      return head;

    ListNode fakeHead = new ListNode(-1);
    fakeHead.next = head;

    ListNode prev = null;
    while (head.value != p) {
      prev = head;
      head = head.next;
    }
    ListNode pprev = prev;
    prev.next = null;
    ListNode hhead = head;
    //prev points to node before the p value, head is the p node itself
    while(prev == null || prev.value != q) {
      ListNode next = head.next;
      head.next = prev;
      prev = head;
      head = next;
    }
    //now prev points to the node with q, head is the node after it.
    //connect "before p" node next to the tail of the reversed part - prev;
    pprev.next = prev;
    //now connect the old head (now tail) of the reversed segment to the next after q node
    hhead.next = head;

    return fakeHead.next;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);

    ListNode result = ReverseSubList.reverse(head, 2, 4);
    System.out.print("Nodes of the reversed LinkedList are: ");
    while (result != null) {
      System.out.print(result.value + " ");
      result = result.next;
    }
  }
}