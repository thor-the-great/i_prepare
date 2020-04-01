package linked_list;

import list.ListNode;

/**
 * 203. Remove Linked List Elements
 * Easy
 *
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example:
 *
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 */
public class RemoveLinkedListElements {

  /**
   *
   * @param head
   * @param val
   * @return
   */
  public ListNode removeElements(ListNode head, int val) {
    if (head == null)
      return null;
    //create fake head node so we can refer future head easily
    ListNode fake = new ListNode(-1);
    fake.next = head;
    //this will be our iteration nodes - prev is previous good node that we keep (haven't removed)
    //and cur is current node that potentially can be skipped
    ListNode cur = fake.next, prev = fake;
    //checking node one by one
    while (cur != null) {
      //if this node should be kept - set next of previous good node to this one,
      //make this node next previous node
      if (cur.val != val) {
        prev.next = cur;
        prev = cur;
      } else {
        //in case we're skipping the node - assign next ref of the previous good node to the
        //next of the current node. This next of current can potentially be a good one, or
        //otherwise will keep advansing it until we reach null (cur.next of last node in the
        //list is null)
        prev.next = cur.next;
      }
      cur = cur.next;
    }
    //at the end or fake head next is pointing to the fist good node, so return it
    return fake.next;
  }
}
