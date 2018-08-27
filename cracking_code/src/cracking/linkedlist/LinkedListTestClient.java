package cracking.linkedlist;

/**
 * Created by thor on 12/8/16.
 */
public class LinkedListTestClient {
    public static void main(String[] args) {
        System.out.println("Doubly linked list");
        MyDoublyLinkedList<Integer> myDLinkedList = new MyDoublyLinkedList<>();
        myDLinkedList.addToTail(2);
        myDLinkedList.addToTail(3);
        myDLinkedList.addToTail(2);
        myDLinkedList.addToTail(5);
        myDLinkedList.addToTail(10);
        myDLinkedList.addToTail(3);
        myDLinkedList.addToTail(3);

        myDLinkedList.print();

        System.out.println("Print via foreach");

        for (Integer i : myDLinkedList ) {
            System.out.println(i);
        }

        System.out.println("Singly linked list");
        MyDoublyLinkedList<Integer> myLinkedList = new MyDoublyLinkedList<>();
        myLinkedList.addToTail(2);
        myLinkedList.addToTail(3);
        myLinkedList.addToTail(2);
        myLinkedList.addToTail(5);
        myLinkedList.addToTail(10);
        myLinkedList.addToTail(3);
        myLinkedList.addToTail(3);

        myLinkedList.print();

        System.out.println("Print via foreach");

        for (Integer i : myLinkedList ) {
            System.out.println(i);
        }
    }
}
