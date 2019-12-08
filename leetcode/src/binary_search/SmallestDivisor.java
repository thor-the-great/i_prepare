package binary_search;

/**
 * 1283. Find the Smallest Divisor Given a Threshold
 * Medium
 *
 * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor and divide all
 * the array by it and sum the result of the division. Find the smallest divisor such that the result mentioned above
 * is less than or equal to threshold.
 *
 * Each result of division is rounded to the nearest integer greater than or equal to that element. (For example:
 * 7/3 = 3 and 10/2 = 5).
 *
 * It is guaranteed that there will be an answer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,5,9], threshold = 6
 * Output: 5
 * Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
 * If the divisor is 4 we can get a sum to 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
 * Example 2:
 *
 * Input: nums = [2,3,5,7,11], threshold = 11
 * Output: 3
 * Example 3:
 *
 * Input: nums = [19], threshold = 5
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 10^4
 * 1 <= nums[i] <= 10^6
 * nums.length <= threshold <= 10^6
 */
public class SmallestDivisor {

    /**
     * The function sum of the value of divisor is monotonically increasing, thus we can use binary search. Left if 1
     * (gives the highest sum, right is max allowed number, 1_000_000). Calculate m and sum for this m, then depending
     * on the value move left or right. Result will be in left cause we are looking for the smallest divisor
     * @param nums
     * @param threshold
     * @return
     */
    public int smallestDivisor(int[] nums, int threshold) {
        int l = 1, r = 1_000_000;

        while (l < r) {
            int m = l + (r - l )/2;
            int mRes = helper(nums, m);
            if (mRes > threshold) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    int helper(int[] nums, int div) {
        int cur = 0;
        for (int n : nums) {
            cur += (n /div);
            if (n % div > 0)
                ++cur;
        }
        return cur;
    }
}
