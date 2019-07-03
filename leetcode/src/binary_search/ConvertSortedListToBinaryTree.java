/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package binary_search;

import list.ListNode;
import trees.TreeNode;
import trees.TreeUtils;

/**
 * 109. Convert Sorted List to Binary Search Tree
 * Medium
 *
 * Given a singly linked list where elements are sorted in ascending order, convert it to a
 * height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth
 * of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted linked list: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class ConvertSortedListToBinaryTree {

  public TreeNode sortedListToBST(ListNode head) {
    return sortedListToBSTSplit(head);
  }

  ListNode h;

  /**
   * Idea - emulate inorder traversal of the tree. Keep counters similar to binary search - halving
   * parts unless size of the part became 0.
   * Keep global variable for the head that will keep the state, every time we advance in our traversal
   * we shift it by one by calling node = node.next
   * Time - O(n), space O(lgn) due to call stack
   * @param head
   * @return
   */
  public TreeNode sortedListToBSTInorder(ListNode head) {
    if (head == null)
      return null;
    this.h = head;
    int size = treeSize(head);
    return helperInorder(0, size - 1);
  }

  TreeNode helperInorder(int l, int r) {
    if (l > r)
      return null;

    int m = (l + r)/2;

    TreeNode left = helperInorder(l, m - 1);

    TreeNode node = new TreeNode(h.val);
    node.left = left;
    h = h.next;
    node.right = helperInorder( m + 1, r);
    return node;
  }

  int treeSize(ListNode head) {
    int s = 0;
    ListNode n = head;
    while (n != null) {
      s++;
      n = n.next;
    }
    return s;
  }

  /**
   * Idea - utilize property of inorder traversal for the BST. Find medium element of the list, split
   * it into halves based on that element. This element became a local root, for each halve repeat
   * the process.
   * Due to fact that we have a list we need a 2 pointers technique for finding that middle element
   * @param head
   * @return
   */
  public TreeNode sortedListToBSTSplit(ListNode head) {
    if (head == null)
      return null;

    ListNode mid = helper(head);
    TreeNode res = new TreeNode(mid.val);

    if (mid == head)
      return res;

    res.left = sortedListToBST(head);
    res.right = sortedListToBST(mid.next);

    return res;
  }

  ListNode helper(ListNode head) {
    if (head == null)
      return null;

    //slow will the the middle element
    ListNode slow = head;
    ListNode fast = head;
    //prev will be the last element in the first halve, we'll be disconnected at the end
    ListNode prev = null;

    //advansing two pointers. Prev holds that ref to our previous slow.
    while (fast != null && fast.next != null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }

    if (prev != null) {
      prev.next = null;
    }

    return slow;
  }
}
