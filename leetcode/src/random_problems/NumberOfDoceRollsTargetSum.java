package random_problems;

import java.util.Arrays;

/**
 * 1155. Number of Dice Rolls With Target Sum
 * Medium
 *
 * You have d dice, and each die has f faces numbered 1, 2, ..., f.
 *
 * Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so
 * the sum of the face up numbers equals target.
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
public class NumberOfDoceRollsTargetSum {

  /**
   * Use approach similar to knapsack problem - fill one row (for one dice), then for the next one
   * fill based on the previous one. Result [i,j] will be [i,j] + [i - 1][j - dice_face].
   *
   * Because we only need current row and the previous one we need only 2 rows.
   *
   * @param d
   * @param f
   * @param target
   * @return
   */
  public int numRollsToTarget(int d, int f, int target) {
    int mod = 1_000_000_007;
    //initialize two arrays for current and previous dices
    int[] dp1 = new int[target + 1], dp2 = new int[target + 1];
    //use 1 as a starting point - there is 1 way to have 0.
    dp1[0] = 1;
    //iterate over dices and then internally - on each possible target
    for (int i = 1; i <= d; i++) {
      for (int j = 1; j <= target; j++) {
        int min = Math.min(j, f);
        int t = 0;
        //for every possible value from 1 to dice face - update current row with
        //values from previous row. Going from the lowest to j
        while (t < min) {
          dp2[j] += dp1[j - min + t];
          //take care of mod if needed
          if (dp2[j] > mod)
            dp2[j] = dp2[j] % mod;
          t++;
        }
      }
      //prepare arrays for the iteration - put values from 2nd to 1st, clear 2nd
      dp1 = Arrays.copyOf(dp2, target + 1);
      Arrays.fill(dp2, 0);
    }

    return dp1[target] % mod;
  }

  /**
   * Cleaner version of the 1-st approach where we keep rows for every dice
   * @param d
   * @param f
   * @param target
   * @return
   */
  public int numRollsToTarget2DArray(int d, int f, int target) {
    int mod = 1_000_000_007;

    int[][] dp = new int[d + 1][target + 1];
    dp[0][0] = 1;

    for (int i = 1; i <= d; i++) {
      for (int j = 1; j <= target; j++) {
        int min = Math.min(j, f);
        int t = min;
        while (t >= 1) {
          dp[i][j] = (dp[i][j] + dp[i - 1][j - t]) % mod;
          t--;
        }
      }
    }

    return dp[d][target];
  }
}
