package path.linkedin;

/**
 * 730. Count Different Palindromic Subsequences
 * Hard
 *
 * Given a string S, find the number of different non-empty palindromic subsequences in S, and return that number
 * modulo 10^9 + 7.
 *
 * A subsequence of a string S is obtained by deleting 0 or more characters from S.
 *
 * A sequence is palindromic if it is equal to the sequence reversed.
 *
 * Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.
 *
 * Example 1:
 * Input:
 * S = 'bccb'
 * Output: 6
 * Explanation:
 * The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
 * Note that 'bcb' is counted only once, even though it occurs twice.
 * Example 2:
 * Input:
 * S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
 * Output: 104860361
 * Explanation:
 * There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
 * Note:
 *
 * The length of S will be in the range [1, 1000].
 * Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.
 */
public class CountDifferentPalindromicSubsequences {

    /**
     * Intuition and Algorithm
     *
     * Let dp[x][i][j] be the answer for the substring S[i...j] where S[i] == S[j] == 'a'+x. Note that since we only
     * have 4 characters a, b, c, d, thus 0 <= x < 4. The DP formula goes as follows:
     *
     * If S[i] != 'a'+x, then dp[x][i][j] = dp[x][i+1][j], note that here we leave the first character S[i] in the
     * window out due to our definition of dp[x][i][j].
     *
     * If S[j] != 'a'+x, then dp[x][i][j] = dp[x][i][j-1], leaving the last character S[j] out.
     *
     * If S[i] == S[j] == 'a'+x, then dp[x][i][j] = 2 + dp[0][i+1][j-1] + dp[1][i+1][j-1] + dp[2][i+1][j-1]
     * + dp[3][i+1][j-1]. When the first and last characters are the same, we need to count all the distinct
     * palindromes (for each of a,b,c,d) within the sub-window S[i+1][j-1] plus the 2 palindromes contributed by the
     * first and last characters.
     *
     * Let n be the length of the input string S, The final answer would be dp[0][0][n-1] + dp[1][0][n-1] +
     * dp[2][0][n-1] + dp[3][0][n-1] mod 1000000007.
     *
     * @param S
     * @return
     */
    public int countPalindromicSubsequences(String S) {
        int N = S.length();
        if (N ==0)
            return 0;
        int mod = 1_000_000_007;

        int[][][] dp = new int[4][N][N];

        for (int i = N - 1; i >= 0; i--) {
            for (int j = i; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    char ch = (char) ('a' + k);
                    if (i == j) {
                        if (S.charAt(i) == ch)
                            dp[k][i][j] = 1;
                        else
                            dp[k][i][j] = 0;
                    } else {
                        if (S.charAt(i) != ch)
                            dp[k][i][j] = dp[k][i + 1][j];
                        else if (S.charAt(j) != ch)
                            dp[k][i][j] = dp[k][i][j - 1];
                        else { //s[i] == s[j] == ch
                            if (j == i + 1) //if there are only 2 characters
                                //it means only 2 pali possible
                                dp[k][i][j] = 2;
                            else {
                                dp[k][i][j] = 2;
                                for (int kk = 0; kk < 4; kk++) {
                                    dp[k][i][j] += dp[kk][i + 1][j - 1];
                                    dp[k][i][j] %= mod;
                                }
                            }
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int kk = 0; kk < 4; kk++) {
            res += dp[kk][0][N - 1];
            res %= mod;
        }
        return res;
    }
}
