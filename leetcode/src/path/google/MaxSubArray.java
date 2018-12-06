package path.google;

/**
 * 152. Maximum Product Subarray
 * Medium
 *
 *
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaxSubArray {

    /**
     * kind of dp solution - keep track of maxes and minies. If current element is < 0 - multiply by minimum, else
     * multiply by maximum. On each iteration save maximum.
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int N = nums.length;
        if (N == 1)
            return nums[0];
        int[] maxes = new int[N];
        int[] minies = new int[N];
        int res = nums[0];
        maxes[0] = nums[0]; minies[0] = nums[0];

        for (int i = 1; i < N; i++) {
            if (nums[i] >= 0) {
                maxes[i] = Math.max(maxes[i - 1]*nums[i], nums[i]);
                minies[i] = Math.min(minies[i - 1]*nums[i], nums[i]);
            } else {
                maxes[i] = Math.max(minies[i - 1]*nums[i], nums[i]);
                minies[i] = Math.min(maxes[i - 1]*nums[i], nums[i]);
            }
            res = Math.max(res, maxes[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        MaxSubArray obj = new MaxSubArray();
        System.out.println(obj.maxProduct(new int[]{2,3,-2,4}));
    }
}
