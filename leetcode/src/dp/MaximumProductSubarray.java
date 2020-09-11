package dp;

/**
 * 152. Maximum Product Subarray
Medium

Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

https://leetcode.com/problems/maximum-product-subarray/
 */
public class MaximumProductSubarray {
    
    /**
     * Do prefix product and suffix product subarrays, compare it to a running number on every 
     * iteration. If the suffix or preffix is 0 - assing current array element instead
     * 
     * O(n) time
     * O(1) space
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int l = 1, r = 1;
        for (int i = 0; i < nums.length; i++) {
            if (l == 0)
                l=nums[i];
            else
                l*=nums[i];
            
            if (r == 0) {
                r=nums[nums.length - 1 - i];
            } else {
                r*=nums[nums.length - 1 - i];
            }

            max = Math.max(max, Math.max(r, l));
        }
        return max;
    }
}
