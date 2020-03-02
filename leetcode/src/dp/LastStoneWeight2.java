/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package dp;

import java.util.Arrays;

/**
 * 1049. Last Stone Weight II
 * Medium
 *
 * We have a collection of rocks, each rock has a positive integer weight.
 *
 * Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x
 * and y with x <= y.  The result of this smash is:
 *
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new
 * weight y-x.
 * At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone
 * (the weight is 0 if there are no stones left.)
 *
 *
 *
 * Example 1:
 *
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
 * we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
 * we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.
 *
 *
 * Note:
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 */
public class LastStoneWeight2 {

  /**
   * All numbers can be with "+" or "-", this actually c an be seen as s1 - s2. And
   * s1 + s2 = sum => 2s1 = sum + dif, s1 = (sum+dif)/2.
   * So we are looking to the smallest difference between s1 and s2, which means - the closest
   * to sum/2 sum.
   * This is a knapsack problem, subsets with smallest difference. Solvable with bottom-up
   * approach, O(n*m) time, O(sum/2) space.
   *
   * @param stones
   * @return
   */
  public int lastStoneWeightII(int[] stones) {
    if (stones.length == 0)
      return 0;
    int sum = 0, sum2 = 0;

    for (int s : stones)
      sum2+=s;

    sum=sum2/2;
    boolean[][] dp = new boolean[2][sum + 1];

    dp[1][0] = true;
    if (stones[0] <= sum)
      dp[1][stones[0]] = true;

    int max = 0;
    for (int s = 1; s < stones.length; s++) {
      dp[0] = Arrays.copyOf(dp[1],sum);
      for (int ss = 1; ss <= sum; ss++) {
        if (ss - stones[s] >= 0)
          dp[1][ss] |= dp[0][ss - stones[s]];
        if (dp[1][ss])
          max = Math.max(max, ss);
      }
    }

    return sum2 - (2*max);
  }
}
