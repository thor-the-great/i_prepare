package dp;

/**
 * 1155. Number of Dice Rolls With Target Sum
 * Medium
 *
 * You have d dice, and each die has f faces numbered 1, 2, ..., f.
 *
 * Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the
 * face up numbers equals target.
 *
 *
 *
 * Example 1:
 *
 * Input: d = 1, f = 6, target = 3
 * Output: 1
 * Explanation:
 * You throw one die with 6 faces.  There is only one way to get a sum of 3.
 * Example 2:
 *
 * Input: d = 2, f = 6, target = 7
 * Output: 6
 * Explanation:
 * You throw two dice, each with 6 faces.  There are 6 ways to get a sum of 7:
 * 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
 * Example 3:
 *
 * Input: d = 2, f = 5, target = 10
 * Output: 1
 * Explanation:
 * You throw two dice, each with 5 faces.  There is only one way to get a sum of 10: 5+5.
 * Example 4:
 *
 * Input: d = 1, f = 2, target = 3
 * Output: 0
 * Explanation:
 * You throw one die with 2 faces.  There is no way to get a sum of 3.
 * Example 5:
 *
 * Input: d = 30, f = 30, target = 500
 * Output: 222616187
 * Explanation:
 * The answer must be returned modulo 10^9 + 7.
 *
 *
 * Constraints:
 *
 * 1 <= d, f <= 30
 * 1 <= target <= 1000
 */
public class NumberOfDiceRollsWithTargetSum {

  /**
   * This problem is similar to max profit in selling n times problem
   * (https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/) - this is knapsack dp problem.
   *
   * Let's say we have a grid [n][m] where element at [n][m] represents the number of variants to
   * represent number m with the n dices. We can compute dp[n][m] as following:
   *
   * dp[n - 1][[m - f - 1] + dp[n - 1][[m - f] + .... + dp[n - 1][[m - 1]
   *
   * this is because to include the current dice we must add number 1...f to the result of n - 1 dices.
   * Initially we put 1 to dp[0][1] ... dp[0][f] - meaning that with one dice we can create target
   * 1 to f and only one way is possible.
   *
   * Few optimizations are possible:
   *
   * We can optimize the sum part for dp[n - 1][[m - f - 1] + dp[n - 1][[m - f] + .... + dp[n - 1][[m - 1].
   * In every next step we exclude the smallest target but add the m - 1 target. So instead of one more
   * nested loop for the sum we keep running sum and do one addition and one subtraction. This changes
   * solution from cubic to quadratic
   *
   * We don't actually need the whole nxm grid. In reallity we only check the previous row, so two 1-d arrays
   * are fine. This lowers the space complaxity from O(nm) to O(n).
   *
   * Below is optmized solution.
   * O(dxtarget) time, O(target) space
   * @param d
   * @param f
   * @param target
   * @return
   */
  public int numRollsToTarget(int d, int f, int target) {
    int mod = 1_000_000_007;

    int[] dpCur = new int[target + 1], dpNext = new int[target + 1];

    for (int i = 1; i <= Math.min(f, target); i++)
      dpCur[i] = 1;

    for (int dd = 1; dd < d; dd++) {
      int sum = 0;
      for (int t = 1; t <= target; t++) {
        sum += dpCur[t - 1];
        if (t - f - 1 >= 0) {
          sum-=dpCur[t - f - 1];
          if (sum < 0)
            sum += mod;
        }
        sum %= mod;
        dpNext[t] = sum;
      }
      dpCur = dpNext;
      dpNext = new int[target + 1];
    }
    return dpCur[target];
  }
}
