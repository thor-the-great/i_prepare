package graphs;

import java.util.Comparator;

/**
 * This is min binary heap
 */
public class MinPQ<Key> {
    private Key[] pq;                    // store items at indices 1 to n
    private int n;                       // number of items on priority queue
    private Comparator<Key> comparator;  // optional comparator

    MinPQ() {
        this(1);
    }

    MinPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MinPQ(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes a priority queue from the array of keys.
     * <p>
     * Takes time proportional to the number of keys, using sink-based heap construction.
     *
     * @param  keys the array of keys
     */
    public MinPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < n; i++)
            pq[i+1] = keys[i];
        for (int k = n/2; k >= 1; k--)
            sink(k);
    }

    public Key getMin() {
        if (n == 0)
            return null;
        return pq[1];
    }

    public void insert(Key k) {
        if (n == pq.length - 1) {
            resize(2*n);
        }
        n++;
        pq[n] = k;
        swim(n);
    }

    public Key delMin() {
        if (n == 0)
            return null;
        Key k = pq[1];
        exch(1, n);
        n--;
        sink(1);
        pq[n+1] = null;
        return k;
    }

    private void resize(int newCap) {
        Key[] tmp = (Key[]) new Object[newCap];
        for (int i = 1; i < pq.length; i++) {
            tmp[i] = pq[i];
        }
        pq = tmp;
    }

    //heap functions
    private void sink(int k) {
        while(n >= 2*k) {
            int j = 2*k;
            if (j < n && greater(j, j + 1))
                j++;
            if (!greater(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }

    private void swim(int k) {
        while(k > 1 && greater(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    //helper functions
    boolean greater(int i, int j) {
        if (comparator == null)
            return ((Comparable<Key>)pq[i]).compareTo(pq[j]) > 0;
        else
            return comparator.compare(pq[i], pq[j]) > 0;
    }

    void exch(int k, int j) {
        Key tmp = pq[k];
        pq[k] = pq[j];
        pq[j] = tmp;
    }

    public static void main(String[] args) {
        MinPQ<Integer> minPQ = new MinPQ<>(4);
        minPQ.insert(4);
        minPQ.insert(1);
        minPQ.insert(7);

        System.out.println(minPQ.delMin()); //1

        minPQ.insert(12);
        minPQ.insert(25);
        minPQ.insert(3);

        System.out.println(minPQ.getMin());//3
        System.out.println(minPQ.delMin());//3
        System.out.println(minPQ.delMin());//4

        minPQ.insert(40);
        minPQ.insert(6);
        minPQ.insert(13);

        System.out.println(minPQ.getMin());//6
    }
}
