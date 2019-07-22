package dp;

/**
 * 746. Min Cost Climbing Stairs
 * Easy
 *
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 *
 * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of
 * the floor, and you can either start from the step with index 0, or the step with index 1.
 *
 * Example 1:
 * Input: cost = [10, 15, 20]
 * Output: 15
 * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
 * Example 2:
 * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * Output: 6
 * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 * Note:
 * cost will have a length in the range [2, 1000].
 * Every cost[i] will be an integer in the range [0, 999].
 */
public class MinCostClimbingStairs {

    /**
     * On every step we count cost as cost[i] + Min(cost[i - 1], cost[i - 2])
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int N = cost.length;
        int res = 0;
        for (int i = N - 1; i >= 0; i--) {
            int prev = 0;
            if (i + 1 < N )
                prev = cost[i + 1];
            int prevPrev = 0;
            if (i + 2 < N )
                prevPrev = cost[i + 2];
            cost[i] = cost[i] + Math.min(prev, prevPrev);
        }

        return Math.min(cost[0], cost[1]);
    }
}
