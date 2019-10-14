package dp;

/**
 * 1223. Dice Roll Simulation
 * Medium
 *
 * A die simulator generates a random number from 1 to 6 for each roll. You introduced a constraint to the generator
 * such that it cannot roll the number i more than rollMax[i] (1-indexed) consecutive times.
 *
 * Given an array of integers rollMax and an integer n, return the number of distinct sequences that can be obtained
 * with exact n rolls.
 *
 * Two sequences are considered different if at least one element differs from each other. Since the answer may be
 * too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2, rollMax = [1,1,2,2,2,3]
 * Output: 34
 * Explanation: There will be 2 rolls of die, if there are no constraints on the die, there are 6 * 6 = 36 possible
 * combinations. In this case, looking at rollMax array, the numbers 1 and 2 appear at most once consecutively,
 * therefore sequences (1,1) and (2,2) cannot occur, so the final answer is 36-2 = 34.
 * Example 2:
 *
 * Input: n = 2, rollMax = [1,1,1,1,1,1]
 * Output: 30
 * Example 3:
 *
 * Input: n = 3, rollMax = [1,1,1,2,2,3]
 * Output: 181
 *
 *
 * Constraints:
 *
 * 1 <= n <= 5000
 * rollMax.length == 6
 * 1 <= rollMax[i] <= 15
 */
public class DiceSimulator {

    /**
     * DP approach. Ideally num for every next num equals to sum of all comb at previous step. However, there are
     * cases when number can be lower. Keep the sum of previous layer in the 7-th column of every row
     *
     * @param n
     * @param rollMax
     * @return
     */
    public int dieSimulator(int n, int[] rollMax) {
        long[][] dp = new long[n][7];
        long mod = 1_000_000_007;

        for (int i = 0; i < 6; i++) {
            dp[0][i] = 1;
        }
        dp[0][6] = 6;

        for (int nn = 1; nn < n; nn++) {
            long sum = 0;
            for (int i = 0; i < 6; i++) {
                dp[nn][i] = dp[nn - 1][6];
                int dif = nn - rollMax[i];
                if (dif >= 0) {
                    if (dif > 0) {
                        dp[nn][i] += mod - dp[dif - 1][6] + dp[dif - 1][i];
                    } else {
                        dp[nn][i] -= 1;
                    }
                }
                sum += dp[nn][i];
            }
            dp[nn][6] = sum%mod;
        }

        return (int)dp[n - 1][6];
    }
}
