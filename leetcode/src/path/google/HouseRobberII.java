package path.google;

/**
 * 213. House Robber II
 * Medium
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
 * stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last
 * one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two
 * adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount
 * of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 *              because they are adjacent houses.
 * Example 2:
 *
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 */
public class HouseRobberII {

    /**
     * Idea - we need to make a choice regarding last and first houses - either we robb first and stay away from last or
     * vise versa.
     * For that we create 2D array, index 0 - we start from 1-st house but don't include last, for case 1 - we start from
     * 2-nd but check last
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int N = nums.length;

        if (N == 0) return 0;
        if (N == 1) return nums[0];

        int[][] dp = new int[N][2];
        //start from 1-st house - we can't robb last one
        dp[0][0] = nums[0];
        dp[1][0] = Math.max(nums[1], dp[0][0]);
        //start from second - we can't count 1-st but can reach last
        dp[0][1] = 0;
        dp[1][1] = nums[1];

        for (int i = 2; i < N; i++) {
            //special case because in case of start from first we can't include last house
            if (i < N - 1) {
                dp[i][0] = Math.max(dp[i - 2][0] + nums[i], dp[i - 1][0]);
                dp[i][1] = Math.max(dp[i - 2][1] + nums[i], dp[i - 1][1]);
            } else {
                dp[i][1] = Math.max(dp[i - 2][1] + nums[i], dp[i - 1][1]);
            }
        }
        return Math.max(dp[N - 1][1], dp[N - 2][0]);
    }
}
