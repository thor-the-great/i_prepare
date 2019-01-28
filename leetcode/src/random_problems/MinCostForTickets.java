package random_problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 983. Minimum Cost For Tickets
 * Medium
 *
 * In a country popular for train travel, you have planned some train travelling one year in advance.  The days of
 * the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.
 *
 * Train tickets are sold in 3 different ways:
 *
 * a 1-day pass is sold for costs[0] dollars;
 * a 7-day pass is sold for costs[1] dollars;
 * a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can
 * travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.
 *
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 *
 *
 *
 * Example 1:
 *
 * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
 * Output: 11
 * Explanation:
 * For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
 * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
 * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
 * In total you spent $11 and covered all the days of your travel.
 * Example 2:
 *
 * Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 * Output: 17
 * Explanation:
 * For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
 * On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
 * In total you spent $17 and covered all the days of your travel.
 *
 *
 * Note:
 *
 * 1 <= days.length <= 365
 * 1 <= days[i] <= 365
 * days is in strictly increasing order.
 * costs.length == 3
 * 1 <= costs[i] <= 1000
 */
public class MinCostForTickets {

    /*int[] costs;
    int[] dp;
    Set<Integer> set = new HashSet();

    public int mincostTickets(int[] days, int[] costs) {
        for (int d : days) {
            set.add(d);
        }
        this.costs = costs;
        dp = new int[366];
        Arrays.fill(dp, -1);
        return helper(1);
    }

    int helper(int day) {
        if (day > 365)
            return 0;

        if (dp[day] != -1)
            return dp[day];

        int res = 0;
        if (!set.contains(day)) {
            res = helper(day + 1);
        } else {
            res = Math.min(helper(day + 1) + costs[0],
                    Math.min(
                            helper(day + 7) + costs[1],
                            helper(day + 30) + costs[2]));
        }
        dp[day] = res;
        return res;
    }*/

    int[] days, costs;
    int[] memo;
    int[] durations = new int[]{1, 7, 30};

    /**
     * Idea: use "window" approach - recurrence is following:
     * we only need to buy a travel pass on a day we intend to travel.
     *
     * Now, let dp(i) be the cost to travel from day days[i] to the end of the plan. If say, j1 is the largest index
     * such that days[j1] < days[i] + 1, j7 is the largest index such that days[j7] < days[i] + 7, and j30 is the
     * largest index such that days[j30] < days[i] + 30, then we have:
     *
     * dp(i)=min(dp(j1)+costs[0],dp(j7)+costs[1],dp(j30)+costs[2])
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets(int[] days, int[] costs) {
        this.days = days;
        this.costs = costs;
        memo = new int[days.length];
        Arrays.fill(memo, -1);

        int res = dp(0);
        return res;
    }

    public int dp(int i) {
        if (i >= days.length)
            return 0;
        if (memo[i] != -1)
            return memo[i];

        int ans = Integer.MAX_VALUE;
        int j = i;
        for (int k = 0; k < 3; ++k) {
            while (j < days.length && days[j] < days[i] + durations[k])
                j++;
            ans = Math.min(ans, dp(j) + costs[k]);
        }

        memo[i] = ans;
        return ans;
    }

    public static void main(String[] args) {
        MinCostForTickets obj = new MinCostForTickets();
        int[] days;
        int[] costs;
        days = new int[] {1,4,6,7,8,20};
        costs = new int[] {2,7,15};
        System.out.println(obj.mincostTickets(days, costs));
    }
}
