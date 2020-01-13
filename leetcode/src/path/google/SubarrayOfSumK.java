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
        int res = 0, cur = 0;
        Map<Integer, Integer> m = new HashMap();
        m.put(0, 1);
        for (int n : nums) {
            cur += n;
            res+= m.getOrDefault(cur - k, 0);
            m.put(cur, m.getOrDefault(cur, 0) + 1);
        }
        return res;
    }
}
