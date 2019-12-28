package arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 5292. Divide Array in Sets of K Consecutive Numbers
 * Medium
 *
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets
 * of k consecutive numbers
 * Return True if its possible otherwise return False.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,3,4,4,5,6], k = 4
 * Output: true
 * Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
 * Example 2:
 *
 * Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
 * Output: true
 * Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
 * Example 3:
 *
 * Input: nums = [3,3,2,2,1,1], k = 3
 * Output: true
 * Example 4:
 *
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 * Explanation: Each array should be divided in subarrays of size 3.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= nums.length
 */
public class DivideArrayInSetsOfKConsecutiveElements {

    /**
     * Create sorted set of numbers and map of count of each number. Starting from the lowest number from the set
     * check each number from num to num + k if the count of this number is greater 0, if all numbers are there
     * increment count of groups. Failure to do so at least once fail the whole method
     * @param nums
     * @param k
     * @return
     */
    public boolean isPossibleDivide(int[] nums, int k) {
        int N = nums.length;

        if (N % k != 0)
            return false;

        Map<Integer, Integer> m = new HashMap();
        TreeSet<Integer> sorted = new TreeSet();
        for (int n : nums) {
            if (m.containsKey(n))
                m.put(n, m.get(n) + 1);
            else
                m.put(n, 1);
            sorted.add(n);
        }
        int ser = 0, expected = N/k;
        while(ser < expected && !sorted.isEmpty()) {
            int start = sorted.first();
            for (int i = start; i < start + k; i++) {
                if (!m.containsKey(i))
                    return false;
                int count = m.get(i);
                if (count == 1) {
                    m.remove(i);
                    sorted.remove(i);
                } else {
                    m.put(i, count - 1);
                }
            }
            ++ser;
        }
        return N/k == ser;
    }
}
