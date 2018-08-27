package cracking.linkedlist;

import java.util.LinkedList;

/**
 * Created by thor on 12/11/16.
 */
public class SumLists {

    MySinglyLinkedList.LinkedListNode sumListsReverse(MySinglyLinkedList.LinkedListNode list1Head, MySinglyLinkedList.LinkedListNode list2Head) {
        if (list1Head == null && list2Head == null)
            return null;
        if (list1Head == null)
            return list2Head;
        if (list2Head == null)
            return list1Head;
        MySinglyLinkedList.LinkedListNode<Integer> list1HeadTmp = list1Head;
        MySinglyLinkedList.LinkedListNode<Integer> list2HeadTmp = list2Head;
        int shifted = 0;
        MySinglyLinkedList.LinkedListNode result = null;
        MySinglyLinkedList.LinkedListNode resultPointer = null;
        while( list1HeadTmp != null || list2HeadTmp != null ) {
            int nextDigit = (list1HeadTmp == null ? 0 : list1HeadTmp.data)
                    + (list2HeadTmp == null ? 0 : list2HeadTmp.data)
                    + shifted;
            shifted = nextDigit / 10;
            nextDigit = nextDigit % 10;
            if (result == null) {
                result = new MySinglyLinkedList.LinkedListNode(nextDigit);
                resultPointer = result;
            }
            else {
                MySinglyLinkedList.LinkedListNode nextNode = new MySinglyLinkedList.LinkedListNode(nextDigit);
                resultPointer.next = nextNode;
                resultPointer = resultPointer.next;
            }
            if (list1HeadTmp != null)
                list1HeadTmp = list1HeadTmp.next;
            if (list2HeadTmp != null)
                list2HeadTmp = list2HeadTmp.next;
        }
        if (shifted > 0 ) {
            MySinglyLinkedList.LinkedListNode nextNode = new MySinglyLinkedList.LinkedListNode(shifted);
            resultPointer.next = nextNode;
        }
        return result;
    }

    MySinglyLinkedList.LinkedListNode sumListsDirect(MySinglyLinkedList.LinkedListNode list1Head, MySinglyLinkedList.LinkedListNode list2Head) {
        if (list1Head == null && list2Head == null)
            return null;
        if (list1Head == null)
            return list2Head;
        if (list2Head == null)
            return list1Head;
        list1Head = reverseList(list1Head);
        list2Head = reverseList(list2Head);
        return reverseList(sumListsReverse(list1Head, list2Head));
    }

    private MySinglyLinkedList.LinkedListNode reverseList(MySinglyLinkedList.LinkedListNode list1Head) {
        MySinglyLinkedList.LinkedListNode currentNode = null;
        MySinglyLinkedList.LinkedListNode tmpNode = null;
        while (list1Head != null) {
            if (tmpNode == null)
                currentNode = new MySinglyLinkedList.LinkedListNode(list1Head.data);
            else
                currentNode = tmpNode;
            if (list1Head.next != null ) {
                tmpNode = new MySinglyLinkedList.LinkedListNode(list1Head.next.data);
                tmpNode.next = currentNode;
            }
            list1Head = list1Head.next;
        }
        return tmpNode == null ? currentNode : tmpNode;
    }

    static void printListByHead(MySinglyLinkedList.LinkedListNode node) {
        while (node != null) {
            System.out.print(node.data + " -> ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        MySinglyLinkedList<Integer> list1 = new MySinglyLinkedList<>();
        list1.addToTail(7);
        list1.addToTail(1);
        list1.addToTail(6);

        MySinglyLinkedList<Integer> list2 = new MySinglyLinkedList<>();
        list2.addToTail(5);
        list2.addToTail(9);
        list2.addToTail(2);

        SumLists obj = new SumLists();
        MySinglyLinkedList.LinkedListNode result = obj.sumListsReverse(list1.head, list2.head);
        System.out.println("Result of sum reversal is : ");
        printListByHead(result);

        list1 = new MySinglyLinkedList<>();
        list1.addToTail(5);
        list1.addToTail(9);

        list2 = new MySinglyLinkedList<>();
        list2.addToTail(5);
        list2.addToTail(4);
        list2.addToTail(3);

        result = obj.sumListsReverse(list1.head, list2.head);
        System.out.println("\nResult of sum reversal is : ");
        printListByHead(result);
        //----------------------

        list1 = new MySinglyLinkedList<>();
        list1.addToTail(6);
        list1.addToTail(1);
        list1.addToTail(7);

        list2 = new MySinglyLinkedList<>();
        list2.addToTail(2);
        list2.addToTail(9);
        list2.addToTail(5);

        result = obj.sumListsDirect(list1.head, list2.head);
        System.out.println("\nResult of sum direct is : ");
        printListByHead(result);

        list1 = new MySinglyLinkedList<>();
        list1.addToTail(9);
        list1.addToTail(5);

        list2 = new MySinglyLinkedList<>();
        list2.addToTail(3);
        list2.addToTail(4);
        list2.addToTail(5);

        result = obj.sumListsDirect(list1.head, list2.head);
        System.out.println("\nResult of sum direct is : ");
        printListByHead(result);
    }
}
