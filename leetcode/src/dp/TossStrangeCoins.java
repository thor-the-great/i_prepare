package dp;

/**
 * 1230. Toss Strange Coins
 * Difficulty: Medium
 * You have some coins.  The i-th coin has a probability prob[i] of facing heads when tossed.
 *
 * Return the probability that the number of coins facing heads equals target if you toss every coin exactly once.
 *
 * Example 1:
 *
 * Input: prob = [0.4], target = 1
 * Output: 0.40000
 * Example 2:
 *
 * Input: prob = [0.5,0.5,0.5,0.5,0.5], target = 0
 * Output: 0.03125
 *
 *
 * Constraints:
 *
 * 1 <= prob.length <= 1000
 * 0 <= prob[i] <= 1
 * 0 <= target <= prob.length
 * Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 */
public class TossStrangeCoins {

    /**
     * Explanation
     * dp[c][k] is the prob of tossing c first coins and get k faced up.
     * dp[c][k] = dp[c - 1][k] * (1 - p) + dp[c - 1][k - 1] * p)
     * where p is the prob for c-th coin.
     *
     * Complexity
     * Time O(N^2)
     * Space O(N)
     *
     * @param prob
     * @param target
     * @return
     */
    public double probabilityOfHeads(double[] prob, int target) {
        int coins = prob.length;
        double[][] dp = new double[coins + 1][target + 1];
        dp[0][0] = 1.0;
        for (int c = 1; c <= coins; ++c) {
            dp[c][0] = dp[c - 1][0]*(1.0 - prob[c - 1]);
            for (int t = 1; t <= target; ++t) {
                dp[c][t] = (dp[c - 1][t - 1]*prob[c - 1]) + (dp[c - 1][t]*(1.0 - prob[c - 1]));
            }
        }
        return dp[coins][target];
    }
}
