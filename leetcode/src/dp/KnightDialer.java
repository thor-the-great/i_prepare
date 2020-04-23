package dp;

/**
 * 935. Knight Dialer
 * Medium
 *
 *
 * A chess knight can move as indicated in the chess diagram below:
 *
 *  .
 *
 *
 *
 * This time, we place our chess knight on any numbered key of a phone pad (indicated above), and
 * the knight makes N-1 hops.  Each hop must be from one key to another numbered key.
 *
 * Each time it lands on a key (including the initial placement of the knight), it presses the
 * number of that key, pressing N digits total.
 *
 * How many distinct numbers can you dial in this manner?
 *
 * Since the answer may be large, output the answer modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: 1
 * Output: 10
 * Example 2:
 *
 * Input: 2
 * Output: 20
 * Example 3:
 *
 * Input: 3
 * Output: 46
 *
 *
 * Note:
 *
 * 1 <= N <= 5000
 */
public class KnightDialer {

  long[][][] dp;
  int mod = 1_000_000_007;

  /**
   * Number of unique combinations == number of unique paths.
   * We can assume that for n == 1 there will be 1 such path. For n + 1 the number of such
   * paths will be the sum of 8 possible places you can come to this cell:
   * (r,c,n) = sum of ((r-2,c-1,n-1) + (r-1, c-2, n-1), .... ).
   * but this can be saved in a 3d array with states being r,c,and n.
   * also need to take into account only valid moves, so check if r and c are within (4x3) and
   * not in invalid squares
   *
   * @param N
   * @return
   */
  public int knightDialer(int N) {
    dp = new long[4][3][N + 1];
    long res = 0;
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 3; c++) {
        res+=helper(r, c, N);
      }
    }

    return (int) (res%mod);
  }

  long helper(int r, int c, int n) {
    if (r < 0 || c < 0 || r >= 4 || c >= 3 || (r == 3 && c != 1))
      return 0;

    if (n == 1)
      return 1;

    if (dp[r][c][n] > 0)
      return dp[r][c][n];

    long num = helper(r - 2, c - 1, n - 1)
        + helper(r - 2, c + 1, n - 1)
        + helper(r - 1, c + 2, n - 1)
        + helper(r + 1, c + 2, n - 1)
        + helper(r + 2, c + 1, n - 1)
        + helper(r + 2, c - 1, n - 1)
        + helper(r + 1, c - 2, n - 1)
        + helper(r - 1, c - 2, n - 1);
    num%=mod;
    dp[r][c][n] = num;
    return num;
  }
}
