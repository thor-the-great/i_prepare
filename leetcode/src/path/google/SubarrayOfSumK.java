package path.google;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K
 * Medium
 *
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum
 * equals to k.
 *
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * Note:
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
public class SubarrayOfSumK {
    /**
     * Idea: Diff between prefix sum i and j is sum from i to j. So we count running sum and save it on every step. Then
     * on next step for next sum we check if sum2 - K is there. Save prefix sums in hashmap
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap();
        m.put(0, 1);
        //count of subarrays
        int c = 0;
        //running sum
        int s = 0;
        for (int num : nums) {
            s += num;
            //sum that gives us k
            int s2 = s - k;
            if (m.containsKey(s2)) {
                c += m.get(s2);
            }
            if (m.containsKey(s))
                m.put(s, m.get(s) + 1);
            else
                m.put(s, 1);
        }
        return c;
    }
}
