package cracking.moderate;

import java.util.HashMap;
import java.util.Map;

//least recently used
public class LRUCache {
    int capacity = 0;
    Map<Integer, Node> map = new HashMap();
    Node head;
    Node tail;

    public LRUCache(int c) {
        capacity = c;
        map.clear();
        head = null;
        tail = null;
    }

    public String get(int key) {
        Node n = map.get(key);
        if (n == null) return null;
        //change the priority
        if (head != n) {
            removeNode(n);
            insertHead(n);
        }
        return n.value;
    }

    public void add(int key, String val) {
        if (map.size() >= capacity && tail != null) {
            map.remove(tail.key);
            removeNode(tail);
        }
        Node n = new Node(key, val);
        insertHead(n);
        map.put(key, n);
    }

    private void removeNode(Node n) {
        if (n == null)
            return;
        Node next = n.next;
        Node prev = n.prev;
        if (next != null) next.prev = prev;
        if (prev != null) prev.next = next;
        if (n == head) head = n.next;
        if (n == tail) tail = n.prev;
    }

    private void insertHead(Node n) {
        if (head == null) {
            head = n;
            tail = n;
        } else {
            n.next = head;
            head.prev = n;
            head = n;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        cache.add(1, "Kiev");
        cache.add(2, "Zapor");
        cache.add(3, "Dnipro");
        cache.add(4, "Lviv");

        System.out.println(cache.get(1));
        cache.get(2);
        cache.get(2);
        cache.add(5, "Krakow");
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(5));
        cache.add(6, "San Francisco");
        System.out.println(cache.get(4));
    }
}

class Node {
    Node prev, next;

    int key;
    String value;

    Node(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
