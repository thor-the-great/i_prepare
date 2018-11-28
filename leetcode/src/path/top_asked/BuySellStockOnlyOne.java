package path.top_asked;

public class BuySellStockOnlyOne {

    public int maxProfit(int[] prices) {
        int N = prices.length;
        if (N <= 1)
            return 0;
        //int l = 0, r = N - 1;
        //return getProfit(prices, l, r);
        int res = 0;
        //int min = prices[0];
        int[] dp = new int[N];
        int tmp = dp[0] - prices[0];
        for (int i = 1; i <= N - 1; i++) {
            //if (prices[i] <= min)
            //else {
            dp[i] = Math.max(dp[i - 1], prices[i] + tmp);
            tmp = Math.max(tmp, dp[i] - prices[i]);
            //}
            //res = Math.max(res, prices[i] - min);
        }
        return dp[N - 1];
    }

    public static void main(String[] args) {
        BuySellStockOnlyOne obj = new BuySellStockOnlyOne();
        System.out.println(obj.maxProfit(new int[]{5, 1, 2, 6, 2, 7, 1}));
    }
}
