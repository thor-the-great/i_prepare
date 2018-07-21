package linked_list;

public class MyDoublyLinkedList {
    /** Initialize your data structure here. */

    DoublyListNode head = null;
    int count = 0;

    public MyDoublyLinkedList() {
        head = null;
        count = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index > count - 1 || head == null)
            return -1;
        DoublyListNode node = head;
        for (int i = 1; i <= index; i++) {
            node = node.next;
        }
        return node.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        DoublyListNode newHead = new DoublyListNode(val);
        newHead.next = head;
        if (head != null)
            head.prev = newHead;
        head = newHead;
        count++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        DoublyListNode newTail = new DoublyListNode(val);
        count++;
        if (head == null) {
            head = newTail;
            return;
        }
        DoublyListNode pointer = head;
        while (pointer.next != null) {
            pointer = pointer.next;
        }
        pointer.next = newTail;
        newTail.prev = pointer;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > count) {
            return;
        }
        if (index == count)
            addAtTail(val);
        else if (index == 0) {
            addAtHead(val);
        } else {
            DoublyListNode pointer = head;
            for(int i =1; i < index; i++) {
                pointer = pointer.next;
            }
            DoublyListNode newNode = new DoublyListNode(val);
            DoublyListNode oldNext = pointer == null ? null : pointer.next;
            pointer.next = newNode;
            newNode.prev = pointer;
            newNode.next = oldNext;
            if (oldNext != null)
                oldNext.prev = newNode;
            count++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= count || head == null)
            return;
        DoublyListNode node = head;
        DoublyListNode oldPrev = null;
        for (int i = 1; i <= index; i++) {
            oldPrev = node;
            node = node != null ? node.next : null;
        }
        DoublyListNode oldNext = node == null ? null : node.next;
        if (oldPrev != null) {
            oldPrev.next = oldNext;
            if (oldNext != null)
                oldNext.prev = oldPrev;
        } else {
            head = oldNext;
        }
        count--;
    }


    public static void main(String[] args) {
        MyDoublyLinkedList linkedList = new MyDoublyLinkedList();
        /*linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
        System.out.println(linkedList.get(1));            // returns 2
        linkedList.deleteAtIndex(1);  // now the linked list is 1->3
        System.out.println(linkedList.get(1));            // returns 3*/

        /*System.out.println(linkedList.get(0));
        linkedList.addAtIndex(1, 2);
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(1));
        linkedList.addAtIndex(0, 1);
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(1));*/

        linkedList.addAtHead(8);
        System.out.println(linkedList.get(1));
        linkedList.addAtTail(81);
        linkedList.deleteAtIndex(2);
        linkedList.addAtHead(26);
        linkedList.deleteAtIndex(2);
        System.out.println(linkedList.get(1));
    }
}

class DoublyListNode {
    public int val;
    public DoublyListNode next, prev;
    public DoublyListNode(int x) {
        val = x;
    }
}
