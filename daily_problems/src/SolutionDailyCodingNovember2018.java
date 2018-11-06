import diff_problems.TreeNode;
import linked_list.ListNode;
import linked_list.ListUtils;

import java.util.*;

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
        return isPalindrome2(head);
    }

    /**
     * This problem was asked by Pinterest.
     *
     * Given an integer list where each number represents the number of hops you can make, determine whether you can
     * reach to the last index starting at index 0.
     *
     * For example, [2, 0, 1, 0] returns true while [1, 1, 0, 1] returns false.
     *
     * @param hops
     * @return
     */
    public boolean hopToLast(int[] hops) {
        //idea is simple - there are only two ways of going from the index, so if we reach it for the second
        //time - we only can go the same way, so doesn't make sense to repeat any of visited steps.
        //thus we keep list of visited indexes, and visit only unvisited ones.
        //this leads to a recursive implementation
        //O(n) - every element visited n times
        //O(1) - if we re-using same array to store visited state, if we can't change it - O(n) for extra array
        if (hops == null || hops.length == 0)
            return false;
        this.hops = hops;
        return helper(0);
    }

    int[] hops;

    boolean helper(int index) {
        if (index == hops.length - 1)
            return true;
        if (index < 0 || index >= hops.length || hops[index] == Integer.MIN_VALUE)
            return false;
        int nextIndex = hops[index];
        hops[index] = Integer.MIN_VALUE;
        return helper(index + nextIndex) || helper(index - nextIndex);
    }

    List<Integer> binaryTreeLevelOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            res.add(n.val);
            if (n.left != null)
                q.add(n.left);
            if (n.right != null)
                q.add(n.right);
        }
        return res;
    }

    /**
     * This problem was asked by Cisco.
     *
     * Given an unsigned 8-bit integer, swap its even and odd bits. The 1st and 2nd bit should be swapped, the
     * 3rd and 4th bit should be swapped, and so on.
     *
     * For example, 10101010 should be 01010101. 11100010 should be 11010001.
     *
     * Bonus: Can you do this in one line?
     * @param num
     * @return
     */
    int swapBits(int num) {
        //idea is - we need to shift right all even bits and shift left all odd bits
        //create mask first for every bit type, then shift separately and combine
        /*int oddBits = num & 85; //85 is 01010101)
        int evenBits = num & 170; //170 is 10101010)
        evenBits >>= 1; //shift even bits to the left
        oddBits <<= 1; //shift odd bits to the right
        return evenBits | oddBits; //combine both shifts*/
        //short version of the same
        return ((num & 85) << 1) | ((num & 170) >> 1);
    }

    public static void main(String[] args) {
        SolutionDailyCodingNovember2018 obj = new SolutionDailyCodingNovember2018();

        System.out.println("---- check if singly linked list is a palindrome ----");
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

        System.out.println("---- check if possible to reach last element from 0 in array of hops -----");

        System.out.println(obj.hopToLast(new int[] {2, 0, 1, 0})); //true
        System.out.println(obj.hopToLast(new int[] {1, 1, 0, 1})); //false
        System.out.println(obj.hopToLast(new int[] {3, 3, 0, 2, 2})); //true
        System.out.println(obj.hopToLast(new int[] {1, 1, 0, 5})); //false

        System.out.println("---- print binary tree in level order ---");
        TreeNode root = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3,
                        new TreeNode(4), new TreeNode(5)));
        List<Integer> levelOrder = obj.binaryTreeLevelOrder(root);
        levelOrder.forEach(i-> System.out.print(i + " "));
        System.out.println();

        System.out.println("--- swap odd and even bits of number (8bit int)----");
        System.out.println(Integer.toBinaryString(
                obj.swapBits(Integer.parseInt("10101010", 2))));//01010101
        System.out.println(Integer.toBinaryString(
                obj.swapBits(Integer.parseInt("11100010", 2))));//11010001
    }
}
