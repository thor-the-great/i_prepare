package path.amazon;

import util.RandomListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 138. Copy List with Random Pointer
 * Medium
 * A linked list is given such that each node contains an additional random pointer which could point to any node in
 * the list or null.
 *
 * Return a deep copy of the list.
 */
public class CopyListWithRandomPointer {

    Map<RandomListNode, RandomListNode> map;

    public RandomListNode copyRandomList(RandomListNode head) {
        //return copyRandomListMap(head);
        return copyRandomListInsertMidNodes(head);
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * O(1) space - clone node and re-use original list by doing
     * orig_node.next = clone_node;
     * clone_node.next = orig_node.next;
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        Node fake = new Node(-1);
        fake.next = head;

        //clone main node, insert new nodes as nexts for original nodes
        Node n = head;
        while (n != null) {
            Node newNode = new Node(n.val);
            Node nNext = n.next;
            n.next = newNode;
            newNode.next = nNext;
            n = nNext;
        }

        //connect random pointers of clones to clones of randomes
        n = fake.next;
        while (n != null) {
            if (n.random != null) {
                n.next.random = n.random.next;
            }
            n = n.next.next;
        }

        //unwire original and new nodes, prepare the new head node for result
        n = fake.next;
        Node res = n.next;
        while (n != null) {
            Node nn = n.next;
            n.next = nn.next;
            if (n.next != null) {
                nn.next = nn.next.next;
            }
            n = n.next;
        }
        return res;
    }

    /**
     * Idea is - create node copy right in the original list, just put every copy node after the original one and
     * re-point original next to the copy.
     * Then iterate one more time and for every random of original point copy random to the next after that copy.
     * Last thing is to unwaive original and copy lists
     * @param head
     * @return
     */
    public RandomListNode copyRandomListInsertMidNodes(RandomListNode head) {
        if (head == null)
            return null;
        RandomListNode n = head;
        while (n != null) {
            RandomListNode copyNode = new RandomListNode(n.label);
            copyNode.next = n.next;
            n.next = copyNode;
            n = copyNode.next;
        }

        n = head;
        while (n != null) {
            if (n.random != null) {
                n.next.random = n.random.next;
            }
            n = n.next.next;
        }

        RandomListNode oldListNode = head; // A->B->C
        RandomListNode copyListNode = head.next; // A'->B'->C'
        RandomListNode copyHead = head.next;
        while (oldListNode != null) {
            oldListNode.next = oldListNode.next.next;
            copyListNode.next = (copyListNode.next != null) ? copyListNode.next.next : null;
            oldListNode = oldListNode.next;
            copyListNode = copyListNode.next;
        }
        return copyHead;
    }

    public RandomListNode copyRandomListMap(RandomListNode head) {
        map = new HashMap();
        if (head == null)
            return null;
        RandomListNode n = head;
        RandomListNode prev = null;
        while (n != null) {
            RandomListNode copyNode = getOrCreateCopyNode(n);
            if (n.random != null) {
                copyNode.random = getOrCreateCopyNode(n.random);
            }
            if (prev != null)
                prev.next = copyNode;
            n = n.next;
            prev = copyNode;
        }
        return map.get(head);
    }

    RandomListNode getOrCreateCopyNode(RandomListNode origin) {
        RandomListNode copyNode = null;
        if (!map.containsKey(origin)) {
            copyNode = new RandomListNode(origin.label);
            map.put(origin, copyNode);
        } else {
            copyNode = map.get(origin);
        }
        return copyNode;
    }
}
