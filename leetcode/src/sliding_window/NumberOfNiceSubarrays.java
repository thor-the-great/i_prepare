package sliding_window;

/**
 * 1248. Count Number of Nice Subarrays
 * Medium
 *
 * Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.
 *
 * Return the number of nice sub-arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 * Example 2:
 *
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There is no odd numbers in the array.
 * Example 3:
 *
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 */
public class NumberOfNiceSubarrays {

    /**
     * exactly K is at_most(K) - at_most(K - 1). Each at_most we count using sliding window (two pointers)
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {

        return atMost(nums, k) - atMost(nums, k - 1);
    }

    int atMost(int[] A, int k) {
        int res = 0, i = 0, n = A.length;
        for (int j = 0; j < n; j++) {
            if (A[j] % 2 > 0)
                k--;

            while (k < 0) {
                if (A[i++] % 2> 0)
                    k++;
            }

            res += j - i + 1;
        }
        return res;
    }
}
