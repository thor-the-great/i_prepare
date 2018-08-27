package cracking.linkedlist;

/**
 * Created by I057833 on 12/12/2016.
 */
public class CycleLinkedList {

    boolean isLoop(MySinglyLinkedList list) {
        boolean isLoop = false;
        if (list == null)
            return isLoop;
        MySinglyLinkedList.LinkedListNode turtle = list.head;
        MySinglyLinkedList.LinkedListNode rabbit = list.head;
        do {
            turtle = incrementIterator(turtle);
            rabbit = incrementIterator(incrementIterator(rabbit));
        } while (rabbit != null && turtle != null && (!turtle.data.equals(rabbit.data)));
        if (rabbit == null || turtle == null)
            return false;
        else
            return true;
    }

    MySinglyLinkedList.LinkedListNode getLoopStartNode(MySinglyLinkedList list) {
        if (list == null)
            return null;
        MySinglyLinkedList.LinkedListNode turtle = list.head;
        MySinglyLinkedList.LinkedListNode rabbit = list.head;
        do {
            turtle = incrementIterator(turtle);
            rabbit = incrementIterator(incrementIterator(rabbit));
        } while (rabbit != null && turtle != null && (!turtle.data.equals(rabbit.data)));
        if (rabbit == null || turtle == null)
            return null;
        else {
            //here we have both rabbit/turtle pointing to the same point in the loop
            MySinglyLinkedList.LinkedListNode headPointer = list.head;
            while (headPointer.data != rabbit.data) {
                headPointer = headPointer.next;
                rabbit = rabbit.next;
            }
            return headPointer;
        }
    }

    MySinglyLinkedList.LinkedListNode incrementIterator(MySinglyLinkedList.LinkedListNode node) {
        if (node == null)
            return null;
        return node.next;
    }

    public static void main(String[] args) {
        MySinglyLinkedList<String> list = new MySinglyLinkedList<>();
        MySinglyLinkedList.LinkedListNode aNode = list.addToTail("A");
        list.addToTail("B");
        list.addToTail("C");
        list.addToTail("D");
        MySinglyLinkedList.LinkedListNode loopStartNode = list.addToTail("E");
        list.addToTail("F");
        list.addToTail("G");
        MySinglyLinkedList.LinkedListNode lastNode = list.addToTail("H");
        lastNode.next = loopStartNode;
        //cNode.next = aNode;
        CycleLinkedList obj = new CycleLinkedList();
        loopStartNode = obj.getLoopStartNode(list);
        if ( loopStartNode == null )
            System.out.println("No loop found");
        else {
            loopStartNode = obj.getLoopStartNode(list);
            System.out.println("Loop starts at node " + loopStartNode.data);
        }
    }
}
