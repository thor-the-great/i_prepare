package linked_list;

import java.util.Random;
import list.ListNode;

/**
 * 382. Linked List Random Node
 * Medium
 *
 * Given a singly linked list, return a random node's value from the linked list. Each node must
 * have the same probability of being chosen.
 *
 * Follow up:
 * What if the linked list is extremely large and its length is unknown to you? Could you solve
 * this efficiently without using extra space?
 *
 * Example:
 *
 * // Init a singly linked list [1,2,3].
 * ListNode head = new ListNode(1);
 * head.next = new ListNode(2);
 * head.next.next = new ListNode(3);
 * Solution solution = new Solution(head);
 *
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal
 * probability of returning.
 * solution.getRandom();
 */
public class LinkedListRandomNode {

  ListNode head;
  Random rand = new Random();

  /**
   * Idea - space efficient and it number of nodes is not known - reservvuar sampling.
   * In this approach we start from first element and keep advancing and generating next random
   * at the end we have our answer
   * O(n) time O(1) space
   *
   * Alternative simpler approach is to put elements into arraylist and generate random from
   * 0 to list size.
   * O(n) space, O(1) time
   * @param head
   */
  public LinkedListRandomNode(ListNode head) {
    this.head = head;
  }

  /** Returns a random node's value. */
  public int getRandom() {
    ListNode c = head;
    int res = c.val;
    c = c.next;
    int idx = 1;
    while (c != null) {
      if (idx == rand.nextInt(idx + 1)) {
        res = c.val;
      }
      c = c.next;
      ++idx;
    }
    return res;
  }
}
