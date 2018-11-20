package path.google;

public class DecodeWays {

    /**
     * Decode Ways
     *   Go to Discuss
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     *
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given a non-empty string containing only digits, determine the total number of ways to decode it.
     *
     * Example 1:
     *
     * Input: "12"
     * Output: 2
     * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
     * Example 2:
     *
     * Input: "226"
     * Output: 3
     * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        //use dyn prog, each state i simulate num of ways to decode string of length i
        if (s.length() == 0)
            return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        if (s.charAt(0) != '0') dp[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            char ch2 = s.charAt(i - 1);
            if (ch2 != '0')
                dp[i] += dp[i - 1];
            char ch1 = s.charAt(i - 2);
            if (ch1 == '1' || (ch1 == '2' && (ch2 - '0') <= 6)) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }

    public int numDecodingsConstSpace(String s) {
        if (s.length() == 0)
            return 0;
        int dp0 = 1;
        int dp1 = 0;
        if (s.charAt(0) != '0') dp1 = 1;
        if (s.length() == 1)
            return dp1;
        int dp = 0;
        for (int i = 2; i <= s.length(); i++) {
            dp = 0;
            char ch2 = s.charAt(i - 1);
            if (ch2 != '0')
                dp += dp1;
            char ch1 = s.charAt(i - 2);
            if (ch1 == '1' || (ch1 == '2' && (ch2 - '0') <= 6)) {
                dp += dp0;
            }
            dp0 = dp1;
            dp1 = dp;
        }
        return dp;
    }
}
