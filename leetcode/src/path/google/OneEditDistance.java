package path.google;

import java.util.stream.IntStream;

/**
 * 161. One Edit Distance
 *
 * Given two strings s and t, determine if they are both one edit distance apart.
 *
 * Note:
 *
 * There are 3 possiblities to satisify one edit distance apart:
 *
 * Insert a character into s to get t
 * Delete a character from s to get t
 * Replace a character of s to get t
 * Example 1:
 *
 * Input: s = "ab", t = "acb"
 * Output: true
 * Explanation: We can insert 'c' into s to get t.
 * Example 2:
 *
 * Input: s = "cab", t = "ad"
 * Output: false
 * Explanation: We cannot get t from s by only one step.
 * Example 3:
 *
 * Input: s = "1203", t = "1213"
 * Output: true
 * Explanation: We can replace '0' with '1' to get t.
 *
 */
public class OneEditDistance {

    public boolean isOneEditDistance(String s, String t) {
        int sL = s.length();
        int tL = t.length();
        if (Math.abs(sL - tL) >= 2 || s.equals(t)) return false;
        int i = 0, j = 0;
        int editCount = 0;
        while(i < sL && j < tL) {
            if (s.charAt(i) != t.charAt(j)) {
                editCount++;
                if (editCount > 1) return false;
                if (sL == tL) {
                    i++;
                    j++;
                } else if (sL > tL)
                    i++;
                else
                    j++;
            } else {
                i++;
                j++;
            }
        }
        return true;
    }

    public boolean isOneEditDistanceDP(String s, String t) {
        if (s == null || t == null) return false;
        int s1L = s.length() + 1;
        int s2L = t.length() + 1;

        int[][] dp = new int[s1L][s2L];
        //init with length of string - by default this will be edit distance if the other string is 0
        IntStream.range(0, s1L).forEach(i-> dp[i][0] = i);
        IntStream.range(0, s2L).forEach(i-> dp[0][i] = i);
        //
        for (int i = 1; i < s1L; i++) {
            for (int j = 1; j < s2L; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j - 1],
                            Math.min(dp[i][j - 1], dp[i - 1][j])
                    );
                }
            }
        }
        return dp[s1L - 1][s2L - 1] == 1;
    }

    public static void main(String[] args) {
        OneEditDistance obj = new OneEditDistance();
        obj.isOneEditDistance("abcd", "abd");
    }
}
