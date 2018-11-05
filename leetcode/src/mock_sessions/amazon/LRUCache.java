package mock_sessions.amazon;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    Map<Integer, Node> map = new HashMap();
    int capacity = 0;
    Node head, tail;


    public LRUCache(int c) {
        map.clear();
        capacity = c;
        head = null;
        tail = null;
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
                map.remove(tail.key);
                remove(tail);
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
        if (prev != null) {
            prev.next = next;
        } else
            head = next;

        if(next != null) {
            next.prev = prev;
        } else
            tail = n.prev;
    }

    void setHead(Node n) {
        n.next = head;
        n.prev = null;
        if (head != null) {
            head.prev = n;
        }
        head = n;

        if (tail == null)
            tail = head;
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
