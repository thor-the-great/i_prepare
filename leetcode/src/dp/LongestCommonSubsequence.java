package dp;

/**
 * 1143. Longest Common Subsequence
 * Medium
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 *
 * A subsequence of a string is a new string generated from the original string with some characters(can be none)
 * deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde"
 * while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.
 *
 *
 *
 * If there is no common subsequence, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 *
 * Constraints:
 *
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * The input strings consist of lowercase English characters only.
 */
public class LongestCommonSubsequence {

    /**
     * do dp, dp[i][j] is the longest common subsequence that ends at s1(i) and s2(j). If s1(i) == s2(j) then
     * dp[i][j] will be dp[i- 1][j - 1] + 1 (lcs of the shorted strings plus 1), otherwise take the longest excluding
     * this char - max(dp[i - 1][j], dp[i][j - 1]).
     *
     * Idea is that if we reverse the string then there will be some characters that are common between both parts.
     * Those characters we don't need to insert. But the rest of the string's chars we have to supply to make the
     * string a palindrome.
     *
     * The task of finding longest common subsequence is well-known - https://leetcode.com/problems/longest-common-subsequence/,
     * DP approach is usually expected and accepted. Just create reversed string and run the LCS aglorithm.
     * After that N - len(LCS) will be the answer.
     *
     * One catch is - we don't actually need to create reversed copy of the string. It's ok just to iterate from
     * the end. The traversal of the DP array stays the same as in LCS solution, just the index for the second string
     * changes in reversed order - [N-1,...0]
     *
     * O(len(s)^2) time for iteration over dp array that has length == len of string. O(len(s)^2) space - required
     * for dp array.
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[len1][len2];
    }
}
