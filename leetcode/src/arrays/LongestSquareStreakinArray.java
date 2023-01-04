package arrays;

import java.util.Arrays;

/**
2501. Longest Square Streak in an Array
Medium

You are given an integer array nums. A subsequence of nums is called a square streak if:

The length of the subsequence is at least 2, and
after sorting the subsequence, each element (except the first element) is the square of the previous number.
Return the length of the longest square streak in nums, or return -1 if there is no square streak.

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

 

Example 1:

Input: nums = [4,3,6,16,8,2]
Output: 3
Explanation: Choose the subsequence [4,16,2]. After sorting it, it becomes [2,4,16].
- 4 = 2 * 2.
- 16 = 4 * 4.
Therefore, [4,16,2] is a square streak.
It can be shown that every subsequence of length 4 is not a square streak.
Example 2:

Input: nums = [2,3,5,6,7]
Output: -1
Explanation: There is no square streak in nums so return -1.
 

Constraints:

2 <= nums.length <= 105
2 <= nums[i] <= 105
 */
public class LongestSquareStreakinArray {

    /**
     * Idea - if we sort, we can check sequences as we go from lowest to greatest num. Keep numbers in set
     * or boolean array, remove when number is met. Catch - as max is 100_000 the largest number is 316 (square root of 10^5).
     * Possible optimization - use two arrays, don't sort
     */
    public int longestSquareStreak(int[] nums) {
        Arrays.sort(nums);
        boolean[] set = new boolean[100_001];
        for (int n : nums) {
            set[n] = true;
        }
        
        int res = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (set[nums[i]]) {
                int curNum = nums[i];
                int curLen = 0;
                while (set[curNum]) {
                    set[curNum] = false;
                    curLen++;
                    if (curNum <= 316) {
                        curNum = curNum * curNum;
                    } else {
                        break;
                    }
                }
                res = Math.max(res, curLen);
            }    
        }
        
        return res >= 2 ? res : -1;
    }
}