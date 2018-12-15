package path.linkedin;

/**
 * 256. Paint House
 * Easy
 *
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost
 * of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent
 * houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0]
 * is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and
 * so on... Find the minimum cost to paint all houses.
 *
 * Note:
 * All costs are positive integers.
 *
 * Example:
 *
 * Input: [[17,2,17],[16,16,5],[14,3,19]]
 * Output: 10
 * Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 *              Minimum cost: 2 + 5 + 3 = 10.
 */
public class PaintHouse {
    /**
     * Idea - dyn prog
     * Bottom-up - solution for each type of paint is cost of this type of paint for this house plus min of two other
     * types for previous house. finally solution will be - min of solutions for last house.
     * This leads to formula:
     * Sol_1(X) = Cost_1(X) + Min(Sol_2(X-1), Sol_3(X-1))
     * Fill it for the first building and then start counting down to the last one.
     * In implementation we re-use original array to store results
     * @param costs
     * @return
     */
    public int minCost(int[][] costs) {
        int N = costs.length;
        if ( N == 0)
            return 0;

        for (int i = 1; i < N; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        return Math.min(costs[N-1][0], Math.min(costs[N-1][1], costs[N-1][2]));
    }
}
