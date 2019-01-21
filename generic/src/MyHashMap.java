import java.util.ArrayList;
import java.util.List;

class MyHashMap {

    double load = 0.75;
    int capacity = 32;
    int tsh;
    List<Entry>[] values;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        tsh = (int) (capacity * load);
        values = new ArrayList[capacity];
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int hash = hashKey(key);
        boolean found = false;
        List<Entry> list = values[hash];
        if (list != null && !list.isEmpty()) {
            for (Entry e : list) {
                if (e.key == key) {
                    e.val = value;
                    found = true;
                }
            }
        }
        if (!found) {
            if (tsh == 0) {
                rebuild();
                hash = hashKey(key);
                list = values[hash];
            }
            Entry e = new Entry(key, value);
            if (list != null) {
                list.add(e);
            } else {
                list = new ArrayList();
                list.add(e);
                values[hash] = list;
            }
            tsh--;
        }
    }

    private void rebuild() {
        capacity *= 2;
        tsh = (int) (capacity * load);
        List<Entry>[] tmp = new ArrayList[capacity];
        for (List<Entry> lE : values) {
            if (lE != null && !lE.isEmpty()) {
                for (Entry e : lE) {
                    int newHash = hashKey(e.key);
                    List<Entry> newLE = tmp[newHash];
                    if (newLE == null) {
                        newLE = new ArrayList();
                        tmp[newHash] = newLE;
                    }
                    newLE.add(e);
                    tsh--;
                }
            }
        }
        values = tmp;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int hash = hashKey(key);
        List<Entry> list = values[hash];
        if (list == null || list.isEmpty())
            return -1;
        for (Entry e : list) {
            if (e.key == key)
                return e.val;
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int hash = hashKey(key);
        List<Entry> list = values[hash];
        if (list == null || list.isEmpty())
            return;
        int idx = -1;
        for (int i = 0; i < list.size(); i++) {
            Entry e = list.get(i);
            if (e.key == key) {
                idx = i;
                break;
            }
        }
        if (idx != -1) {
            list.remove(idx);
            tsh++;
        }
    }

    int hashKey(int key) {
        return key % capacity;
    }

    public static void main(String[] args) {
        MyHashMap myMap = new MyHashMap();
        myMap.put(1, 1);
        myMap.put(2, 2);
        myMap.get(1);
        myMap.get(3);
    }
}

class Entry {
    int key;
    int val;

    Entry(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */




