/**
 * 583. Delete Operation for Two Strings
Medium

Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.


Example 1:

Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Example 2:

Input: word1 = "leetcode", word2 = "etco"
Output: 4
 

Constraints:

1 <= word1.length, word2.length <= 500
word1 and word2 consist of only lowercase English letters.

https://leetcode.com/problems/delete-operation-for-two-strings/
 */
public class DeleteOperationforTwoStrings {
    
    /**
     * DP approach.
     * First fist longest common sunsequence. Then everything else in both strings has to be removed, so in num of chars:
     * 
     * NUM_CHARS_TO_REMOVE = M + N - 2*LCS
     * 
     * LCS can be calculated with dp in O(M*N) time
     * 
     * O(M*N) time
     * O(M*N) space
     * 
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int N1 = word1.length(), N2 = word2.length();
        
        int[][] dp = new int[N1+1][N2+1];
        
        for (int i1 = 1; i1 <= N1; i1++) {
            for (int i2 = 1; i2 <= N2; i2++) {
                if (word1.charAt(i1 - 1) == word2.charAt(i2 - 1)) {
                    dp[i1][i2] = dp[i1 - 1][i2 - 1] + 1;
                } else {
                    dp[i1][i2] = Math.max(dp[i1 - 1][i2], dp[i1][i2 - 1]);
                }
            }
        }
        
        int lcs = dp[N1][N2];
        return N1 + N2 - 2*lcs;
    }
}
