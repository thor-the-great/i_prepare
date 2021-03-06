package path.linkedin;

import java.util.*;

/**
 * 380. Insert Delete GetRandom O(1)
 * Medium
 *
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of
 * being returned.
 * Example:
 *
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 *
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 *
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 *
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 *
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 *
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 *
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 *
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 */
public class RandomizedSet {

    Random rand = new Random();
    Map<Integer, Integer> m;
    List<Integer> keys;
    int lastIdx;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        m = new HashMap();
        keys = new ArrayList();
        lastIdx = -1;
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (m.containsKey(val))
            return false;
        else {
            keys.add(val);
            lastIdx++;
            m.put(val, lastIdx);
            return true;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!m.containsKey(val))
            return false;
        else {
            int idx = m.get(val);
            //this is critical - need to replace last element with selected one, otherwise randomize index selection
            //will not work
            if (idx < lastIdx) {
                int last = keys.get(lastIdx);
                keys.set(idx, last);
                m.put(last, idx);
            }
            keys.remove(lastIdx);
            m.remove(val);
            lastIdx--;
            return true;
        }
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int nextIdx = rand.nextInt(lastIdx + 1);
        return keys.get(nextIdx);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */