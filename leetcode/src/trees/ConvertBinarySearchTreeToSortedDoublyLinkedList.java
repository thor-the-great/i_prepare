package trees;

import java.util.Stack;

/**
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List
 * Medium
 *
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 *
 * You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a
 * doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element,
 * and the successor of the last element is the first element.
 *
 * We want to do the transformation in place. After the transformation, the left pointer of the tree node should
 * point to its predecessor, and the right pointer should point to its successor. You should return the pointer to
 * the smallest element of the linked list.
 *
 * Example 1:
 *
 *
 *
 * Input: root = [4,2,5,1,3]
 *
 *
 * Output: [1,2,3,4,5]
 *
 * Explanation: The figure below shows the transformed BST. The solid line indicates the successor relationship, while
 * the dashed line means the predecessor relationship.
 *
 * Example 2:
 *
 * Input: root = [2,1,3]
 * Output: [1,2,3]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 * Explanation: Input is an empty tree. Output is also an empty Linked List.
 * Example 4:
 *
 * Input: root = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * -1000 <= Node.val <= 1000
 * Node.left.val < Node.val < Node.right.val
 * All values of Node.val are unique.
 * 0 <= Number of Nodes <= 2000
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {

    /**
     * Traverse tree in iterative way, keep the node from previous step, assign left and right nodes as we visit
     * at the end take care of head (it doesn't have left) and last one (it doesn't have right)
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        if (root == null)
            return root;

        Node head = null;
        Node n = null;

        Node cur = root;
        Stack<Node> s = new Stack();

        while (cur != null || !s.isEmpty()) {
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }
            //visit
            cur = s.pop();
            if (head == null)
                head = cur;

            if (n != null) {
                n.right = cur;
                cur.left = n;
            }
            n = cur;
            cur = cur.right;
        }

        head.left = n;
        n.right = head;

        return head;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}


