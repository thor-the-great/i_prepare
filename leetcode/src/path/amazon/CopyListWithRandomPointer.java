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
