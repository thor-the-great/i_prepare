package path.amazon;
/**
 * Maximum Subarray
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum
 * and return its sum.
 *
 * Example:
 *
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 *
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach,
 * which is more subtle.
 */
public class MaxSubarray {

    int[] nums;

    public int maxSubArray(int[] nums) {
        //idea of this one - is to use divide and conq approach. Divide array into sub-arrays. For every such
        //sub-array down to 1 element max sum will be max of (left side, right side and array with point in the middle)
        //left and right are simple recursive calls, middle one calculated by iterating from the center to the left and
        //right, one each step calc max sum
        this.nums = nums;
        return maxRecursive(0, nums.length -1 );
    }

    int maxRecursive(int l, int r) {
        if (l == r)
            return nums[l];
        int m = (l + r) /2;
        return Math.max(Math.max(maxRecursive(l, m), maxRecursive(m + 1, r)), maxSumMid(l, r, m));
    }

    int maxSumMid(int l, int r, int m) {
        int maxLeft = Integer.MIN_VALUE;
        int s = 0;
        for (int i = m; i >= l; i--) {
            s += nums[i];
            if (maxLeft < s)
                maxLeft = s;
        }

        int maxRight = Integer.MIN_VALUE;
        s = 0;
        for (int i = m + 1; i <= r; i++) {
            s += nums[i];
            if (maxRight < s)
                maxRight = s;
        }

        return maxRight + maxLeft;
    }

    public int maxSubArrayKadaneOnePass(int[] nums) {
        int maxSoFar = Integer.MIN_VALUE;
        int maxEndHere = 0;
        for (int i =0; i < nums.length; i++) {
            maxEndHere += nums[i];
            if (maxSoFar < maxEndHere) maxSoFar = maxEndHere;
            if (maxEndHere < 0) maxEndHere = 0;
        }
        return maxSoFar;
    }
}
