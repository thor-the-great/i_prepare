package design;

import java.util.ArrayList;
import java.util.List;

/**
 * Design a HashMap without using any built-in hash table libraries.
 *
 * To be specific, your design should include these functions:
 *
 *     put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
 *     get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 *     remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
 *
 *
 * Example:
 *
 * MyHashMap hashMap = new MyHashMap();
 * hashMap.put(1, 1);
 * hashMap.put(2, 2);
 * hashMap.get(1);            // returns 1
 * hashMap.get(3);            // returns -1 (not found)
 * hashMap.put(2, 1);          // update the existing value
 * hashMap.get(2);            // returns 1
 * hashMap.remove(2);          // remove the mapping for 2
 * hashMap.get(2);            // returns -1 (not found)
 *
 *
 * Note:
 *
 *     All keys and values will be in the range of [0, 1000000].
 *     The number of operations will be in the range of [1, 10000].
 *     Please do not use the built-in HashMap library.
 *
 */
public class MyHashMap {

    /** Initialize your data structure here. */
    int capacity = 16;
    double loadfactor = 0.75;
    int treshold = (int) (capacity*loadfactor);
    List<Entry>[] values;

    public MyHashMap() {
        values = new ArrayList[capacity];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        if (treshold == 0) {
            rebuild(true);
        }
        int hash = getHash(key);
        if (values[hash] == null) {
            values[hash] = new ArrayList();
        }
        for (int i = values[hash].size() - 1; i >= 0; i--) {
            Entry entry = values[hash].get(i);
            if (entry.key == key) {
                entry.val = value;
                return;
            }
        }
        values[hash].add(new Entry(key, value));
        --treshold;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hash = getHash(key);
        if (values[hash] == null || values[hash].isEmpty())
            return -1;
        for (Entry entry : values[hash]) {
            if (entry.key == key) {
                return entry.val;
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash = getHash(key);
        if (values[hash] == null || values[hash].isEmpty())
            return;
        for (int i = values[hash].size() - 1; i >= 0; i--) {
            Entry entry = values[hash].get(i);
            if (entry.key == key) {
                values[hash].remove(entry);
                ++treshold;
                if (treshold > (capacity*loadfactor)) {
                    rebuild(false);
                }
            }
        }
    }

    void rebuild(boolean increase) {
        if (increase) {
            capacity *=2;
        } else {
            capacity /= 2;
        }
        int treshold = (int) (capacity*loadfactor);
        List<Entry>[] tmp = new ArrayList[capacity];
        for (List<Entry> sameHashKeys : values) {
            if (sameHashKeys != null && !sameHashKeys.isEmpty()) {
                for (Entry entry : sameHashKeys) {
                    int newHash = getHash(entry.key);
                    if (tmp[newHash] == null) {
                        tmp[newHash] = new ArrayList();
                    }
                    tmp[newHash].add(entry);
                    --treshold;
                }
            }
        }
        values = tmp;
    }

    int getHash(int key) {
        return key % capacity;
    }

    class Entry {
        int key;
        int val;

        Entry(int k, int v) {
            key = k;
            val = v;
        }
    }
}
