package cracking.linkedlist;

import java.util.Iterator;

/**
 * Created by thor on 12/8/16.
 */
public class MySinglyLinkedList<E> implements Iterable<E> {

    static class LinkedListNode<E> {
        E data;
        LinkedListNode next;
        LinkedListNode(E data) {
            this.data = data;
        }
    }

    LinkedListNode head;
    LinkedListNode tail;

    public MySinglyLinkedList() {
        head = null;
    }

    public LinkedListNode addToTail(E data) {
        LinkedListNode newNode = new LinkedListNode(data);
        if (head == null) {
            head = newNode;
            return head;
        }
        LinkedListNode node = head;
        while(node.next != null) {
            node = node.next;
        }
        node.next = newNode;
        return newNode;
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
            E data = (E) initNode.data;
            initNode = initNode.next;
            return data;
        }
    }
}
