package random_problems;

/**
 * 714. Best Time to Buy and Sell Stock with Transaction Fee
 * Medium
 *
 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and
 * a non-negative integer fee representing a transaction fee.
 *
 * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 * You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
 *
 * Return the maximum profit you can make.
 *
 * Example 1:
 * Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * Buying at prices[0] = 1
 * Selling at prices[3] = 8
 * Buying at prices[4] = 4
 * Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * Note:
 *
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 *
 */
public class BestTimeToSellTransactionFee {

    /**
     * Idea: hold is profit we have if we own the stock but not sell, and sell - profit if we sell
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int N = prices.length;
        if (N <= 1)
            return 0;
        int sell = 0;
        int hold = -prices[0];
        for (int i = 1; i < N; i++) {
            sell = Math.max(hold + prices[i] - fee, sell);
            hold = Math.max(hold, sell - prices[i]);
        }

        return sell;
    }

    public int maxProfitGoingToOptimal(int[] prices, int fee) {
        int N = prices.length;
        int[] buys = new int[N], sells = new int[N];
        buys[0] = prices[0]; sells[0] = 0;
        for (int i = 1; i < N; i++) {
            buys[i] = Math.min(buys[i - 1], prices[i] - sells[i - 1]);
            sells[i] = Math.max(sells[i - 1], prices[i] - buys[i - 1] - fee);
        }
        return sells[N - 1];
    }
}
