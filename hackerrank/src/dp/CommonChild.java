package dp;

/**
 * Common Child
 *
 * A string is said to be a child of a another string if it can be formed by deleting 0 or more characters from the
 * other string. Given two strings of equal length, what's the longest string that can be constructed such that it is
 * a child of both?
 *
 * For example, ABCD and ABDC have two children with maximum length 3, ABC and ABD. They can be formed by eliminating
 * either the D or C from both strings. Note that we will not consider ABCD as a common child because we can't
 * rearrange characters and ABCD  ABDC.
 *
 * Function Description
 *
 * Complete the commonChild function in the editor below. It should return the longest string which is a common child
 * of the input strings.
 *
 * commonChild has the following parameter(s):
 *
 * s1, s2: two equal length strings
 * Input Format
 *
 * There is one line with two space-separated strings,  and .
 *
 * Constraints
 *
 * All characters are upper case in the range ascii[A-Z].
 * Output Format
 *
 * Print the length of the longest string , such that  is a child of both  and .
 *
 * Sample Input
 *
 * HARRY
 * SALLY
 * Sample Output
 *
 *  2
 * Explanation
 *
 * The longest string that can be formed by deleting zero or more characters from  and  is , whose length is 2.
 *
 * Sample Input 1
 *
 * AA
 * BB
 * Sample Output 1
 *
 * 0
 * Explanation 1
 *
 *  and  have no characters in common and hence the output is 0.
 *
 * Sample Input 2
 *
 * SHINCHAN
 * NOHARAAA
 * Sample Output 2
 *
 * 3
 * Explanation 2
 *
 * The longest string that can be formed between  and  while maintaining the order is .
 *
 * Sample Input 3
 *
 * ABCDEF
 * FBDAMN
 * Sample Output 3
 *
 * 2
 * Explanation 3
 *  is the longest child of the given strings.
 */
public class CommonChild {

    /**
     * Similar to knapsack problem - dp[i][j] is the longest subsequence ending at i of s1 and j of s2. At every step
     * we either take max of previous (dp[i - 1][j] or dp[i][j - 1]) or if chars at s1[i] == s2[j] take
     * dp[i-1][j-1] + 1
     * The answer will be at dp[len1 - 1][len2 - 1]
     */
    static int commonChild(String s1, String s2) {
        int l1 = s1.length(), l2 = s2.length();
        int[][] dp = new int[l1][l2];

        for (int i = 0; i < l1; i++) {
            if (i > 0 && dp[i - 1][0] == 1)
                dp[i][0] = 1;
            else if (s2.charAt(0) == s1.charAt(i))
                dp[i][0] = 1;
        }

        for (int i = 0; i < l2; i++) {
            if (i > 0 && dp[0][i - 1] == 1)
                dp[0][i] = 1;
            else if (s1.charAt(0) == s2.charAt(i))
                dp[0][i] = 1;
        }

        for (int r = 1; r < l1; r++) {
            for (int c = 1; c < l2; c++) {
                dp[r][c] = Math.max(
                        Math.max(dp[r][c - 1], dp[r - 1][c]),
                        dp[r - 1][c - 1] +  (s1.charAt(r) == s2.charAt(c) ? 1 : 0));
            }
        }
        return dp[l1 - 1][l2 - 1];
    }
}
