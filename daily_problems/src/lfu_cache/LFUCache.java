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
    DefaultDict freqMap = new DefaultDict();
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
        LinkedHashMap<Integer, Integer> deq = freqMap.get(nodeVal.freq);
        deq.remove(key);
        if (deq.isEmpty()) {
            freqMap.remove(nodeVal.freq);
            if (minFreq == nodeVal.freq)
                minFreq += 1;
        }
        Node newNodeVal = new Node(nodeVal.val, nodeVal.freq + 1);
        keyValue.put(key, newNodeVal);
        freqMap.get(nodeVal.freq + 1).put(key, key);
        return nodeVal.val;
    }

    public void set(int key, int value) {
        if (!keyValue.containsKey(key)) {
            //eviction code
            if (keyValue.size() == capacity) {
                LinkedHashMap<Integer, Integer> toEvictDequeue = freqMap.get(minFreq);
                Iterator<Integer> keysIt = toEvictDequeue.keySet().iterator();
                Integer toEvict = keysIt.next();
                keysIt.remove();
                if (toEvictDequeue.isEmpty()) {
                    freqMap.remove(minFreq);
                }
                keyValue.remove(toEvict);
            }
            Node valNode = new Node(value, 1);
            keyValue.put(key, valNode);
            freqMap.get(1).put(key, key);
            minFreq = 1;
        }
    }

    class DefaultDict extends HashMap<Integer, LinkedHashMap<Integer, Integer>> {

        @Override
        public LinkedHashMap<Integer, Integer> get(Object key) {
            LinkedHashMap<Integer, Integer> returnValue = super.get(key);
            if (returnValue == null) {
                returnValue = new LinkedHashMap();
                this.put((Integer) key, returnValue);
            }
            return returnValue;
        }
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
        obj.set(1, 1);
        obj.set(2, 2);
        obj.set(3, 3);
        obj.set(4, 4);//1-0,2-0,3-0,4-0
        obj.set(5, 5);//5-0,2-0,3-0,4-0
        System.out.print(obj.get(5)+", ");//5-1,2-0,3-0,4-0
        System.out.print(obj.get(1)+", ");//same, get=-1
        obj.get(2);//5-1,2-1,3-0,4-0
        obj.get(2);//5-1,2-2,3-0,4-0
        obj.get(4);//5-1,2-2,3-0,4-1
        obj.set(6, 6);//5-1,2-2,6-0,4-1
        System.out.print(obj.get(2)+", ");//5-1,2-3,6-0,4-1
        System.out.print(obj.get(3)+", ");//5-1,2-3,6-0,4-1 - same
        System.out.print(obj.get(5)+", ");//5-2,2-3,6-0,4-1
        obj.get(6);//5-2,2-3,6-1,4-1
        obj.get(6);//5-2,2-3,6-2,4-1
        obj.set(7, 7);//5-2,2-3,6-2,7-0
        System.out.print(obj.get(4)+", ");//5-2,2-3,6-2,7-0 - same
        System.out.print(obj.get(7)+", ");//5-2,2-3,6-2,7-1
        obj.get(7);//5-2,2-3,6-2,7-2
        obj.set(8, 8);
        System.out.print(obj.get(5)+", ");
        System.out.print(obj.get(2)+", ");
        System.out.print(obj.get(6)+", ");
        System.out.print(obj.get(7)+", ");
        System.out.print(obj.get(8)+", ");

        System.out.println("\n --- next test case -----");
        obj = new LFUCache(3);
        obj.set(1, 1);
        obj.set(2, 2);
        obj.set(3, 3);
        obj.get(2);//2-1
        obj.get(1);//1-1
        obj.get(1);//1-2
        obj.get(1);//1-3
        obj.get(2);//2-2
        obj.set(4, 4);
        obj.get(2);
        System.out.print(obj.get(1)+", ");
        System.out.print(obj.get(2)+", ");
        System.out.print(obj.get(3)+", ");
        System.out.print(obj.get(4)+", ");

    }
}

