package dp;

/**
 * 97. Interleaving String
Medium

Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:

    s = s1 + s2 + ... + sn
    t = t1 + t2 + ... + tm
    |n - m| <= 1
    The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...

Note: a + b is the concatenation of strings a and b.

 

Example 1:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true

Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false

Example 3:

Input: s1 = "", s2 = "", s3 = ""
Output: true

 

Constraints:

    0 <= s1.length, s2.length <= 100
    0 <= s3.length <= 200
    s1, s2, and s3 consist of lowercase English letters.

 

Follow up: Could you solve it using only O(s2.length) additional memory space?

https://leetcode.com/problems/interleaving-string/

 */
public class InterleavingString {
    
    int[][] dp;
    
    /**
     * Idea - start with recursive solution, on each step try one char from s1 and one char from s2
     * It's O(2^(m + n + l)) time.
     * Add dp - memorize the result for combination of s1_index, s2_index. s3_index is follow as a sum of s1_idx + s2_idx.
     * O(n*m) time, O(n*m) space
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return helper(s1, 0, s2, 0, s3, 0);
    }
    
    boolean helper(String s1, int p1, String s2, int p2, String s3, int p3) {
        if (p3 == s3.length()) {
            return true;
        }
        
        if (dp[p1][p2] != -1) {
            return dp[p1][p2] == 1;
        }
        
        char nextCh = s3.charAt(p3);
        boolean b1 = false;
        if (p1 < s1.length() && nextCh == s1.charAt(p1)) {
            b1 = helper(s1, p1 + 1, s2, p2, s3, p3 + 1);
        }
        boolean b2 = false;
        if (p2 < s2.length() && nextCh == s2.charAt(p2)) {
            b2 = helper(s1, p1, s2, p2 + 1, s3, p3 + 1);
        }
        boolean res = b1 || b2;
        dp[p1][p2] = res ? 1 : 0;
        return res;
    }
}
