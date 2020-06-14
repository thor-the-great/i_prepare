package design;

import java.util.*;

/**
 * Insert Delete GetRandom O(1)
 *
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each
 * element must have the same probability of being returned.
 * Example:
 */
public class RandomizedSet {

  /**
   * Idea - use hashmpa and arraylist. add elements to arraylist, and map has values to index
   * mapping. When remove - check if the element is last in the list, if it's not - change current one
   * with last one and update the map.
   */
  List<Integer> list;
  Map<Integer, Integer> map;
  Random rand;

  /** Initialize your data structure here. */
  public RandomizedSet() {
    map = new HashMap();
    list = new ArrayList();
    rand = new Random();
  }

  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
    if (map.containsKey(val))
      return false;
    list.add(val);
    map.put(val, list.size() - 1);
    return true;
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
    if (!map.containsKey(val))
      return false;
    int idx = map.get(val);
    map.remove(val);
    if (idx != list.size() - 1) {
      int lastVal = list.get(list.size() - 1);
      map.put(lastVal, idx);
      list.set(idx, lastVal);
    }
    list.remove(list.size() - 1);
    return true;
  }

  /** Get a random element from the set. */
  public int getRandom() {
    return list.get(rand.nextInt(list.size()));
  }
}

