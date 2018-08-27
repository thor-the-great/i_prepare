package cracking.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thor on 12/8/16.
 */
public class RemoveDuplicates<E> extends MyDoublyLinkedList<E> {

    void removeDuplicates() {
        Map<E, LinkedListNode> dataValues = new HashMap<>();
        LinkedListNode nextNode = head;
        while (nextNode != null) {
            E data = nextNode.data;
            //if this data is met for the first time
            if (!dataValues.containsKey(data)) {
                dataValues.put(data, nextNode);
            }
            else {
                nextNode.previous.next = nextNode.next;
                if (nextNode.next != null)
                    nextNode.next.previous = nextNode.previous;
                //this is our tale
                else
                    tail = nextNode.previous;
            }
            nextNode = nextNode.next;
        }
    }

    void removeDuplicatesInPlace() {
        Map<E, LinkedListNode> dataValues = new HashMap<>();
        LinkedListNode normalIt = head;
        LinkedListNode repeatIt = head.next;
        while (normalIt != null) {
            E data = normalIt.data;
            while (repeatIt != null) {
                if (repeatIt.data.equals(data)) {
                    repeatIt.previous.next = repeatIt.next;
                    if (repeatIt.next != null)
                        repeatIt.next.previous = repeatIt.previous;
                        //this is our tale
                    else
                        tail = repeatIt.previous;
                }
                repeatIt = repeatIt.next;
            }
            normalIt = normalIt.next;
            repeatIt = normalIt == null ? null : normalIt.next;
        }
    }

    public static void main(String[] args) {
        RemoveDuplicates<Integer> myLinkedList = new RemoveDuplicates<>();
        myLinkedList.addToTail(2);
        myLinkedList.addToTail(3);
        myLinkedList.addToTail(2);
        myLinkedList.addToTail(5);
        myLinkedList.addToTail(10);
        myLinkedList.addToTail(3);
        myLinkedList.addToTail(3);

        myLinkedList.print();

        //myLinkedList.removeDuplicates();
        myLinkedList.removeDuplicatesInPlace();
        System.out.println("After removing duplicates");
        myLinkedList.print();
    }
}
