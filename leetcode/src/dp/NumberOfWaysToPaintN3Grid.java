package dp;

/**
 * 1411. Number of Ways to Paint N × 3 Grid
 *
 * Difficulty: Hard
 * You have a grid of size n x 3 and you want to paint each cell of the grid with exactly
 * one of the three colours: Red, Yellow or Green while making sure that no two adjacent
 * cells have the same colour (i.e no two cells that share vertical or horizontal sides
 * have the same colour).
 *
 * You are given n the number of rows of the grid.
 *
 * Return the number of ways you can paint this grid. As the answer may grow large, the
 * answer must be computed modulo 10^9 + 7.
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 12
 * Explanation: There are 12 possible way to paint the grid as shown:
 *
 * Example 2:
 *
 * Input: n = 2
 * Output: 54
 * Example 3:
 *
 * Input: n = 3
 * Output: 246
 * Example 4:
 *
 * Input: n = 7
 * Output: 106494
 * Example 5:
 *
 * Input: n = 5000
 * Output: 30228214
 *
 *
 * Constraints:
 *
 * n == grid.length
 * grid[i].length == 3
 * 1 <= n <= 5000
 */
public class NumberOfWaysToPaintN3Grid {

  /**
   * One of two patterns is possible - 123 or 121. For every of those the next
   * to is counted by the formula below -
   * for 123 = 2*123 + 2*121,
   * for 121 = 3*121 + 2*123
   * Then do DP for each next state
   *
   123 -

   212  121  121 -2
   312  123  123 -2
   231  123
   232  121

   121

   212  121  121 -3
   213  123  123 -2
   232  121
   312  123
   313  121

   * @param n
   * @return
   */
  public int numOfWays(int n) {
    long first123 = 6, first121 = 6;
    int mod = 1_000_000_007;
    for (int i = 1; i < n; i++) {
      long cur123 = 2*(first123 + first121);
      long cur121 = 2*first123 + 3*first121;
      first123 = cur123 % mod;
      first121 = cur121 % mod;
    }
    return (int)(first123 + first121)%mod;
  }
}
