package cracking.linkedlist;

import java.util.Iterator;

/**
 * Created by thor on 12/8/16.
 */
public class MyDoublyLinkedList<E> implements Iterable<E> {

    class LinkedListNode {
        E data;
        LinkedListNode next;
        LinkedListNode previous;

        LinkedListNode(E data) {
            this.data = data;
        }
    }

    LinkedListNode head;
    LinkedListNode tail;

    public MyDoublyLinkedList() {
        head = null;
        tail = null;
    }

    public void addToTail(E data) {
        if (head == null) {
            head = new LinkedListNode(data);
            return;
        }
        if (tail == null) {
            tail = new LinkedListNode(data);
            head.next = tail;
            tail.previous = head;
            return;
        }
        LinkedListNode oldTail = new LinkedListNode(tail.data);
        tail.previous.next = oldTail;
        oldTail.previous = tail.previous;
        tail = new LinkedListNode(data);
        tail.previous = oldTail;
        oldTail.next = tail;
    }

    @Override
    public Iterator iterator() {
        Iterator<E> iterator = new MyIterator(head);
        return  iterator;
    }

    public void print() {
        LinkedListNode nextNode = head;
        while (nextNode != null) {
            System.out.println(nextNode.data);
            nextNode = nextNode.next;
        }
    }

    public class MyIterator implements Iterator<E> {

        LinkedListNode initNode;

        MyIterator(LinkedListNode initNode) {
            this.initNode = initNode;
        }

        @Override
        public boolean hasNext() {
            return initNode != null;
        }

        @Override
        public E next() {
            E data = initNode.data;
            initNode = initNode.next;
            return data;
        }
    }
}
