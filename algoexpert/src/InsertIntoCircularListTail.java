import list.ListNode;

/**
 * Insert a Node at the Tail of a Circular Linked List New
 *     Linked Lists Multi-link Linked Lists
 *
 * Given a circular linked list, write a method to insert a node at its tail. Return the list's
 * head.
 *
 * Examples:
 *
 * *x = indicates head node
 * Insert 1 ==> *1
 * Insert 2 ==> 1->2->*1
 * Insert 3 ==> 1->2->3->*1
 */
public class InsertIntoCircularListTail {

  public ListNode insertAtTail(ListNode head, int data) {

    if (head == null) {
      head = new ListNode(data);
      head.next = head;
      return head;
    }

    ListNode headCopy = head;

    while(head.next != headCopy) {
      head = head.next;
    }

    head.next = new ListNode(data);
    head.next.next = headCopy;

    return headCopy;
  }
}
