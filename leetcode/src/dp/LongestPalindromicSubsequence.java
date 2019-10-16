package dp;

/**
 * 516. Longest Palindromic Subsequence
 * Medium
 *
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that
 * the maximum length of s is 1000.
 *
 * Example 1:
 * Input:
 *
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 * Input:
 *
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 */
public class LongestPalindromicSubsequence {

  /**
   * Using DP. i,j represents longest palindromic for substring from i to j. i,i is always a palindrome of length 1.
   * Start checking short words, if chars are equal - set r,c = r-1,c+1 + 2. Otherwise set to max r-1,c and
   * r,c-1
   *
   * @param s
   * @return
   */
  public int longestPalindromeSubseq(String s) {
    int N = s.length();
    int[][] dp = new int[N][N];
    for (int i = 0; i < N; i++) {
      dp[i][i] = 1;
    }

    for (int r = 1; r < N; r++) {
      for (int c = r - 1; c >= 0; c --) {
        if (s.charAt(r) == s.charAt(c)) {
          dp[r][c] = 2 + dp[r - 1][c + 1];
        } else {
          dp[r][c] = Math.max(dp[r - 1][c], dp[r][c + 1]);
        }
      }
    }
    return dp[N - 1][0];
  }
}
