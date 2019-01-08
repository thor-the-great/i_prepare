package path.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 *  146. LRU Cache
 * Hard
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
 * operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it
 * should invalidate the least recently used item before inserting a new item.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 */
class LRUCache {
    /**
     * This implementation uses fake no-empty node as head and tail, so it suppouse less checks for null
     */
    Map<Integer, Node> map = new HashMap();
    int capacity = 0;
    Node head, tail;


    public LRUCache(int c) {
        map.clear();
        capacity = c;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node n = map.get(key);
        remove(n);
        setHead(n);
        return n.val;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            if (map.size() >= capacity) {
                map.remove(tail.prev.key);
                remove(tail.prev);
            }
            Node n = new Node(key, value);
            setHead(n);
            map.put(key, n);
        } else {
            Node n = map.get(key);
            n.val = value;
            remove(n);
            setHead(n);
        }
    }

    void remove(Node n) {
        Node prev = n.prev;
        Node next = n.next;
        prev.next = next;
        next.prev = prev;
    }

    void setHead(Node n) {
        if (head.next != n) {
            n.next = head.next;
            head.next.prev = n;
            head.next = n;
            n.prev = head;
        }
    }

    class Node {
        Node next, prev;
        int val, key;

        Node(int k, int v) {
            key = k;
            val = v;
        }
    }
}
