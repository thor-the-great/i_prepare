package node_lock;

/**
 * This problem was asked by Google.
 *
 * Implement locking in a binary tree. A binary tree node can be locked or unlocked only if all of its descendants or ancestors are not locked.
 *
 * Design a binary tree node class with the following methods:
 *
 * is_locked, which returns whether the node is locked
 * lock, which attempts to lock the node. If it cannot be locked, then it should return false. Otherwise, it should lock it and return true.
 * unlock, which unlocks the node. If it cannot be unlocked, then it should return false. Otherwise, it should unlock it and return true.
 * You may augment the node to add parent pointers or any other property you would like. You may assume the class is used in a single-threaded program, so there is no need for actual locks or mutexes. Each method should run in O(helper2), where helper2 is the height of the tree.
 */
public class Solution {

    //we keep the flag for the current node, plus number of locked descendants for each node
    //to check if lock/unlock possible we just check number of locked descendants, if it's > 0 - operation is not possible

    public boolean isLocked(Node node) {
        return node.isLocked;
    }

    public boolean lock(Node node) {
        if (node.isLocked || !checkLockUnlock(node))
            return false;

        node.isLocked = true;
        Node nextNode = node.parent;
        while(nextNode != null) {
            nextNode.lockCount++;
            nextNode = nextNode.parent;
        }
        return true;
    }

    public boolean unlock(Node node) {
        if (!node.isLocked || !checkLockUnlock(node))
            return false;
        node.isLocked = false;
        Node nextNode = node.parent;
        while(nextNode != null) {
            nextNode.lockCount--;
            nextNode = nextNode.parent;
        }
        return true;
    }

    private boolean checkLockUnlock(Node node) {
        if (node.lockCount > 0 )
            return false;
        Node nextNode = node.parent;
        while(nextNode != null) {
            if (nextNode.isLocked)
                return false;
            nextNode = nextNode.parent;
        }
        return true;
    }

}

class Node {

    Node(int val, Node left, Node right, Node parent) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    int val;
    Node left;
    Node right;
    int lockCount = 0;
    boolean isLocked = false;
    Node parent;
}
