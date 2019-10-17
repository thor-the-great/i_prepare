package dp;

/**
 * 1216. Valid Palindrome III
 * Hard
 *
 *
 * Share
 * Given a string s and an integer k, find out if the given string is a K-Palindrome or not.
 *
 * A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcdeca", k = 2
 * Output: true
 * Explanation: Remove 'b' and 'e' characters.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s has only lowercase English letters.
 * 1 <= k <= s.length
 */
public class ValidPalindrome3 {
    /**
     * Idea is to use dynamic programmig and find length of the palindromic subsequences until we found one of length
     * at least s.len - k
     *
     * DP matrix has meaning of longest palindromic subsequence that starts at i and ends at j. We compare each pair
     * of symbols and if it's equals - add 2 to the length of the previous subsequence, otherwise - take the longest
     * length from 2 neighbours. Check if we reach the length at every step. Initialize matrix with 1-s on diagonal
     * - meaning is the every single char is a palindrome of length 1.
     *
     * O(n^2) for time - two nested for loops. O(n^2) space - nxn matrix to save DP states.
     * @param s
     * @param k
     * @return
     */
    public boolean isValidPalindrome(String s, int k) {
        int N = s.length();
        int len = s.length() - k;
        //init dp matrix, set 1-s in diagonal
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) dp[i][i] = 1;
        //start walking over shorter substrings
        for (int r = 1; r < N; r++) {
            for (int c = r - 1; c >=0; c--) {
                //if chars are same - add 2 to the prev palindrome length
                if (s.charAt(r) == s.charAt(c)) {
                    dp[r][c] = dp[r - 1][c + 1] + 2;
                } else {
                    //otherwise - get longest among neighbours
                    dp[r][c] = Math.max(dp[r - 1][c], dp[r][c + 1]);
                }
                //check if we reach the threshold
                if (dp[r][c] >= len)
                    return true;
            }
        }
        return false;
    }
}
