package path.top_asked;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

public class BuySellStockOnlyTwo {
    int[] prices;

    public int maxProfit(int[] prices) {
        //return maxProfitDP(prices);
        return maxProfitDP2(prices);
    }

    public int maxProfitDP(int[] prices) {
        int N = prices.length;
        if (N <= 1)
            return 0;
        int res = 0;
        int KK = 2;
        int[][] dp = new int[N][KK];

        for (int k = 0; k < KK; k++) {
            int t = (k == 0 ? 0 : dp[0][k - 1]) - prices[0];
            for (int i = 1; i < N; i++) {
                dp[i][k] = Math.max(dp[i - 1][k], prices[i] + t);
                t = Math.max(t, (k == 0 ? 0 : dp[i][k - 1]) - prices[i]);
                res = Math.max(res, dp[i][k]);
            }
        }
        return res;
    }

    public int maxProfitDP2(int[] prices) {
        int N = prices.length;
        if (N <= 1)
            return 0;

        int[] dp = new int[N + 1];
        int min = prices[0];
        for (int i = 2; i < N; i++) {
            dp[i] = Math.max(dp[i - 1], prices[i - 1] - min);
            min = Math.min(min, prices[i - 1]);
        }

        int max = prices[N - 1];
        int res = Math.max(dp[N], dp[N - 1]);
        for (int i = N - 2; i >=0; i--) {
            dp[i] += Math.max(0, max - prices[i]);
            max = Math.max(max, prices[i]);
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    public int maxProfitArrayDivider(int[] prices) {
        this.prices = prices;
        int N = prices.length;
        if (N <= 1)
            return 0;
        int max = 0;
        for (int div = 1; div <= N - 1; div++) {
            int prof1 = getProfit(0, div);
            int prof2 = getProfit(div + 1, N - 1);
            max = Math.max(max, prof1 + prof2);
        }
        return max;
    }

    int getProfit(int l, int r) {
        if (r - l < 1)
            return 0;
        int res = 0;
        int min = prices[l];
        for (int i = l + 1; i <= r; i++) {
            if (prices[i] <= min)
                min = prices[i];
            else
                res = Math.max(res, prices[i] - min);
        }
        return res;
    }

    public static void main(String[] args) {
        BuySellStockOnlyTwo obj = new BuySellStockOnlyTwo();
        System.out.println(obj.maxProfit(new int[] {3,3,5,0,0,3,1,4}));
        System.out.println(obj.maxProfit(new int[] {1,2}));
    }
}
