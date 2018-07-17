package linked_list.singly_classic_problems;

import linked_list.ListNode;

public class Solution {

    /**
     * https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1205/
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        /*
        //iterative, accepted but slow, uses stack as DS
        Stack<ListNode> stack = new Stack();
        ListNode pointer = head;
        while (pointer != null) {
            stack.push(pointer);
            pointer = pointer.next;
        }

        head = stack.pop();
        pointer = head;
        while(!stack.empty()) {
            ListNode nextNode = stack.pop();
            pointer.next = nextNode;
            pointer = nextNode;
        }
        pointer.next = null;
        return head;*/

        //iterative, fast, saves previous, current and next nodes and just change their places
        /*ListNode prev = null, curr = head, next;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;*/

        //recursive approach. Line before return is super important, otherwise it's gonna be cycle on every iteration
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        //remove cycle of form 2->3->2->3 etc
        head.next = null;
        return newHead;
    }

    /**
     * https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1207/
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        //move to find first non-removable element, it will be a new head. same time
        //do removal for those leading nodes with value = val
        /*while(head != null && head.val == val)
            head = head.next;

        if (head == null)
            return head;*/
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode curr = fakeHead;
        while (curr.next != null) {
            if (curr.next.val != val) {
                curr = curr.next;
            } else {
                curr.next = curr.next.next;
            }

        }
        return fakeHead.next;
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode oddP = head;
        ListNode evenP = head.next;
        ListNode evenHead = head.next;
        while(oddP != null && evenP != null) {
            ListNode nextOddNode = null;
            if (oddP.next != null) {
                nextOddNode = oddP.next.next;
            }
            if (evenP.next != null) {
                evenP.next = evenP.next.next;
                evenP = evenP.next;
            } else {
                evenP.next = null;
                evenP = null;
            }
            oddP.next = nextOddNode;
            if (nextOddNode != null)
                oddP = nextOddNode;
        }
        oddP.next = evenHead;
        return head;
    }

    /**
     * https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1209/
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next==null)
            return true;

        ListNode fastPointer = head;
        ListNode middlePointer = head;
        while (fastPointer.next != null && fastPointer.next.next != null) {
            fastPointer = fastPointer.next.next;
            middlePointer = middlePointer.next;
        }

        ListNode secondHead = middlePointer.next;
        middlePointer.next = null;

        //reverse it, head will be in prev
        ListNode prev = null, next;
        while(secondHead != null) {
            next = secondHead.next;
            secondHead.next = prev;
            prev = secondHead;
            secondHead = next;
        }

        //now traverse lists and compare each element
        ListNode firstPointer = head;
        while(prev != null) {
            if (prev.val != firstPointer.val)
                return false;
            prev = prev.next;
            firstPointer = firstPointer.next;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();

        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        //System.out.println(StringUtils.singlyListNodeToString(one));
        //ListNode reversed = obj.reverseList(one);
        //System.out.println(StringUtils.singlyListNodeToString(reversed));

        //ListNode oddEvenList = obj.oddEvenList(one);
        //System.out.println(StringUtils.singlyListNodeToString(oddEvenList));
        ListNode one1 = new ListNode(1);
        ListNode zero1 = new ListNode(0);
        ListNode one2 = new ListNode(1);
        one1.next = zero1;
        zero1.next = one2;

        System.out.println(obj.isPalindrome(one1));
    }
}
