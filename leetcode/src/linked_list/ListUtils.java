package linked_list;

public class ListUtils {

    public static ListNode getSinglyLinkedList(int[] values) {
        if (values == null || values.length == 0)
            return null;
        ListNode prev = null;
        for (int i = values.length - 1; i >=0; i--) {
            ListNode n = new ListNode(values[i]);
            n.next = prev;
            prev = n;
        }
        return prev;
    }
}
