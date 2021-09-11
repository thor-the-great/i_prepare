package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 446. Arithmetic Slices II - Subsequence
Hard

Given an integer array nums, return the number of all the arithmetic subsequences of nums.

A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

    For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
    For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.

A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.

    For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].

The test cases are generated so that the answer fits in 32-bit integer.

 

Example 1:

Input: nums = [2,4,6,8,10]
Output: 7
Explanation: All arithmetic subsequence slices are:
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]

Example 2:

Input: nums = [7,7,7,7,7]
Output: 16
Explanation: Any subsequence of this array is arithmetic.

 

Constraints:

    1  <= nums.length <= 1000
    -231 <= nums[i] <= 231 - 1

https://leetcode.com/problems/arithmetic-slices-ii-subsequence/
 */
public class ArithmeticSlicesII {
    
    /**
     * dp, at dp[j] is all sequences from 0..j, and element is map where key == difference and 
     * value is length
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int N = nums.length, res = 0;
        Map<Integer, Integer>[] dp = new HashMap[N];
        for (int i = 0; i < N; i++) {
            dp[i] = new HashMap();
            for (int j = 0; j < i; j++) {
                long difL = (long) nums[i] - nums[j];
                if (difL <= Integer.MIN_VALUE || difL > Integer.MAX_VALUE) {
                    continue;
                }
                
                int d = (int)difL;
                int c1 = 0;
                if (dp[i].containsKey(d)) {
                    c1 = dp[i].get(d);
                }
                int c2 = 0;
                if (dp[j].containsKey(d)) {
                    c2 = dp[j].get(d);
                }
                res += c2;
                dp[i].put(d, c1 + c2 + 1);
            }
        }
        
        return res;
    }
}
