package lfu_cache;

import java.util.*;

public class LFUCache {
    //map stores key and Node(value, current_frequency_of_usage). After each get current_frequency_of_usage will be updated
    Map<Integer, Node> keyValue = new HashMap();
    //oh, this is interesting. This is custom extension of HashMap<Integer, LinkedHashMap<Integer, Integer>>
    //key Integer - frequency of usage
    //LinkedHashMap<Integer, Integer> - in fact - list of all values that were used with this frequency. But the key idea
    //is to have this LinkedHashMap, so it keeps insertion order automatically, so keySet() will return keys in "insertion
    //order", meaning first will be one inserted first etc. This is great for eviction logic - we just take first
    //element in iteration and remove it. Both operations are in O(1)
    Map<Integer, LinkedHashMap<Integer, Integer>> freqMap = new HashMap();
    int minFreq = 0;
    int capacity;

    public LFUCache(int c) {
        capacity = c;
        keyValue.clear();
        freqMap.clear();
        minFreq = 0;
    }

    public int get(int key) {
        //check if key is in cache
        if (!keyValue.containsKey(key)) {
            return -1;
        }
        Node nodeVal = keyValue.get(key);
        Map<Integer, Integer> deq = getKey(nodeVal.freq);
        deq.remove(key);
        if (deq.isEmpty()) {
            freqMap.remove(nodeVal.freq);
            if (minFreq == nodeVal.freq)
                minFreq += 1;
        }
        nodeVal.freq += 1;
        getKey(nodeVal.freq).put(key, key);
        return nodeVal.val;
    }

    public void put(int key, int value) {
        if (capacity == 0)
            return;
        if (!keyValue.containsKey(key)) {
            //eviction code
            if (keyValue.size() == capacity) {
                Map<Integer, Integer> toEvictDequeue = getKey(minFreq);
                Integer toEvict = toEvictDequeue.keySet().iterator().next();
                toEvictDequeue.remove(toEvict);
                if (toEvictDequeue.isEmpty()) {
                    freqMap.remove(minFreq);
                }
                keyValue.remove(toEvict);
            }
            Node valNode = new Node(value, 1);
            keyValue.put(key, valNode);
            getKey(1).put(key, key);
            minFreq = 1;
        } else {
            Node nodeVal = keyValue.get(key);
            Map<Integer, Integer> deq = getKey(nodeVal.freq);
            deq.remove(key);
            if (deq.isEmpty()) {
                freqMap.remove(nodeVal.freq);
                if (minFreq == nodeVal.freq)
                    minFreq += 1;
            }
            nodeVal.freq += 1;
            nodeVal.val = value;
            getKey(nodeVal.freq).put(key, key);
        }
    }

    private Map<Integer, Integer> getKey(int key) {
        freqMap.putIfAbsent(key, new LinkedHashMap());
        return freqMap.get(key);
    }

    class Node {
        int val;
        int freq;

        Node(int val, int freq) {
            this.val = val;
            this.freq = freq;
        }
    }

    public static void main(String[] args) {
        LFUCache obj = new LFUCache(4);
        obj.put(1, 1);
        obj.put(2, 2);
        obj.put(3, 3);
        obj.put(4, 4);//1-0,2-0,3-0,4-0
        obj.put(5, 5);//5-0,2-0,3-0,4-0
        System.out.print(obj.get(5)+", ");//5-1,2-0,3-0,4-0
        System.out.print(obj.get(1)+", ");//same, get=-1
        obj.get(2);//5-1,2-1,3-0,4-0
        obj.get(2);//5-1,2-2,3-0,4-0
        obj.get(4);//5-1,2-2,3-0,4-1
        obj.put(6, 6);//5-1,2-2,6-0,4-1
        System.out.print(obj.get(2)+", ");//5-1,2-3,6-0,4-1
        System.out.print(obj.get(3)+", ");//5-1,2-3,6-0,4-1 - same
        System.out.print(obj.get(5)+", ");//5-2,2-3,6-0,4-1
        obj.get(6);//5-2,2-3,6-1,4-1
        obj.get(6);//5-2,2-3,6-2,4-1
        obj.put(7, 7);//5-2,2-3,6-2,7-0
        System.out.print(obj.get(4)+", ");//5-2,2-3,6-2,7-0 - same
        System.out.print(obj.get(7)+", ");//5-2,2-3,6-2,7-1
        obj.get(7);//5-2,2-3,6-2,7-2
        obj.put(8, 8);
        System.out.print(obj.get(5)+", ");
        System.out.print(obj.get(2)+", ");
        System.out.print(obj.get(6)+", ");
        System.out.print(obj.get(7)+", ");
        System.out.print(obj.get(8)+", ");

        System.out.println("\n --- next test case -----");
        obj = new LFUCache(3);
        obj.put(1, 1);
        obj.put(2, 2);
        obj.put(3, 3);
        obj.get(2);//2-1
        obj.get(1);//1-1
        obj.get(1);//1-2
        obj.get(1);//1-3
        obj.get(2);//2-2
        obj.put(4, 4);
        obj.get(2);
        System.out.print(obj.get(1)+", ");
        System.out.print(obj.get(2)+", ");
        System.out.print(obj.get(3)+", ");
        System.out.print(obj.get(4)+", ");

    }
}

