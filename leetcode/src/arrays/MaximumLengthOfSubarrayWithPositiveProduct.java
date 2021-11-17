package arrays;

/**
 * 1567. Maximum Length of Subarray With Positive Product
 * Medium
 *
 * Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.
 *
 * A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
 *
 * Return the maximum length of a subarray with positive product.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-2,-3,4]
 * Output: 4
 * Explanation: The array nums already has a positive product of 24.
 * Example 2:
 *
 * Input: nums = [0,1,-2,-3,-4]
 * Output: 3
 * Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
 * Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
 * Example 3:
 *
 * Input: nums = [-1,-2,-3,0,1]
 * Output: 2
 * Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].
 * Example 4:
 *
 * Input: nums = [-1,2]
 * Output: 1
 * Example 5:
 *
 * Input: nums = [1,2,3,5,-6,4,0,10]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class MaximumLengthOfSubarrayWithPositiveProduct {

  /**
   * Catch 1 - length will be longer if number of elements is higher from one zero to next zero. So we are interested
   * in positions of zeroes.
   * Catch 2 - need to count all negative numbers from zero to zero. IF numbers of negatives is even (including 0) - just
   * take the whole sequence length. If it's odd there are two cases - either it's from first negative to the right 0
   * or from left 0 to the last negative.
   * To track positions of negatives use two variables.
   *
   * O(n) time
   * O(1) space
   * @param nums
   * @return
   */
  public int getMaxLen(int[] nums) {
    int max = 0;
    int zeroPos = -1, firstNeg = -1, numOfNegs = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] < 0) {
        if (firstNeg == -1)
          firstNeg = i;
        ++numOfNegs;
      }
      if (nums[i] == 0) {
        firstNeg = -1;
        numOfNegs = 0;
        zeroPos = i;
      } else {
        max = Math.max(max, (numOfNegs % 2 == 0) ? i - zeroPos : i - firstNeg);
      }
    }
    return max;
  }

  /**
   * Simpler idea - count num of nums that gives positive and negative product
   * - 0 - we rest both pos and neg
   * - positive - increment pos and increment neg if it's not 0
   * - negative - set neg = positive + 1, pos = neg + 1 (if neg is > 0)
   * 
   * on each step save result as max of (current result, pos)
   */
  public int getMaxLenPositiveNegative(int[] nums) {
    int neg = 0, pos = 0;
    int res = 0;
    for (int n : nums) {
        if (n == 0) {
            neg = 0; pos = 0;
        }
        else if (n > 0) {
            pos++;
            if (neg > 0 ) {
                ++neg;
            } 
        } else {
            int posTmp = pos;
            pos = neg == 0 ? 0 : neg + 1;
            neg = posTmp + 1;
        }
        res = Math.max(res, pos);
    }
    return res;
}
}

