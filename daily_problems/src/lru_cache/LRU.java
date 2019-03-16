package lru_cache;

import java.util.HashMap;
import java.util.Map;

/**
 * This problem was asked by Google.
 *
 * Implement an LRU (Least Recently Used) cache. It should be able to be initialized with a cache size n,
 * and contain the following methods:
 *
 * set(key, value): sets key to value. If there are already n items in the cache and we are adding a new item, then
 * it should also remove the least recently used item.
 *
 * get(key): gets the value at key. If no such key exists, return null.
 * Each operation should run in O(1) time.
 *
 */

class Node {
    int val, key;
    Node next, prev;
    Node(int key, int value) {
        this.key = key;
        this.val = value;
    }
}
public class LRU {
    //idea is to use custom DS - combined Map<Key, NaryTreeNode> and doubly linked list of Nodes where NaryTreeNode has key and value.
    //keep head and tail of list, so most recent element is in head and very last element is in tail
    //
    //having map gives us O(1) for lookup by key and add/remove. Having linked list gives us O(1) for head/end lookup
    //space - O(capacity) - map is O(capacity), linked list - too
    Node head;
    Node tail;
    Map<Integer, Node> map;
    int capacity;

    public LRU(int capacity) {
        this.capacity = capacity;
        map = new HashMap(capacity, 1.0f);
        head = null;
        tail = null;
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            setHead(n);
        } else {
            Node newN = new Node(key, value);
            if (map.size() < capacity) {
                setHead(newN);
            } else {
                map.remove(tail.key);
                remove(tail);
                setHead(newN);
            }
            map.put(key, newN);
        }
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node n = map.get(key);
        remove(n);
        setHead(n);
        return n.val;
    }

    void remove(Node n) {
        if (n.prev != null)
            n.prev.next = n.next;
        else
            head = n.next;

        if (n.next != null)
            n.next.prev = n.prev;
        else
            tail = n.prev;
    }

    void setHead(Node n) {
        n.next = head;
        n.prev = null;

        if (head != null)
            head.prev = n;

        head = n;

        if (tail == null)
            tail = head;
    }

    public static void main(String[] args) {
        int capacity = 5;
        LRU cache = new LRU(capacity);
        for (int i =0; i < capacity; i++) {
            cache.set(i, i);
        }
        System.out.println(cache.get(capacity - 1)); //capacity - 1
        System.out.println(cache.get(capacity + 1)); //null, key hasnt been added
        cache.set(capacity + 2, capacity + 2); //added 7
        cache.set(capacity + 1, capacity + 1); //added 6
        cache.set(capacity, capacity); //added 5
        System.out.println(cache.get(0)); //null, 0 must be pushed out of cache
        System.out.println(cache.get(capacity + 1));//capacity + 1 - 6
        System.out.println(cache.get(1)); // null, invalidated by recent adds
        System.out.println(cache.get(4)); // 4, should be still valid
        cache.set(capacity + 3, capacity + 3); //added 8
        cache.set(capacity + 4, capacity + 4); // added 9
        System.out.println(cache.get(4)); //4 - it's refreshed recently
        System.out.println(cache.get(2)); // null - should be invalidated at the moment
        System.out.println(cache.get(8)); // 8 - one of the recent adds
    }
}
