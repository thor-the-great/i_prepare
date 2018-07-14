package linked_list;

public class MyLinkedListSingly {

    /**
     * Initialize your data structure here.
     */

    ListNodeSingly head = null;
    int count;

    public MyLinkedListSingly() {
        head = null;
        count = 0;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index > count - 1 || head == null)
            return -1;
        ListNodeSingly node = head;
        for (int i = 1; i <= index; i++) {
            node = node.next;
        }
        return node.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        ListNodeSingly newHead = new ListNodeSingly(val);
        newHead.next = head;
        head = newHead;
        count++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        ListNodeSingly next = head;
        while (next.next != null)
            next = next.next;
        if (next != null) {
            ListNodeSingly newTail = new ListNodeSingly(val);
            next.next = newTail;
            count++;
        }
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > count || (head == null && index > 0))
            return;
        ListNodeSingly node = head;
        for (int i = 1; i < index; i++) {
            node = node.next;
        }
        ListNodeSingly newNode = new ListNodeSingly(val);
        ListNodeSingly oldNext = node != null ? node.next : null;
        newNode.next = oldNext;
        if (node != null)
            node.next = newNode;
        else
            head = newNode;
        count++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= count || head == null)
            return;
        ListNodeSingly node = head;
        ListNodeSingly oldPrev = null;
        for (int i = 1; i <= index; i++) {
            oldPrev = node;
            node = node != null ? node.next : null;
        }
        ListNodeSingly oldNext = node == null ? null : node.next;
        if (oldPrev != null) {
            oldPrev.next = oldNext;
        } else
            head = oldNext;
        count--;
    }

    public static void main(String[] args) {
        MyLinkedListSingly linkedList = new MyLinkedListSingly();
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

class ListNodeSingly {
    int val;
    ListNodeSingly next;

    ListNodeSingly(int val) {
        this.val = val;
    }
}


