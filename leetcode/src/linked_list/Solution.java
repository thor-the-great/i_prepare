package linked_list;

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

    public ListNode getIntersectionNodeSecondAttempt(ListNode headA, ListNode headB) {
        //assume first list is longer
        int sizeA = getSize(headA);
        int sizeB = getSize(headB);
        if (sizeB > sizeA) {
            ListNode tmp = headA;
            headA = headB;
            headB = tmp;
        }
        int diff = Math.abs(sizeA - sizeB);
        while (diff > 0) {
            headA = headA.next;
            diff--;
        }
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }

    int getSize(ListNode headA) {
        ListNode root = headA;
        int s = 0;
        while (root != null) {
            s++;
            root = root.next;
        }
        return s;
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

    /**
     * Add Two Numbers
     *
     * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in
     * reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     *
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * Example:
     *
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //idea is following:
        //- first create mock node that will have our head, need it to return result
        //- then iterate over both lists and calculate value and carry. Create new result node with the %10
        //and keep carry for the next node.
        //when finished - check for carry, if not 0 - add one more node.
        int carry = 0;
        ListNode mockHead = new ListNode(-1);
        ListNode curr = mockHead;
        while (l1 != null || l2 != null) {
            int val = carry;
            if (l1 != null) {
                val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                val += l2.val;
                l2 = l2.next;
            }
            ListNode nextNode = new ListNode(val % 10);
            carry = val / 10;
            curr.next = nextNode;
            curr = curr.next;
        }
        if (carry > 0) {
            ListNode n = new ListNode(carry);
            curr.next = n;
        }
        return mockHead.next;
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
