package timesorted_map;

import java.util.*;

/**
 * This problem was asked by Stripe.
 *
 * Write a map implementation with a get function that lets you retrieve the value of a key at a particular time.
 *
 * It should contain the following methods:
 *
 * set(key, value, time): sets key to value for t = time.
 * get(key, time): gets the key at t = time.
 * The map should work like this. If we set a key at a particular time, it will maintain that value forever or until
 * it gets set at a later time. In other words, when we get a key at a time, it should return the value that was set
 * for that key set at the most recent time.
 *
 * Consider the following examples:
 *
 * d.set(1, 1, 0) # set key 1 to value 1 at time 0
 * d.set(1, 2, 2) # set key 1 to value 2 at time 2
 * d.get(1, 1) # get key 1 at time 1 should be 1
 * d.get(1, 3) # get key 1 at time 3 should be 2
 * d.set(1, 1, 5) # set key 1 to value 1 at time 5
 * d.get(1, 0) # get key 1 at time 0 should be null
 * d.get(1, 10) # get key 1 at time 10 should be 1
 * d.set(1, 1, 0) # set key 1 to value 1 at time 0
 * d.set(1, 2, 0) # set key 1 to value 2 at time 0
 * d.get(1, 0) # get key 1 at time 0 should be 2
 *
 * @param <K>
 * @param <V>
 */
public class TimeMap <K, V> {
    //idea is to have a map of key and custom objects
    //this custom object based on arrays, search of time key is based on binary search. BS in java
    //has this property to return a position that element can be inserted.
    Map<K, TimeSorted> map;

    public TimeMap() {
        map = new HashMap();
    }

    public void set(K key, V value, int time) {
        TimeSorted<V> ts;
        if (!map.containsKey(key)) {
            ts = new TimeSorted();
            map.put(key, ts);
        } else {
            ts = map.get(key);
        }
        ts.set(time, value);
    }

    public V get(K key, int time) {
        if (!map.containsKey(key)) return null;

        TimeSorted<V> ts = map.get(key);
        return ts.get(time);
    }


    public static void main(String[] args) {
        TimeMap<Integer, Integer> obj = new TimeMap();
        obj.set(1, 1, 0);
        obj.set(1, 2, 2);
        System.out.println(obj.get(1,1));
        System.out.println(obj.get(1,3));
        obj.set(1, 1, 5);
        System.out.println(obj.get(1,0));
        System.out.println(obj.get(1,10));
        obj.set(1, 1, 0);
        obj.set(1, 2, 0);
        System.out.println(obj.get(1,0));
    }
}

class TimeSorted <V> {
    List<Integer> times;
    List<V> values;

    TimeSorted() {
        times = new ArrayList<>();
        values = new ArrayList<>();
    }

    V get(int key) {
        if (values.size() == 0)
            return null;
        int pos = Collections.binarySearch(times, key);
        if (pos >= 0) {
            return values.get(pos);
        } else {
            int newPos = -1 - pos;
            if (newPos == 0) return null;
            if (newPos < values.size())
                return values.get(newPos - 1);
            else
                return values.get(values.size() - 1);
        }
    }

    void set(int key, V value) {
        int pos = Collections.binarySearch(times, key);
        //key is already in the collection
        if (pos >= 0) {
            values.set(pos, value);
        } else {
            //if not - insert elements in both arrays in the same position
            int newPos = -1 - pos;
            times.add(newPos, key);
            values.add(newPos, value);
        }
    }
}
