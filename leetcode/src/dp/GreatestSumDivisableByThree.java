package dp;

public class GreatestSumDivisableByThree {

    /**
     * DP approach. The characteristic of the sum that we care about is mod 3. So starting from the last element
     * take the previous sum for this modulo, add next array element and do modulo 3 again. Save the max between
     * previous sum same modulo and this one
     * Result will be in the dp[0][0] - max sum for modulo 0 - meaning divisible by 3.
     * @param nums
     * @return
     */
    public int maxSumDivThree(int[] nums) {
        int N = nums.length;
        //create array i,j will be max sum from i to end with modulo 3 == j
        int[][] dp = new int[N][3];
        //init with the last array element
        dp[N - 1][nums[N - 1] % 3] = nums[N - 1];
        //starting from the second from the end element do the dp loop
        for (int i = N - 2; i >= 0; i--) {
            //copy max sums from the previous step
            for (int j =0; j < 3; j++)
                dp[i][j] = dp[i +1][j];
            //for each modulo 3 value
            for (int j = 0; j < 3; j++) {
                //get previous sum for modulo j and add nums[i]
                int s = dp[i + 1][j] + nums[i];
                //then put to the s % 3 element max between s and the sum for the same modulo
                //from the previous step
                dp[i][s % 3] = Math.max(s, dp[i][s % 3]);
            }
        }
        //result always be in 0,0 - max sum with modulo 3
        return dp[0][0];
    }
}
