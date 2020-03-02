package grooking_coding_patterns.dp;

/**
 * Problem Challenge 2
 * We'll cover the following
 * Target Sum (hard) *
 * Example 1:
 * Example 2:
 * Try it yourself
 * Target Sum (hard) #
 * You are given a set of positive numbers and a target sum ‘S’. Each number should be assigned either a ‘+’ or ‘-’
 * sign. We need to find the total ways to assign symbols to make the sum of the numbers equal to the target ‘S’.
 *
 * Example 1: #
 * Input: {1, 1, 2, 3}, S=1
 * Output: 3
 * Explanation: The given set has '3' ways to make a sum of '1': {+1-1-2+3} & {-1+1-2+3} & {+1+1+2-3}
 * Example 2: #
 * Input: {1, 2, 7, 1}, S=9
 * Output: 2
 * Explanation: The given set has '2' ways to make a sum of '9': {+1+2+7-1} & {-1+2+7+1}
 */
public class TargetSum {

    /**
     * Idea:
     * possible_sum - s1 - s2;
     * s1 + s2 = sum
     * s1 - s2 = s;
     * 2s1 = sum + s
     * s1 = (sum + s)/2
     *
     * we need to find subsets of sum = (sum + s)/2
     * @param num
     * @param s
     * @return
     */
    public int findTargetSubsets(int[] num, int s) {
        int sum = 0;
        for (int n : num)
            sum+=n;

        sum = (sum + s)/2;

        int[][] dp = new int[num.length][sum + 1];

        dp[0][0] = 1;
        if (sum-num[0] >= 0)
            dp[0][num[0]] = 1;

        for (int i = 1; i < num.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num[i])
                    dp[i][j] += dp[i - 1][j - num[i]];
            }
        }

        return dp[num.length - 1][sum];
    }


    public static void main(String[] args) {
        TargetSum ts = new TargetSum();
        int[] num = {1, 1, 2, 3};
        System.out.println(ts.findTargetSubsets(num, 1));
        num = new int[]{1, 2, 7, 1};
        System.out.println(ts.findTargetSubsets(num, 9));
    }
}
