/**
 * Delete N-th node from doubly linked list
 *
 */
public class DeleteNthNodeDoublyLinkedList {

    public DoublyLinkedNode deleteAtPos(DoublyLinkedNode head, int pos) {

        if (head == null || pos <= 0)
            return head;

        DoublyLinkedNode fake = new DoublyLinkedNode(-1);
        fake.next = head;
        head.prev = fake;

        DoublyLinkedNode n = head;
        while (n != null) {
            if (pos == 1) {
                n.prev.next = n.next;
                if (n.next != null)
                    n.next.prev = n.prev;
            }
            n = n.next;
            pos--;
        }

        return fake.next;
    }
}

class DoublyLinkedNode {
    int data;
    DoublyLinkedNode next;
    DoublyLinkedNode prev;
    public DoublyLinkedNode(int data) { this.data = data; }
}
