package dp;

/**
 * 2266. Count Number of Texts
Medium

Alice is texting Bob using her phone. The mapping of digits to letters is shown in the figure below.

In order to add a letter, Alice has to press the key of the corresponding digit i times, where i is the position of the letter in the key.

    For example, to add the letter 's', Alice has to press '7' four times. Similarly, to add the letter 'k', Alice has to press '5' twice.
    Note that the digits '0' and '1' do not map to any letters, so Alice does not use them.

However, due to an error in transmission, Bob did not receive Alice's text message but received a string of pressed keys instead.

    For example, when Alice sent the message "bob", Bob received the string "2266622".

Given a string pressedKeys representing the string received by Bob, return the total number of possible text messages Alice could have sent.

Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: pressedKeys = "22233"
Output: 8
Explanation:
The possible text messages Alice could have sent are:
"aaadd", "abdd", "badd", "cdd", "aaae", "abe", "bae", and "ce".
Since there are 8 possible messages, we return 8.

Example 2:

Input: pressedKeys = "222222222222222222222222222222222222"
Output: 82876089
Explanation:
There are 2082876103 possible text messages Alice could have sent.
Since we need to return the answer modulo 109 + 7, we return 2082876103 % (109 + 7) = 82876089.

 

Constraints:

    1 <= pressedKeys.length <= 105
    pressedKeys only consists of digits from '2' - '9'.

 */
public class CountNumberOfTexts {
    
    int res = 0;
    int[] codes = new int[] {
        0, 0, 3, 3, 3, 3, 3, 4, 3, 4
    };
    int mod = 1_000_000_007;
    
    /**
     * Start with recursive apporach, slowly work to DP
     */
    public int countTextsDPBottomUp(String pressedKeys) {
        int N  = pressedKeys.length();
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int idx = N - 1; idx >=0; idx--) {
            int code = codes[pressedKeys.charAt(idx) - '0'];
            int res  = 0;
            for (int i = idx; i < idx + code && i < N && pressedKeys.charAt(idx) == pressedKeys.charAt(i); i++) {
                res += dp[i + 1];
                res%=mod;
            }
            dp[idx] = res; 
        }
        
        return dp[0];
    }

    public int countTextsDPTopDown(String pressedKeys) {
        int[] dp = new int[pressedKeys.length()];
        return helper(0, pressedKeys, dp);
    }
    
    int helper(int idx, String s, int[] dp) {
        if (s.length() == idx) {
            return 1;
        }
        if (dp[idx] != 0) {
            return dp[idx];
        }
        int code = codes[s.charAt(idx) - '0'];
        int res = 0;
        for (int i = idx; i < idx + code && i < s.length() && s.charAt(idx) == s.charAt(i); i++) {
            res += helper(i + 1, s, dp);
            res%=mod;
        }
        dp[idx] = res;
        return res;
    }

    public int countTextsRecursive(String pressedKeys) {
        return helper(0, pressedKeys);
    }
    
    int helper(int idx, String s) {
        if (s.length() == idx) {
            return 1;
        }
        
        int code = codes[s.charAt(idx) - '0'];
        int res = 0;
        for (int i = idx; i < idx + code && i < s.length() && s.charAt(idx) == s.charAt(i); i++) {
            res += helper(i + 1, s);
            res%=mod;
        }
        return res;
    }
}
