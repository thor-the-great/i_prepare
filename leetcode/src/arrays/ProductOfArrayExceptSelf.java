package arrays;

/**
 * 238. Product of Array Except Self
 * Medium
 *
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product
 * of all the elements of nums except nums[i].
 *
 * Example:
 *
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Note: Please solve it without division and in O(n).
 *
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose
 * of space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {

    /**
     * Go from left to right accumulating products from elements.
     * Then go from right to left, but this time accumulate products in the variable and multiply it with every
     * element
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int N = nums.length;
        int[] t = new int[N];

        t[0] = 1;
        for (int i = 1; i < N; i++) {
            t[i] = t[i - 1]*nums[i - 1];
        }

        int c = 1;
        for (int i = N - 1; i >=0; i--) {
            t[i] *=c;
            c*=nums[i];
        }
        return t;
    }
}
