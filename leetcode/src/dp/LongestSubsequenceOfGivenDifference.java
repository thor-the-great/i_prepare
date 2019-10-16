package dp;

/**
 * 1218. Longest Arithmetic Subsequence of Given Difference
 * Medium
 *
 * Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is
 * an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4], difference = 1
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [1,2,3,4].
 * Example 2:
 *
 * Input: arr = [1,3,5,7], difference = 1
 * Output: 1
 * Explanation: The longest arithmetic subsequence is any single element.
 * Example 3:
 *
 * Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [7,5,3,1].
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i], difference <= 10^4
 */
public class LongestSubsequenceOfGivenDifference {

    /**
     * Using dynamic programming approach. Let's do the array of values for all elements from array, we can do this
     * because we know the boundaries of our values (-10_000 - 10_000). The index became a value of the element
     * (plus offset so we can start from 0) and the value is longest sequence we've seen so far that ends at this
     * element. That basically means dp[i] = max(dp[i], dp[i - k] + 1).
     *
     * We can fill the array as we go from 0 to last element, this gives us increasing sub-sequence as it is in
     * original array.
     *
     * One catch is to check for values to be inside or min..max boundaries, otherwise we'll have indexOutOfBounds
     * exception. Need to cheeck both min and max because difference can be negative. In such case we can return 0
     * as previous sequence.
     *
     * O(n) time - linear scan of array. O(1) space - use fixed-length array for DP table.
     * @param arr
     * @param difference
     * @return
     */
    public int longestSubsequence(int[] arr, int difference) {
        int[] dp = new int[20_001];
        int res = 0;
        for (int n : arr) {
            //current value plus offset
            int i = n + 10_000;
            //value - diff - possible previous value in sequence
            int idx = i - difference;
            //length of sequence ended with prev value
            int p = (idx >= 0 && idx <= 20_000) ? dp[idx] : 0;
            //cehck if newly calculated value is greater than we have met before for this number
            dp[i] = Math.max(dp[i], p + 1);
            //save global longest sequence length
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
