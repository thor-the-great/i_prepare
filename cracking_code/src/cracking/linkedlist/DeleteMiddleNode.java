package cracking.linkedlist;


/**
 * Created by I057833 on 12/9/2016.
 */
public class DeleteMiddleNode<E> extends MySinglyLinkedList<E> {

    void removeMiddleNode(LinkedListNode middleNode) {
        if (middleNode == null)
            return;
        LinkedListNode next = middleNode.next;
        middleNode.data = next.data;
        middleNode.next = next.next;
    }

    LinkedListNode getNodeByValue(E data) {
        LinkedListNode node = head;
        boolean isFound = false;
        while (!isFound || node.next == null) {
            if (data.equals(node.data)) {
                isFound = true;
            } else{
                node = node.next;
            }
        }
        if (!isFound)
            return null;
        else
            return node;
    }

    public static void main(String[] args) {
        DeleteMiddleNode<Integer> myLinkedList = new DeleteMiddleNode<>();
        myLinkedList.addToTail(2);
        myLinkedList.addToTail(3);
        myLinkedList.addToTail(4);
        myLinkedList.addToTail(5);
        myLinkedList.addToTail(10);

        myLinkedList.print();

        System.out.println("Remove node");
        LinkedListNode node = (LinkedListNode) myLinkedList.getNodeByValue(Integer.valueOf(4));
        myLinkedList.removeMiddleNode(node);
        myLinkedList.print();
    }
}
