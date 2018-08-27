package cracking.linkedlist;

/**
 * Created by I057833 on 12/9/2016.
 */
public class PartitionAroundElement extends MySinglyLinkedList<Integer> {

    void partitionAroundElementInPlace(Integer data) {

        LinkedListNode partitionElement = head;
        LinkedListNode itNodeBefore = null;
        boolean isFound = false;
        while(partitionElement.next != null) {
            if (partitionElement.data == data) {
                isFound = true;
                break;
            }
            itNodeBefore = partitionElement;
            partitionElement = partitionElement.next;
        }
        if (!isFound)
            return;
        LinkedListNode nextNode = head;
        LinkedListNode preNextNode = null;
        boolean isPartitionPassed = false;
        while(nextNode.next != null) {
            if (((Integer)nextNode.data).intValue() > data.intValue() && !isPartitionPassed) {
                LinkedListNode oldNextNode = partitionElement.next;
                partitionElement.next = nextNode;
                preNextNode.next = nextNode.next;
                nextNode.next = oldNextNode;
                nextNode = preNextNode.next;
            } else if (((Integer)nextNode.data).intValue() == data.intValue()) {
                isPartitionPassed = true;
                preNextNode = nextNode;
                nextNode = nextNode.next;
            } else if ((Integer) nextNode.data < data.intValue() && isPartitionPassed){
                preNextNode.next = nextNode.next;
                if (itNodeBefore != null)
                    itNodeBefore.next = nextNode;
                else
                    head = nextNode;
                nextNode.next = partitionElement;
                itNodeBefore = nextNode;
                nextNode = preNextNode.next;
            } else {
                preNextNode = nextNode;
                nextNode = nextNode.next;
            }
        }
    }

    void partitionAroundElementBufferList(Integer data) {
        int dataInt = data.intValue();
        LinkedListNode nextNode = head;
        LinkedListNode beforeNode = null;
        LinkedListNode afertNode = null;
        LinkedListNode beforeHead = null;
        LinkedListNode afertHead = null;
        //boolean isPartitionPassed = false;
        while (nextNode != null) {
            int nextNodeDataInt = ((Integer)nextNode.data).intValue();
            if ( nextNodeDataInt > dataInt){
                if (afertNode == null) {
                    afertNode = new LinkedListNode(nextNode.data);
                    afertHead = afertNode;
                } else {
                    afertNode.next = new LinkedListNode(nextNode.data);
                    afertNode = afertNode.next;
                }
            } else if ( nextNodeDataInt < dataInt ) {
                if (beforeNode == null) {
                    beforeNode = new LinkedListNode(nextNode.data);
                    beforeHead = beforeNode;
                } else {
                    beforeNode.next = new LinkedListNode(nextNode.data);
                    beforeNode = beforeNode.next;
                }
            } else if ( nextNodeDataInt == dataInt ) {
                if (afertNode == null) {
                    afertNode = new LinkedListNode(nextNode.data);
                    afertHead = afertNode;
                } else {
                    LinkedListNode newAfterHead = new LinkedListNode(nextNode.data);
                    newAfterHead.next = afertHead;
                    afertHead = newAfterHead;
                }
            }
            nextNode = nextNode.next;
        }
        if (beforeHead != null ) {
            head = beforeHead;
            beforeNode.next = afertHead;
        } else {
            head = afertHead;
        }
    }

    public static void main(String[] args) {
        PartitionAroundElement myLinkedList = new PartitionAroundElement();
        myLinkedList.addToTail(2);
        myLinkedList.addToTail(6);
        myLinkedList.addToTail(0);
        myLinkedList.addToTail(3);
        myLinkedList.addToTail(4);
        myLinkedList.addToTail(1);
        myLinkedList.addToTail(3);
        myLinkedList.addToTail(5);
        myLinkedList.addToTail(10);

        myLinkedList.print();

        int partElement = 3;
        myLinkedList.partitionAroundElementInPlace(partElement);
        //myLinkedList.partitionAroundElementBufferList(partElement);
        System.out.println("After partitioning");
        myLinkedList.print();
    }
}
