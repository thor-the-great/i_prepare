package random_problems;

import java.util.Arrays;

/**
 * 322. Coin Change
 * Medium
 *
 * You are given coins of different denominations and a total amount of money amount. Write a
 * function to compute the fewest number of coins that you need to make up that amount. If that
 * amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Example 1:
 *
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 */
public class CoinChange {

  /**
   * DP problem - create array of amounts from 0 to amount. Initialize all elements but
   * first with amount + 1. For each coin iterate over array and set the element in array @ amount
   * as min of current at amount and arr[amount - coin].  
   * @param coins
   * @param amount
   * @return
   */
  public int coinChange(int[] coins, int amount) {
    int max = amount + 1;
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, max);
    dp[0] = 0;
    for (int c = 0; c < coins.length; c++) {
      for (int a = 1; a <= amount; a++) {
        if (a >= coins[c])
          dp[a] = Math.min(dp[a], dp[a - coins[c]] + 1);
      }
    }
    return dp[amount] == max ? -1 : dp[amount];
  }
}
