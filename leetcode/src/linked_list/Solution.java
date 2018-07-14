package linked_list;

import java.util.List;

public class Solution {

    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }

        @Override
        public String toString() {
            return Integer.toString(val);
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;
        ListNode slow = head.next;
        ListNode fast = slow != null ? slow.next : null;
        while (slow != null && fast != null) {
            if (slow == fast)
                return true;
            slow = slow.next;
            if (fast.next != null)
                fast = fast.next.next;
            else
                fast = null;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return null;
        ListNode slow = head;
        ListNode fast = head;

        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode afterPointer = head;
                while(slow != afterPointer) {
                    slow = slow.next;
                    afterPointer = afterPointer.next;
                }
                return afterPointer;
            }
        }
        return null;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        ListNode pointerA = headA;
        while (pointerA != null) {
            pointerA = pointerA.next;
            lenA++;
        }

        int lenB = 0;
        ListNode pointerB = headB;
        while (pointerB != null) {
            pointerB = pointerB.next;
            lenB++;
        }

        if (lenB > lenA) {
            ListNode tmp = headB;
            headB = headA;
            headA = tmp;
        }

        int diffCount = Math.abs(lenA - lenB);
        ListNode longPointer = headA;
        for (int i =0; i < diffCount; i++) {
            longPointer = longPointer.next;
        }
        ListNode shortPointer = headB;
        //now both pointers are the same distance from the intersection
        while(longPointer != shortPointer && shortPointer != null) {
            longPointer = longPointer.next;
            shortPointer = shortPointer.next;
        }

        if (shortPointer == null)
            return null;
        else
            return shortPointer;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode shiftedPointer = head;
        for (int i = 0; i < n; i++) {
            shiftedPointer = shiftedPointer.next;
        }
        //now pointer is shifted on N positions, for launch second pointer and move them together
        ListNode pointer = head;
        ListNode prevPointer = null;
        while (shiftedPointer != null) {
            shiftedPointer = shiftedPointer.next;
            prevPointer = pointer;
            pointer = pointer.next;
        }

        if (prevPointer == null)
            head =  head.next;
        else if (pointer!= null) {
            prevPointer.next = pointer.next;
        }
        return head;
    }

    public static void main(String[] args) {

        Solution obj = new Solution();

        /*ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        one.next = two;
        two.next = one;
        System.out.println(obj.detectCycle(one));*/

        /*ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        two.next = three;
        ListNode four = new ListNode(4);*/

        //System.out.println(obj.getIntersectionNode(three, two));
        System.out.println(obj.getIntersectionNode(null, null));

        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        //two.next = three;
        //three.next = four;
        //four.next = five;

        ListNode proc = obj.removeNthFromEnd(one, 2);

        System.out.println(proc);
    }
}
