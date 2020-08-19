package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target
 * 
    1546. Maximum Number of Non-Overlapping Subarrays With Sum Equals Target
    Difficulty:Medium
    Given an array nums and an integer target.

    Return the maximum number of non-empty non-overlapping subarrays such that the sum of values in each subarray is equal to target.

    Example 1:

    Input: nums = [1,1,1,1,1], target = 2
    Output: 2
    Explanation: There are 2 non-overlapping subarrays [1,1,1,1,1] with sum equals to target(2).
    Example 2:

    Input: nums = [-1,3,5,1,4,2,-9], target = 6
    Output: 2
    Explanation: There are 3 subarrays with sum equal to 6.
    ([5,1], [4,2], [3,5,1,4,2,-9]) but only the first 2 are non-overlapping.
    Example 3:

    Input: nums = [-2,6,6,3,5,4,1,2,8], target = 10
    Output: 3
    Example 4:

    Input: nums = [0,0,0], target = 0
    Output: 3
    

    Constraints:

    1 <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4
    0 <= target <= 10^6
 */
public class MaximumNumberOfNonOverlappingSubarrays {

    /**
     * Think of array of prefix sums, we build it as we go. Similar to two elements of a sum in array. In map
     * keep sum and number of subarrays we met so far (overall not for this pair). Keep running res variable 
     * compare value_from_map + 1 with the running res, keep the maxim. 
     * 
     * @param nums
     * @param target
     * @return
     */
    public int maxNonOverlapping(int[] nums, int target) {
        Map<Integer, Integer> numOfSubarraysByPrefSums = new HashMap();
        numOfSubarraysByPrefSums.put(0, 0);
        int sum = 0;
        int numOfSubarrays = 0;
        for (int n : nums) {
            sum += n;
            if (numOfSubarraysByPrefSums.containsKey(sum - target)) {
                numOfSubarrays = Math.max(numOfSubarraysByPrefSums.get(sum - target) + 1, numOfSubarrays);
            }
            numOfSubarraysByPrefSums.put(sum, numOfSubarrays);
        }
        return numOfSubarrays;
    }
}