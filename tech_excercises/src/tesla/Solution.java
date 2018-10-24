package tesla;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * Create an in memory cache. The cache should be able to handle a generic type defined at instantiation time.
 * The cache needs to support two types of eviction. Time based (original age of item) and size based
 * (total size of cache).
 * When evicting based on size, we should evict the least recently accessed items first (LRU approach).
 *
 * The cache must be thread safe.
 *
 * You should use any 3rd party libraries that you feel are appropriate. You should implement this in as if you were
 * writing code for
 * production.
 *
 * The "Info" button in CoderPad lists the various libraries that are available to you in Java.
 *
 */
class Solution {

    void doTest() {
        Cache<Integer> c = new Cache();
        IntStream.range(0, 7).forEach(n-> {
            c.put(Integer.toString(n), n);
            try {
                Thread.sleep( 100+ n);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } );

        System.out.println(c.retrieve("1"));
        System.out.println(c.retrieve("5"));

        c.sizeEvict(3);
        System.out.println("After size eviction");

        IntStream.range(0, 7).forEach(n-> {
            System.out.println(c.retrieve(Integer.toString(n)));
        } );

        long ts = System.currentTimeMillis();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        c.put("7", 7);
        c.put("8", 8);

        c.timeEvict(ts);
        System.out.println("After time eviction");
        System.out.println(c.retrieve("3"));
        System.out.println(c.retrieve("8"));
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.doTest();
    }

    class Cache <V> {
        Map<String, Node<V>> m;
        Queue<Node<V>> q;
        Node head, tail;

        Cache() {
            m = new HashMap();
            q = new LinkedList();
            head = null;
            tail = null;
        }

        public void put(String key, V value) {
            Node<V> n = new Node(key, value, System.currentTimeMillis());
            setHead(n);
            m.put(key, n);
            q.add(n);
        }

        public V retrieve(String key) {
            if (!m.containsKey(key)) return null;
            Node<V> n = m.get(key);
            removeNode(n);
            setHead(n);
            return n.val;
        }

        public void sizeEvict(int cacheSize) {
            if (cacheSize < m.size()) {
                int numToRemove = m.size() - cacheSize;
                while (numToRemove > 0) {
                    String key = tail.key;
                    m.remove(key);
                    removeNode(tail);
                    numToRemove--;
                }
            }
        }

        public void timeEvict(long age){
            while (!q.isEmpty()) {
                Node n = q.peek();
                if (n.time <= age) {
                    q.poll();
                    String key = n.key;
                    removeNode(n);
                    m.remove(key);
                } else
                    break;
            }
        }

        void setHead(Node n) {
            n.next = head;
            n.prev = null;

            if (head != null) head.prev = n;

            head = n;

            if (tail == null) tail = head;
        }

        void removeNode(Node n) {
            if (n.prev != null) n.prev.next = n.next;
            else head = n.next;

            if (n.next != null) n.next.prev = n.prev;
            else tail = n.prev;
        }
    }

    class Node<V> {
        V val;
        String key;
        Node next, prev;
        long time;

        Node(String key, V value, long time) {
            this.key = key;
            this.val = value;
            this.time = time;
        }
    }
}
