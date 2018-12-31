package path.linkedin;

/**
 * 265. Paint House II
 * Hard
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house
 *
 *  with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same
 * color.
 *
 * The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0]
 * is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
 * Find the minimum cost to paint all houses.
 *
 * Note:
 * All costs are positive integers.
 *
 * Example:
 *
 * Input: [[1,5,3],[2,9,4]]
 * Output: 5
 * Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
 *              Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
 * Follow up:
 * Could you solve it in O(nk) runtime?
 */
public class PaintHouseII {

    /**
     * Idea - similar to a 1-st part - for each house for each color cost will be cost for this house/color + min
     * cost of previous houses painted in all colors except for this one.
     * catch: to avoid k loops to track min on the previous row we keep two min variables, so in all case we can find
     * min on previous quickly
     * @param costs
     * @return
     */
    public int minCostII(int[][] costs) {
        if (costs.length == 0)
            return 0;
        int res = 0;
        int n = costs.length;
        int k = costs[0].length;
        int[][] dp = new int[n][k];
        dp[0] = costs[0];

        int min0, min1 = 0;

        for (int nn = 1; nn < n; nn++) {
            min0 = -1;
            min1 = -1;
            for (int kkk = 0; kkk < k; kkk++) {
                if (min0 < 0 || dp[nn - 1][kkk] < dp[nn - 1][min0]) {
                    min1 = min0;
                    min0 = kkk;
                }
                else if (min1 < 0 || dp[nn - 1][kkk] < dp[nn - 1][min1])
                    min1 = kkk;
            }

            for (int kk = 0; kk < k; kk++) {
                int idx = (kk == min0) ? min1 : min0;
                dp[nn][kk] = dp[nn - 1][idx] + costs[nn][kk];
            }
        }
        res = dp[n - 1][0];
        for (int kkk = 1; kkk < k; kkk++) {
            res = Math.min(res, dp[n - 1][kkk]);
        }
        return res;
    }

    public static void main(String[] args) {
        PaintHouseII obj = new PaintHouseII();
        int[][] costs;
        costs = new int[][] {
            {20,19,11,13,12,16,16,17,15,9,5,18},
            {3,8,15,17,19,8,18,3,11,6,7,12},
            {15,4,11,1,18,2,10,9,3,6,4,15}
        };
        System.out.println(obj.minCostII(costs));
    }


}
