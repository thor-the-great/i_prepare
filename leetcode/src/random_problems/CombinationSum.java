package random_problems;

import java.util.Arrays;

/**
 * 377. Combination Sum IV
 * Medium
 *
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that
 * add up to a positive integer target.
 *
 * Example:
 *
 * nums = [1, 2, 3]
 * target = 4
 *
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 *
 * Note that different sequences are counted as different combinations.
 *
 * Therefore the output is 7.
 *
 *
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 *
 * Credits:
 * Special thanks to @pbrother for adding this problem and creating all test cases.
 *
 */
public class CombinationSum {

    /**
     * Idea: this is bottom-up approach - to make target sum we'll need to start from 0 (which is possible to have
     * with 1 combination - just don't take anything else.
     * Then we iterate over possible sums, adding up combinations we can take to get it. Initially everything is
     * initialized with 0.
     *
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int targt = 1; targt <= target; targt++) {
            for (int numIdx = 0; numIdx < nums.length; numIdx++) {
                if (targt - nums[numIdx] >= 0) {
                    dp[targt] += dp[targt - nums[numIdx]];
                }
            }
        }

        return dp[target];
    }

    int[] nums;
    int[] dp;

    public int combinationSum4DPRecursive(int[] nums, int target) {
        dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        this.nums = nums;
        helper(target);
        return dp[target];
    }

    int helper(int curr) {

        if (dp[curr] != -1)
            return dp[curr];

        if (curr < 0)
            return 0;

        int res = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] <= curr) {
                res += helper(curr - nums[i]);
            }
        }
        dp[curr] = res;
        return res;
    }

    public static void main(String[] args) {
        CombinationSum obj = new CombinationSum();
        int[] arr;

        arr = new int[] {1, 2, 3};
        System.out.println(obj.combinationSum4(arr, 4));
    }
}
