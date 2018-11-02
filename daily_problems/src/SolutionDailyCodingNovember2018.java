import linked_list.ListNode;
import linked_list.ListUtils;
import linked_list.StringUtils;
import trees.BST;
import trees.BSTNode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class SolutionDailyCodingNovember2018 {

    public boolean isPalindrome2(ListNode head) {
        if (head == null) return true;

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast == slow && slow.next == null) return true;

        ListNode secondHead = slow.next;
        slow.next = null;

        ListNode p1 = secondHead;
        ListNode p2 = p1.next;

        while (p2 != null) {
            ListNode tmp = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = tmp;
        }
        secondHead.next = null;
        ListNode right = p2 == null ? p1 : p2;
        ListNode left = head;

        while (right != null) {
            if (left.val != right.val)
                return false;
            right = right.next;
            left = left.next;
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        /*
        if (head == null) return true;
        //start rolling two pointer - slow and fast
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //
        if (slow == fast && slow.next == null)
            return true;

        //save second head of the subtree
        ListNode secondHead = slow.next;
        slow.next = null;
        ListNode p1 = secondHead;
        ListNode p2 = p1.next;
        while(p1 !=null && p2 != null) {
            ListNode temp = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = temp;
        }
        secondHead.next = null;
        //compare two sublists now
        ListNode p = p2==null ? p1 :p2;
        ListNode q = head;
        while (p!=null) {
            if ( p.val != q.val )
                return false;
            p = p.next;
            q = q.next;
        }
        return true;*/
        return isPalindrome2(head);
    }

    public static void main(String[] args) {
        SolutionDailyCodingNovember2018 obj = new SolutionDailyCodingNovember2018();

        ListNode head = ListUtils.getSinglyLinkedList(new int[] {1, 2, 5, 2, 1});
        System.out.println(obj.isPalindrome(head));

        head = ListUtils.getSinglyLinkedList(new int[] {1});
        System.out.println(obj.isPalindrome(head));

        head = ListUtils.getSinglyLinkedList(new int[] {1, 2});
        System.out.println(obj.isPalindrome(head));

        head = ListUtils.getSinglyLinkedList(new int[] {1, 2, 2, 1});
        System.out.println(obj.isPalindrome(head));

        head = ListUtils.getSinglyLinkedList(new int[] {1, 2, 4, 1});
        System.out.println(obj.isPalindrome(head));

        head = ListUtils.getSinglyLinkedList(new int[] {1, 2, 1});
        System.out.println(obj.isPalindrome(head));

        head = ListUtils.getSinglyLinkedList(new int[] {1, 3, 3, 1});
        System.out.println(obj.isPalindrome(head));

        head = ListUtils.getSinglyLinkedList(new int[] {2, 3, 4, 1});
        System.out.println(obj.isPalindrome(head));
    }
}
