package random_problems;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 981. Time Based Key-Value Store
 * Medium
 *
 * Create a timebased key-value store class TimeMap, that supports two operations.
 *
 * 1. set(string key, string value, int timestamp)
 *
 * Stores the key and value, along with the given timestamp.
 * 2. get(string key, int timestamp)
 *
 * Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the one with the largest timestamp_prev.
 * If there are no values, it returns the empty string ("").
 *
 *
 * Example 1:
 *
 * Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],
 * ["foo","bar2",4],["foo",4],["foo",5]]
 * Output: [null,null,"bar","bar",null,"bar2","bar2"]
 * Explanation:
 * TimeMap kv;
 * kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
 * kv.get("foo", 1);  // output "bar"
 * kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2,
 * then the only value is at timestamp 1 ie "bar"
 * kv.set("foo", "bar2", 4);
 * kv.get("foo", 4); // output "bar2"
 * kv.get("foo", 5); //output "bar2"
 *
 * Example 2:
 *
 * Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],
 * ["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 * Output: [null,null,null,"","high","high","low","low"]
 *
 *
 * Note:
 *
 * All key/value strings are lowercase.
 * All key/value strings have length in the range [1, 100]
 * The timestamps for all TimeMap.set operations are strictly increasing.
 * 1 <= timestamp <= 10^7
 * TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.
 */
public class TimeMap {

    Map<String, TreeSet<Value>> keyTS;

    Comparator<Value> comp = new Comparator<Value>() {
        public int compare(Value v1, Value v2) {
            return Integer.compare(v1.ts, v2.ts);
        }
    };

    /**
     * Initialize your data structure here.
     */
    public TimeMap() {
        keyTS = new HashMap();
    }

    public void set(String key, String value, int timestamp) {
        if (!keyTS.containsKey(key)) {
            keyTS.put(key, new TreeSet(comp));
        }
        Value val = new Value(timestamp, value);
        keyTS.get(key).add(val);
    }

    public String get(String key, int timestamp) {
        if (!keyTS.containsKey(key))
            return "";

        TreeSet<Value> values = keyTS.get(key);
        Value v = new Value(timestamp, "");
        Value searchEl = values.floor(v);

        if (searchEl == null)
            return "";
        else
            return searchEl.value;
    }
}

class Value {
    int ts;
    String value;

    Value(int ts, String s) {
        this.ts = ts;
        this.value = s;
    }
}
