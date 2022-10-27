package dp;

/**
 * 1937. Maximum Number of Points with Cost
 * Medium
 *
 * You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.
 *
 * To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.
 *
 * However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.
 *
 * Return the maximum number of points you can achieve.
 *
 * abs(x) is defined as:
 *
 *     x for x >= 0.
 *     -x for x < 0.
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,2,3],[1,5,1],[3,1,1]]
 * Output: 9
 * Explanation:
 * The blue cells denote the optimal cells to pick, which have coordinates (0, 2), (1, 1), and (2, 0).
 * You add 3 + 5 + 3 = 11 to your score.
 * However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
 * Your final score is 11 - 2 = 9.
 *
 * Example 2:
 *
 * Input: points = [[1,5],[2,3],[4,2]]
 * Output: 11
 * Explanation:
 * The blue cells denote the optimal cells to pick, which have coordinates (0, 1), (1, 1), and (2, 0).
 * You add 5 + 3 + 4 = 12 to your score.
 * However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
 * Your final score is 12 - 1 = 11.
 *
 *
 *
 * Constraints:
 *
 *     m == points.length
 *     n == points[r].length
 *     1 <= m, n <= 105
 *     1 <= m * n <= 105
 *     0 <= points[r][c] <= 105
 */
public class MaximumNumberofPointswithCost {

    /**
     * DP approach
     * Start with dp[i,j] as max of points we get if go from 0,0 to i,j.
     * for each i,j we check point[i,j] and all dp from row i-1 including cost. this will be O(n^3) solution as
     * for each [i, j] we need to check all dp[i -1,0] - dp[i -1, cols - 1]. We can optimize it, for cell [i,j]
     * optimal previous cell can be either to the right or to the left of it.
     * Left : we start from dp [i -1, 0], for left[1] it's max(left[0] - 1, dp[i-1, 1]) etc. Same for right
     * At the end for each dp[i, j]  it's points[i, j] + Max(left[j], right[j])
     * Overall O(n^2) solution, O(n) space
     *
     * @param points
     * @return
     */
    public long maxPoints(int[][] points) {
        long res = 0;
        int rows = points.length, cols = points[0].length;

        long[] prev = new long[cols];
        for (int c = 0; c < cols; c++) {
            prev[c] = points[0][c];
        }


        for (int r = 1; r < rows; r++) {
            long[] dp = new long[cols], left = new long[cols], right = new long[cols];
            left[0] = prev[0];
            right[cols - 1] = prev[cols - 1];
            for (int c = 1; c < cols; c++) {
                left[c] = Math.max(left[c - 1] - 1, prev[c]);
            }
            for (int c = cols - 2; c >= 0; c--) {
                right[c] = Math.max(right[c + 1] - 1, prev[c]);
            }
            for (int c = 0; c < cols; c++) {
                dp[c] = points[r][c] + Math.max(left[c], right[c]);
            }
            prev = dp;
        }

        res = prev[0];
        for (int c = 1; c < cols; c++) {
            res = Math.max(res, prev[c]);
        }
        return res;
    }
}
