package cracking.linkedlist;


import java.util.*;
/**
 * Created by I057833 on 12/12/2016.
 */
public class PalindromeList {

     boolean isPalindromeStack(MySinglyLinkedList list) {
            if (list == null)
                return false;
            Stack<MySinglyLinkedList.LinkedListNode> stack = new Stack();
            MySinglyLinkedList.LinkedListNode pointer = list.head;
            while(pointer != null) {
                stack.push(pointer);
                pointer = pointer.next;
            }
            MySinglyLinkedList.LinkedListNode pointer2 = list.head;
            while (pointer2 != null) {
                MySinglyLinkedList.LinkedListNode stackNode = stack.pop();
                if (!pointer2.data.equals(stackNode.data)) {
                    return false;
                }
                else {
                    pointer2 = pointer2.next;
                }
            }
            return true;
        }

    boolean isPalindromeStackOpt(MySinglyLinkedList list) {
        if (list == null)
            return false;
        MySinglyLinkedList.LinkedListNode slow = list.head;
        MySinglyLinkedList.LinkedListNode fast = list.head;
        Stack<MySinglyLinkedList.LinkedListNode> stack = new Stack();
        while(fast != null && fast.next != null) {
            stack.push(slow);
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        while (slow != null) {
            MySinglyLinkedList.LinkedListNode stackNode = stack.pop();
            if (!stackNode.data.equals(slow.data))
                return false;
            slow = slow.next;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromeList obj = new PalindromeList();
        MySinglyLinkedList<Character> list = new MySinglyLinkedList<>();
        String stringToList = "hello olleh";
        for(int i = 0; i < stringToList.length(); i++) {
            char c = stringToList.charAt(i);
            list.addToTail(c);
        }
        System.out.println("String " + stringToList + " is palindrome? " + obj.isPalindromeStackOpt(list));

        list = new MySinglyLinkedList<>();
        stringToList = "this is some random string";
        for(int i = 0; i < stringToList.length(); i++) {
            char c = stringToList.charAt(i);
            list.addToTail(c);
        }
        System.out.println("String " + stringToList + " is palindrome? " + obj.isPalindromeStackOpt(list));

        list = new MySinglyLinkedList<>();
        stringToList = "mehelloollehem";
        for(int i = 0; i < stringToList.length(); i++) {
            char c = stringToList.charAt(i);
            list.addToTail(c);
        }
        System.out.println("String " + stringToList + " is palindrome? " + obj.isPalindromeStackOpt(list));
    }
}
