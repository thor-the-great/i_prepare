package strings;

/**
 * 97. Interleaving String
 * Hard
 *
 * 1221
 *
 * 74
 *
 * Add to List
 *
 * Share
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * Example 1:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Example 2:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 */
public class InterleavingString {

  /**
   * DP approach - knapsack pattern, create gird of bools, s1Len X s2Len. Cell represents
   * if (i + j) of s3 can be formed by s1(0..i) and s2(0..j).
   * Do i + 1 and j + 1 for convenience.
   * [0,0] = true
   * [i, 0] == dp[i - 1][0] && s1(i) == s3(i), [0, j] == dp[0][j - 1] && s2(j) == s3(j)
   *
   * Transition function:
   *
   * dp[i][j] ==    dp[i - 1][j] && (s1(i) == s3(i + j)
   *             OR dp[i][j - 1] && (s2(j) == s3(i + j)
   *
   *  final answer is at dp[s1Len][s2Len]
   *
   * @param s1
   * @param s2
   * @param s3
   * @return
   */
  public boolean isInterleave(String s1, String s2, String s3) {
    int s1Len = s1.length(), s2Len = s2.length();

    if (s3.length() != s1Len + s2Len)
      return false;

    boolean[][] dp = new boolean[s1Len + 1][s2Len + 1];
    dp[0][0] = true;

    for (int i = 1; i <= s1Len; i++) {
      dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
    }

    for (int i = 1; i <= s2Len; i++) {
      dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
    }

    for (int i = 1; i <= s1Len; i++) {
      for (int j = 1; j <= s2Len; j++) {
        dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
      }
    }

    return dp[s1Len][s2Len];
  }
}
