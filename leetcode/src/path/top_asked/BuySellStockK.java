package path.top_asked;

import java.util.Arrays;

public class BuySellStockK {

    public int maxProfit(int k, int[] prices) {
        int N = prices.length;
        if (N <= 1) return 0;
        int MAX = 2*k;
        if (MAX >= N) {
            //this case we can do max allowed transactions anddo max profit
            int s = 0;
            for (int i = 1; i < N; i++) {
                if (prices[i] > prices[i - 1])
                    s += prices[i] - prices[i - 1];
            }
            return s;
        }

        int[] dp = new int[MAX + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < Math.min(i + 1, MAX) + 1; j++) {
                if (j % 2 == 1)
                    dp[j] = Math.max(dp[j], dp[j - 1] - prices[i]);
                else
                    dp[j] = Math.max(dp[j], dp[j - 1] + prices[i]);
            }
        }
        return dp[MAX];
    }

    public int maxProfit2(int k, int[] prices) {
        int N = prices.length;
        if (N <= 1 || k == 0) return 0;
        if (2*k >= N) {
            //this case we can do max allowed transactions and do max profit
            int s = 0;
            for (int i = 1; i < N; i++) {
                if (prices[i] > prices[i - 1])
                    s += prices[i] - prices[i - 1];
            }
            return s;
        }

        int[][] dp = new int[k][N];
        for (int kk = 0; kk < k; kk++) {
            int localMax = (kk == 0 ? 0 : dp[kk - 1][0]) - prices[0];
            for (int i = 1; i < N; i++) {
                dp[kk][i] = Math.max(dp[kk][i - 1],  prices[i] + localMax);
                localMax = Math.max(localMax, (kk == 0 ? 0 : dp[kk - 1][i]) - prices[i]);
            }
        }
        return dp[k - 1][N - 1];
    }

    public static void main(String[]  args) {

        BuySellStockK obj = new BuySellStockK();
        int[] p;
        int k;

        p = new int[]{1, 4, 3, 2, 7};
        k = 2;
        System.out.println(obj.maxProfit2(k, p));
    }
}
