package random_problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 170. Two Sum III - Data structure design
 *
 *
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 *
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 *
 * Example 1:
 *
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 * Example 2:
 *
 * add(3); add(1); add(2);
 * find(3) -> true
 * find(6) -> false
 *
 */
public class TwoSumDS {

    /**
     * Idea is to use sorted array and then search quickly using 2 pointers technique.
     * Add element in O(1)
     * Sort elements on find, also keep flag and re-use sorted array if no elements were added
     */
    List<Integer> arr = new ArrayList();
    boolean sorted = false;

    public TwoSumDS() {
        arr.clear();
        sorted = false;
    }

    /**
     * Add the number to an internal data structure..
     */
    public void add(int number) {
        arr.add(number);
        sorted = false;
    }

    /**
     * Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        if (!sorted) {
            Collections.sort(arr);
            sorted = true;
        }
        int l = 0, r = arr.size() - 1;
        while (l < r) {
            int s = arr.get(l) + arr.get(r);
            if (s == value) return true;
            if (s > value)
                r--;
            else
                l++;
        }
        return false;
    }
}
