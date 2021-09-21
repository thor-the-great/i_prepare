package sliding_window;

/**
 * 485. Max Consecutive Ones
Easy

Given a binary array nums, return the maximum number of consecutive 1's in the array.

 

Example 1:

Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
Example 2:

Input: nums = [1,0,1,1,0,1]
Output: 2
 

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.

https://leetcode.com/problems/max-consecutive-ones/
 */
public class MaxConsecutiveOnes {
    /**
     * two pointers, left always st to the last 0 before series of ones
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = nums[0] == 1 ? 1 : 0, l = nums[0] == 1 ? -1 : 0;
        for (int r = 1; r < nums.length; r++) {
            if (nums[r] == 0) {
                l = r;
            } else {
                max = Math.max(max, r - l);
            }
        }
        
        return max;
    }
}
