package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2121. Intervals Between Identical Elements
Medium

You are given a 0-indexed array of n integers arr.

The interval between two elements in arr is defined as the absolute difference between their indices. More formally, the interval between arr[i] and arr[j] is |i - j|.

Return an array intervals of length n where intervals[i] is the sum of intervals between arr[i] and each element in arr with the same value as arr[i].

Note: |x| is the absolute value of x.

 

Example 1:

Input: arr = [2,1,3,1,2,3,3]
Output: [4,2,7,2,4,4,5]
Explanation:
- Index 0: Another 2 is found at index 4. |0 - 4| = 4
- Index 1: Another 1 is found at index 3. |1 - 3| = 2
- Index 2: Two more 3s are found at indices 5 and 6. |2 - 5| + |2 - 6| = 7
- Index 3: Another 1 is found at index 1. |3 - 1| = 2
- Index 4: Another 2 is found at index 0. |4 - 0| = 4
- Index 5: Two more 3s are found at indices 2 and 6. |5 - 2| + |5 - 6| = 4
- Index 6: Two more 3s are found at indices 2 and 5. |6 - 2| + |6 - 5| = 5
Example 2:

Input: arr = [10,5,10,10]
Output: [5,0,3,4]
Explanation:
- Index 0: Two more 10s are found at indices 2 and 3. |0 - 2| + |0 - 3| = 5
- Index 1: There is only one 5 in the array, so its sum of intervals to identical elements is 0.
- Index 2: Two more 10s are found at indices 0 and 3. |2 - 0| + |2 - 3| = 3
- Index 3: Two more 10s are found at indices 0 and 2. |3 - 0| + |3 - 2| = 4
 

Constraints:

n == arr.length
1 <= n <= 105
1 <= arr[i] <= 105

https://leetcode.com/problems/intervals-between-identical-elements/
 */
public class IntervalsBetweenIdenticalElements {

    /**
     * Idea:
     * - group indexes of each element in map by value, list indexes as list
     * - for every one list need to count difference, it's possible to compute it knowing the difference for first element and then coputing for every next element.
     * for each element we 1. increase distance if look from left to element and 2. decrease if look element to right.
     * Increase will be (distance between current and previous) * (number of elements from 0 to current)
     * Decrease will be (distance between current and previous) * (number of elements from current to last)
     */
    public long[] getDistances(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int N = arr.length;
        long[] res = new long[N];
        
        for (int i = 0; i < N; i++) {
            int num = arr[i];
            if (!map.containsKey(num)) {
                List<Integer> list = new ArrayList<>();
                map.put(num, list);
            } 
            map.get(num).add(i);
        }
        for (List<Integer> list : map.values()) {
            if (list.size() == 1) {
                continue;
            }
            long sum = 0;
            //construct sum for the first element
            for (int i = 1; i < list.size(); i++) {
                sum += list.get(i) - list.get(0);
            }
            res[list.get(0)] = sum;
    
            for (int pos = 1; pos < list.size(); pos++) {
                //count how many elements are before and after current element
                int prev = pos, next = list.size() - prev - 1;
                //this is delta of shifting zero point to current element
                int diff = list.get(pos) - list.get(pos - 1);
                sum += (prev - next - 1)*diff;
                res[list.get(pos)] = sum;
            }
        }
        return res;
    }
}
