package cracking.linkedlist;

/**
 * Created by I057833 on 12/9/2016.
 */
public class KthElementFromEnd<E> extends MySinglyLinkedList<E> {

    E getElementFromEnd(int kth) {
        LinkedListNode slow = head;
        LinkedListNode fast = head;
        for (int i = 1; i <= kth; i++) {
            if (fast.next == null)
                return null;
            fast = fast.next;
        }
        /*if (fast.next == null)
            return slow.data;*/
        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return (E) slow.data;
    }


    public static void  main(String[] args) {
        KthElementFromEnd<Integer> myLinkedList = new KthElementFromEnd<>();
        myLinkedList.addToTail(2);
        myLinkedList.addToTail(3);
        myLinkedList.addToTail(2);
        myLinkedList.addToTail(5);
        myLinkedList.addToTail(10);
        myLinkedList.addToTail(3);
        myLinkedList.addToTail(3);
        myLinkedList.addToTail(23);
        myLinkedList.addToTail(11);
        myLinkedList.addToTail(34);
        myLinkedList.addToTail(58);

        myLinkedList.print();

        //myLinkedList.removeDuplicates();
        int kth = 3;
        Integer element = myLinkedList.getElementFromEnd(kth);
        System.out.println("" + kth + " element is " + element);
        //myLinkedList.print();
    }
}
