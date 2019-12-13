/**
 * Longest Palindromic Substring New
 *     Multi Dimensional Arrays Dynamic Programming
 *
 * Given a String, write a method - longestPalSubstr that finds and returns the longest substring
 * which is also a Palindrome. Try and accomplish this in at most O(n2) runtime.
 *
 * Examples :
 * "bccb" => "bccb"
 * "bccd" => "cc"
 * "bccc" => "ccc"
 */
public class LongestPalindromicSubstring {

  /**
   * DP solution - O(n^2).
   * Create NxN matrix of booleans, if from i to j is a palindrome - set dp[i][j] = true.
   * Iterate over the matrix and check for the characters. Catch - if string is less than 3 length -
   * need to fill matrix cell as well
   * @param s
   * @return
   */
  public String longestPalindrome(String s) {
    if (s.length() < 2)
      return s;
    boolean[][] dp = new boolean[s.length()][s.length()];
    int i = 0, j = 0;
    for (int r = s.length() - 1; r >=0; r--) {
      for (int c = r; c < s.length(); c++) {
        if (s.charAt(r) == s.charAt(c) && (c - r < 3 || dp[r + 1][c - 1]))
          dp[r][c] = true;
        if (dp[r][c] && (c - r) > (j - i)) {
          i = r; j = c;
        }
      }
    }

    return s.substring(i, j + 1);
  }
}
