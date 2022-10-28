package dp;

/**
 * 2369. Check if There is a Valid Partition For The Array
 * Medium
 *
 * You are given a 0-indexed integer array nums. You have to partition the array into one or more contiguous subarrays.
 *
 * We call a partition of the array valid if each of the obtained subarrays satisfies one of the following conditions:
 *
 *     The subarray consists of exactly 2 equal elements. For example, the subarray [2,2] is good.
 *     The subarray consists of exactly 3 equal elements. For example, the subarray [4,4,4] is good.
 *     The subarray consists of exactly 3 consecutive increasing elements, that is, the difference between adjacent elements is 1. For example, the subarray [3,4,5] is good, but the subarray [1,3,5] is not.
 *
 * Return true if the array has at least one valid partition. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,4,4,5,6]
 * Output: true
 * Explanation: The array can be partitioned into the subarrays [4,4] and [4,5,6].
 * This partition is valid, so we return true.
 *
 * Example 2:
 *
 * Input: nums = [1,1,1,2]
 * Output: false
 * Explanation: There is no valid partition for this array.
 *
 *
 *
 * Constraints:
 *
 *     2 <= nums.length <= 105
 *     1 <= nums[i] <= 106
 */
public class CheckifThereIsValidPartitionForArray {

    /**
     * DP with single array of bool
     * For each i we do check if partition of [0, i] is possible. For that we check 2 cases:
     * dp[i - 2] == true && seq_len(2) possible
     * OR
     * dp[i - 3] == true && seq_len(3) possible
     * at the end check dp[len - 1]
     *
     * O(n) time, O(n) space
     *
     * @param nums
     * @return
     */
    public boolean validPartition(int[] nums) {
        int N = nums.length;
        boolean[] dp = new boolean[N];
        dp[0] = false;
        dp[1] = checkLen2(nums, 1);
        if (N > 2) {
            dp[2] = checkLen3(nums, 2);
        }
        for (int i = 3; i < N; i++) {
            boolean isLen2 = dp[i - 2] && checkLen2(nums, i);
            boolean isLen3 = dp[i - 3] && checkLen3(nums, i);;
            dp[i] = isLen2 || isLen3;
        }

        return dp[N - 1];
    }

    boolean checkLen2(int[] nums, int i) {
        return (nums[i] == nums[i - 1]);
    }

    boolean checkLen3(int[] nums, int i) {
        return ((nums[i] == nums[i - 1] && nums[i - 1] == nums[i - 2])
                || ((nums[i] - nums[i - 1]) == 1 && (nums[i - 1] - nums[i - 2]) == 1));
    }
}
