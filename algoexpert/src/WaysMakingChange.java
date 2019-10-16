import java.util.HashMap;
import java.util.Map;

public class WaysMakingChange {

  /**
   * DP approach with memo,
   * @param coins
   * @param amount
   * @return
   */
  public static int makeChange(int[] coins, int amount) {
    if (amount <= 0 || coins.length == 0 )
      return 0;

    Map<String, Integer> m = new HashMap();

    return helper(coins, 0, amount, new HashMap());
  }

  public static int makeChangeUpBottom(int[] coins, int amount) {
    if (amount < 0)
      return 0;

    int[] dp = new int[amount + 1];
    dp[0] = 1;

    for (int c = 0; c < coins.length; c++) {
      for (int a = coins[c]; a <= amount; a++) {
        dp[a] += dp[a - coins[c]];
      }
    }
    return dp[amount];
  }

  static int helper(int[] coins, int idx, int amount, Map<String, Integer> m) {
    if (amount == 0)
      return 1;

    if (idx >= coins.length)
      return 0;

    String key = amount + "*" + idx;
    if (m.containsKey(key))
      return m.get(key);

    int ways = 0;
    int amountWithCoin = 0;

    while (amountWithCoin <= amount) {
      ways += helper(coins, idx + 1, amount - amountWithCoin, m);
      amountWithCoin += coins[idx];
    }

    m.put(key, ways);
    return ways;
  }

  public static void main(String[] args) {
    System.out.println(makeChange(new int[] {25, 10, 5, 1}, 10));
  }
}
