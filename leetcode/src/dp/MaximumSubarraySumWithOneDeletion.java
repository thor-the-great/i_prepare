package dp;

/**
 * 1186. Maximum Subarray Sum with One Deletion
 * Medium
 *
 * Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most
 * one element deletion. In other words, you want to choose a subarray and optionally delete one element from it so
 * that there is still at least one element left and the sum of the remaining elements is maximum possible.
 *
 * Note that the subarray needs to be non-empty after deleting one element.
 *
 * Example 1:
 *
 * Input: arr = [1,-2,0,3]
 * Output: 4
 * Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
 * Example 2:
 *
 * Input: arr = [1,-2,-2,3]
 * Output: 3
 * Explanation: We just choose [3] and it's the maximum sum.
 * Example 3:
 *
 * Input: arr = [-1,-1,-1,-1]
 * Output: -1
 * Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an
 * empty subarray to make the sum equals to 0.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i] <= 10^4
 */
public class MaximumSubarraySumWithOneDeletion {

    /**
     * DP solution:
     * Its similar to max sum in array problem, solved by Kadein's algo. The difference is that we can delete one
     * element, in this case we need to calculate the max number from sub-array of [0, i - 1] and [i + 1, N -1].
     * @param arr
     * @return
     */
    public int maximumSum(int[] arr) {
        int N = arr.length;
        int max = arr[0];
        //create dp array
        //dp1 for sums that ends at i - 1
        //dp2 for sums that starts at i + 1
        int[] dp1 = new int[N], dp2 = new int[N];
        //fill the left side of i first
        dp1[0] = arr[0];
        for (int i = 1; i < N; i++) {
            dp1[i] = arr[i] + (dp1[i - 1] > 0 ? dp1[i - 1] : 0);
            max = Math.max(max, dp1[i]);
        }
        //now fill the right side
        dp2[N - 1] = arr[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            dp2[i] = arr[i] + (dp2[i + 1] > 0 ? dp2[i + 1] : 0);
        }
        for (int i = 1; i < N - 1; i++) {
            if (arr[i] < 0)
                max = Math.max(max, dp1[i - 1] + dp2[i + 1]);
        }
        return max;
    }
}
