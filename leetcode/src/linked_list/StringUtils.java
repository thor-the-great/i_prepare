package linked_list;

import java.util.List;

public class StringUtils {

    /*public static String listToString(List<Integer> a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i =0;i < a.size(); i++) {
            sb.append(a.get(i));
            if (i < a.size() - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }*/

    public static String listToString(List<Object> a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i =0;i < a.size(); i++) {
            sb.append(a.get(i));
            if (i < a.size() - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static String singlyListNodeToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode pointer = head;
        while(pointer != null) {
            sb.append(pointer.toString());
            pointer = pointer.next;
            if (pointer != null)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
