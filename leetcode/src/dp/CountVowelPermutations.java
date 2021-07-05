package dp;

public class CountVowelPermutations {
    
    /**
     * Idea: use dp, count at every step dp[i][j] = string of len i with last char j.
     * Create table of possible char from to transition.
     * 
     * Start from O(i*j) space, optimize to O(5) space
     */
    public int countVowelPermutation(int n) {
        int mod = 1_000_000_007;
        long[] dp = new long[5];
        for (int i = 0; i < 5; i++) {
            dp[i] = 1;
        }

        /*        
    Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
    'a' - 0 
    'e' - 1
    'i' - 2
    'o' - 3
    'u' - 4
    
    
    a -> e
    e -> a, i
    i -> a, e, o, u
    o -> i, u
    u -> a
    
    a - e,i,u
    e - a, i
    i - e,o
    o - i
    u - i,o
    
        */
        
        for (int j = 1; j < n; j++) {
            long[] tmp = new long[5];
            tmp[0] = (dp[1] + dp[2] + dp[4])%mod;
            tmp[1] = (dp[0] + dp[2])%mod;
            tmp[2] = (dp[1] + dp[3])%mod;
            tmp[3] = dp[2];
            tmp[4] = (dp[2] + dp[3])%mod;
            dp = tmp;
        }
        int res = 0;
        for (int i = 0; i < 5; i++) {
            res += dp[i];
            res=res%mod;
        }
        return res;
    }
}
