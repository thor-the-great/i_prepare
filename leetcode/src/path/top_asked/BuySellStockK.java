package path.top_asked;

import java.util.Arrays;

public class BuySellStockK {

    /**
     * At every point we get either sell or not sell.
     * If not sell - take the profit from same k transactions but previous day
     * If sell - get prices[d] - (some price of [d1 ... d - 1] + profit [0..d1])
     * we need to maximize that (some price of [d1 ... d - 1] + profit [0..d1])
     * @param k
     * @param prices
     * @return
     */
    public int maxProfitExplainedDP(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k <= 0 )
            return 0;
        int N = prices.length;
        if (2*k > N) {
            int max = 0;
            for (int i = 1; i < N; i++) {
                int dif = prices[i] - prices[i - 1];
                if (dif > 0)
                    max += dif;
            }
            return max;
        }
        int[] odd = new int[N];
        int[] even = new int[N];
        int[] curProf; int[] prevProf;
        for (int t = 1; t <= k; t++) {
            int maxCur = Integer.MIN_VALUE;
            curProf = (t % 2 == 1) ? odd : even;
            prevProf = (t % 2 == 1) ? even : odd;
            for (int d = 1; d < N; d++) {
                maxCur = Math.max(maxCur, prevProf[d - 1] - prices[d - 1]);
                curProf[d] = Math.max(curProf[d - 1], prices[d] + maxCur);
            }
        }
        return (k % 2 == 1) ? odd[N - 1] : even[N - 1];
    }

    public int maxProfit1(int k, int[] prices) {
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

    public int maxProfit3(int k, int[] prices) {
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
        int[][] dp = new int[N][k+1];
        /*for (int[] d : dp)
           Arrays.fill(d, Integer.MIN_VALUE);

        for (int i = 0; i <= k; i++)
            dp[0][i] = 0;
        for (int i = 0; i <N; i++)
            dp[i][0] = 0;*/

        int maxSoFar;
        for (int kk = 1; kk <= k; kk++) {
            maxSoFar = Integer.MIN_VALUE;
            for (int i = 1; i < N; i++) {
                maxSoFar = Math.max(maxSoFar, dp[i - 1][kk - 1] - prices[i - 1]);
                dp[i][kk] = Math.max(maxSoFar + prices[i], dp[i - 1][kk]);
            }
        }
        return dp[N -1][k];
    }

    public int maxProfit4(int k, int[] prices) {
        int N = prices.length;
        if (N <= 1) return 0;
        if (2*k > N) {
            int res = 0;
            for (int i = 1; i < N; i++) {
                int dif = prices[i] - prices[i - 1];
                if (dif > 0)
                    res += dif;
            }
            return res;
        }

        int[][] dp = new int[k + 1][N];
        for (int kk = 1; kk <= k; kk++) {
            int tmp = Integer.MIN_VALUE;
            for (int i = 1; i < N; i++) {
                tmp = Math.max(tmp, dp[kk - 1][i - 1] - prices[i - 1]);
                dp[kk][i] = Math.max(tmp + prices[i], dp[kk][i - 1]);
            }
        }
        return dp[k][N - 1];
    }

    /**
     * This is optimized dp in terms of memory - use only 2*N space instead of N*k. In fact we need only
     * current line of profits and the previous one to compare - don't need all k at once
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit5DPOpt(int k, int[] prices) {
        int N = prices.length;
        if (N <= 1) return 0;
        if (2*k > N) {
            int res = 0;
            for (int i = 1; i < N; i++) {
                int dif = prices[i] - prices[i - 1];
                if (dif > 0)
                    res += dif;
            }
            return res;
        }

        int[][] dp = new int[2][N];
        for (int kk = 0; kk < k; kk++) {
            int tmp = Integer.MIN_VALUE;
            for (int i = 1; i < N; i++) {
                tmp = Math.max(tmp, dp[0][i - 1] - prices[i - 1]);
                dp[1][i] = Math.max(tmp + prices[i], dp[1][i - 1]);
            }
            dp[0] = Arrays.copyOf(dp[1], N);
        }
        return dp[0][N - 1];
    }

    public int maxProfit(int k, int[] prices) {
        return maxProfit5DPOpt(k, prices);
    }

    public static void main(String[]  args) {

        BuySellStockK obj = new BuySellStockK();
        int[] p;
        int k;

        p = new int[]{1, 4, 3, 2, 7};
        k = 2;
        System.out.println(obj.maxProfit(k, p));

        p = new int[]{1, 3, 1, 4, 1, 5, 7, 8};
        k = 2;
        System.out.println(obj.maxProfit(k, p));

        p = new int[]{1, 4, 3, 2, 7};
        k = 20;
        System.out.println(obj.maxProfit(k, p));

        p = new int[]{1, 3, 1, 4, 1, 5, 7, 8};
        k = 20;
        System.out.println(obj.maxProfit(k, p));

        p = new int[]{1, 2};
        k = 2;
        System.out.println(obj.maxProfit(k, p));
    }
}
