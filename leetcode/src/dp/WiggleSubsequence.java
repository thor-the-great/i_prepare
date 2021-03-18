package dp;

/**
 * 376. Wiggle Subsequence
Medium

Given an integer array nums, return the length of the longest wiggle sequence.

A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.

    For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) are alternately positive and negative.
    In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.

A subsequence is obtained by deleting some elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.

 

Example 1:

Input: nums = [1,7,4,9,2,5]
Output: 6
Explanation: The entire sequence is a wiggle sequence.

Example 2:

Input: nums = [1,17,5,10,13,15,10,5,16,8]
Output: 7
Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

Example 3:

Input: nums = [1,2,3,4,5,6,7,8,9]
Output: 2

 

Constraints:

    1 <= nums.length <= 1000
    0 <= nums[i] <= 1000

 

Follow up: Could you solve this in O(n) time?

https://leetcode.com/problems/wiggle-subsequence/
 */
public class WiggleSubsequence {

    /**
     * DP solution.
     * Each i-th element can be:
     * 1. gt i-1
     * 2. lt i-1
     * 3. eq i-1
     * 
     * keep in up[i] the longest subseq where current element is > than previous
     * and down[i] where current is < than previous
     * 
     * then in cases:
     * 1 - update up[i] as max (up[i - 1] and down[i - 1] + 1)
     * 2 - update down[i] as max (down[i - 1] and up[i - 1] + 1)
     * 3 - keep both values from i - 1
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        int N = nums.length;
        if (N < 2)
            return N;
        int[] up = new int[N], down = new int[N];
        up[0] = 1;
        down[0] = 1;
        for (int i = 1; i < N; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
            } else if (nums[i] < nums[i- 1]) {
                down[i] = Math.max(down[i - 1], up[i - 1] + 1);
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[N - 1], down[N - 1]);
    }
}
