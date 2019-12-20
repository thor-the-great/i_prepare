package path.linkedin;

import java.util.Arrays;

/**
 * 322. Coin Change
 * Medium
 *
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
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
 *
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        //return coinChangeRecursive(coins, amount);
        //return coinChangeDP(coins, amount);
        return coinChangeDPBottomUp(coins, amount);
    }

    int[] coins;
    int[] dp;

    public int coinChangeDPBottomUp(int[] coins, int amount) {
        int N = coins.length;
        if (amount == 0)
            return 0;
        if (N == 0)
            return -1;
        int[] dp2 = new int[amount + 1];
        Arrays.fill(dp2, 1, amount  +1, amount + 1);

        for(int c = 0; c < coins.length; c++) {
            if (coins[c] < amount)
                dp2[coins[c]] = 1;
        }

        for(int i = 1; i <= amount; i++) {
            for(int c = 0; c < coins.length; c++) {
                if (coins[c] <= i )
                    dp2[i] = Math.min(dp2[i - coins[c]] + 1, dp2[i]);
            }
        }
        return dp2[amount] > amount ? -1 : dp2[amount];
    }

    public int coinChangeDP(int[] coins, int amount) {
        int N = coins.length;
        if (amount == 0)
            return 0;
        if (N == 0)
            return -1;
        this.coins = coins;
        this.dp = new int[amount];
        return helperDP(amount);
    }

    int helperDP(int amount) {
        if (amount == 0)
            return 0;
        if (amount < 0) return -1;
        if (dp[amount - 1] > 0) return dp[amount - 1];
        int minSol = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int res = helperDP(amount - coins[i]);
            if (res >= 0 && res < minSol)
                minSol = res + 1;
        }
        dp[amount - 1] = minSol == Integer.MAX_VALUE ? - 1: minSol;
        return dp[amount - 1];
    }

    public int coinChangeRecursive(int[] coins, int amount) {
        int N = coins.length;
        if (amount == 0)
            return 0;
        if (N == 0)
            return -1;
        this.coins = coins;
        return helper(amount, 0);
    }

    int helper(int amount, int idx) {
        if (amount == 0)
            return 0;
        if (idx < coins.length) {
            int num = amount / coins[idx];
            int minSol = Integer.MAX_VALUE;
            for (int i = 0; i <= num; i++) {
                int used = i * coins[idx];
                if (amount >= used ) {
                    int res = helper(amount - used, idx + 1);
                    if (res != -1)
                        minSol = Math.min(minSol, res + i);
                }
            }
            return minSol == Integer.MAX_VALUE ? -1 : minSol;
        }
        return -1;
    }

    public int coinChangeDPRec(int[] coins, int amount) {
        if (amount < 0)
            return -1;

        return helper(coins, amount, new int[amount + 1]);
    }

    int helper(int[] coins, int amount, int[] count) {
        if (amount < 0)
            return -1;
        if (amount == 0)
            return 0;

        if (count[amount] != 0)
            return count[amount];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int cur = helper(coins, amount - coin, count);
            if (cur >= 0 && cur < min) {
                min = cur + 1;
            }
        }
        count[amount] = (min == Integer.MAX_VALUE ? -1 : min);
        return count[amount];
    }

    public static void main(String[] args) {
        CoinChange obj = new CoinChange();
        System.out.println(obj.coinChange(new int[]{1,2,5}, 11));//3
        System.out.println(obj.coinChange(new int[]{346,29,395,188,155,109}, 9401));//26
        System.out.println(obj.coinChange(new int[]{2}, 3));//-1
    }
}
