package path.google;

import list.ListNode;
import list.StringUtils;

/**
 * 25. Reverse Nodes in k-Group
 * Hard
 *
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a
 * multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 *
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Note:
 *
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 */
public class ReverseNodesInKGroups {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode res = head;
        ListNode start = null;
        ListNode end = head;

        while(head!=null){
            int i=0;

            //take the part of linkedlist of size k
            while(end!=null && i<k){
                end = end.next;
                i++;
            }

            //if size!=k then break
            if(i!=k){
                break;
            }

            //note down the curhead and end of the current list portion
            ListNode prev = end;
            ListNode curHead = head;

            //reverse the selected portion of list
            while(head!=end){
                ListNode forward = head.next;
                head.next = prev;
                prev = head;
                head = forward;
            }

            //make prev start to point at the start of the reversed list and move it to current head
            if(start!=null){
                start.next = prev;
                start = curHead;
            }else{
                res = prev;
                start = curHead;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        ReverseNodesInKGroups obj = new ReverseNodesInKGroups();
        ListNode listHead;

        listHead = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        obj.reverseKGroup(listHead, 2);
        System.out.println(StringUtils.singlyListNodeToString(listHead));
    }
}
