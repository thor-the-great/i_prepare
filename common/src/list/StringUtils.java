package list;

import java.util.List;
import list.ListNode;

public class StringUtils {

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

    public static String listStringsToString(List<String> a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (a != null) {
            for (int i = 0; i < a.size(); i++) {
                sb.append("'").append(a.get(i)).append("'");
                if (i < a.size() - 1)
                    sb.append(", ");
            }
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
