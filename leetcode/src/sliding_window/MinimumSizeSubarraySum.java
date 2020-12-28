package sliding_window;

/**
 * 209. Minimum Size Subarray Sum
Medium

3146

129

Add to List

Share
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 

https://leetcode.com/problems/minimum-size-subarray-sum/
 */
public class MinimumSizeSubarraySum {
    /**Sliding window, add each element to sum (move right pointer), then check if it's >= s and move left, 
     * recheck for min every time */
    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0, l = 0, res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s) {
                res = Math.min(res, i - l + 1);
                sum-=nums[l];
                ++l;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }    
}
