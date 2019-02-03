package random_problems;

/**
 * 312. Burst Balloons
 * Hard
 *
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You
 * are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right]
 * coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 *
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * Example:
 *
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBalloons {
    int[][] memo;
    int[] balloons;


    public int maxCoins(int[] nums) {
        int N = nums.length;
        memo = new int[N + 2][N + 2];
        balloons = new int[N + 2];
        int i = 1;
        for (int n : nums) {
            balloons[i++] = n;
        }
        balloons[0] = balloons[N + 1] = 1;
        return helper(0, N + 1);
    }

    int helper(int l, int r) {
        if (l + 1 == r) return 0;

        if (memo[l][r] > 0)
            return memo[l][r];
        int ans = 0;
        for (int i = l + 1; i < r; i++) {
            ans = Math.max(ans,
                    balloons[l] * balloons[i] * balloons[r] + helper(l, i) + helper(i, r));
        }
        memo[l][r] = ans;
        return ans;
    }
}
