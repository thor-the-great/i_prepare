import path.linkedin.MaxPointsOnLine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class  MyHashMap <K, V> {
    int size = 0;
    //int cap = 0;
    int load = 0;
    List<Entity>[] values;
    double loadFactor;

    MyHashMap () {
        this(8);
    }

    MyHashMap (int capacity) {
        values = new LinkedList[capacity];
        load = 0;
        loadFactor = 0.75;
    }

    void put(K key, V val) {

        if (((double)load/values.length) >= loadFactor) {
            rehash();
        }

        int idx = getIdx(key, values.length);
        if (values[idx] == null) {
            values[idx] = new LinkedList<>();
            values[idx].add(new Entity(key, val));
            load++;
            size++;
        } else {
            List<Entity> vals = values[idx];
            boolean found = false;
            for (int i =0; i < vals.size(); i++) {
                Entity e = vals.get(i);
                if (e.key.equals(key)) {
                    e.val = val;
                    found = true;
                    break;
                }
            }
            if (!found) {
                vals.add(new Entity(key, val));
                size++;
            }
        }
    }

    private int getIdx(K key, int mod) {
        int hash = key.hashCode();
        return hash % mod;
    }

    private void rehash() {
        int newCap = values.length * 2;
        List<Entity>[] tmp = new LinkedList[newCap];
        for (int i =0; i < values.length; i++) {
            if (values[i] == null)
                continue;
            List<Entity> entList = values[i];
            for (Entity e : entList) {
                int newIdx = getIdx(e.key, newCap);
                if (tmp[newIdx] == null) {
                    tmp[newIdx] = new LinkedList<>();
                }
                tmp[newIdx].add(e);
            }
        }
        values = tmp;
    }

    V get(K key) {
        int idx = getIdx(key, values.length);
        if (values[idx] == null)
            return null;
        List<Entity> vals = values[idx];
        for (int i =0; i < vals.size(); i++) {
            Entity e = vals.get(i);
            if (e.key.equals(key)) {
                return e.val;
            }
        }
        return null;
    }

    int size() {
        return size;
    }

    class Entity  {
        K key;
        V val;
        Entity (K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> m = new MyHashMap();
        IntStream.range(0, 16).forEach(i->m.put(i, Integer.toString(i)));
        System.out.println(m.size());
        IntStream.range(0, 20).forEach(i->System.out.print(m.get(i) + ", "));
        System.out.println(m.size());
    }
}
