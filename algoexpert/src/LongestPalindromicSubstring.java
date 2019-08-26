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
   * @param str
   * @return
   */
  public String longestPalSubstr(String str){
    if (str == null || str.length() == 0 )
      return str;
    int N = str.length();
    boolean[][] dp = new boolean[N][N];

    int maxLen = 1;
    int maxIdx = 0;

    for (int i = N - 1; i >= 0; i--) {
      for (int j = i; j < N; j++) {
        if (str.charAt(i) == str.charAt(j) && ((j - i <= 2 ) || dp[i + 1][j - 1])) {
          dp[i][j] = true;
          if ((j - i + 1) > maxLen ) {
            maxLen = j - i + 1;
            maxIdx = i;
          }
        }
      }
    }

    return str.substring(maxIdx, maxIdx + maxLen);
  }
}
