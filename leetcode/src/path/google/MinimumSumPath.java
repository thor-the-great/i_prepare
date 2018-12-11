package path.google;

/**
 * 64. Minimum Path Sum
 * Medium
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example:
 *
 * Input:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 *
 */
public class MinimumSumPath {
    /**
     * Idea is to use dp, initially - 2D, then collapsed to 1D
     * think of it bottom-up - every final solution is:
     * min (F(i - 1, j), F(i, j - 1)) + grid[i][j]
     * pre-fill row 0 and column 0 by the grid values (no way we can do better than those values)
     * then start sweeping every row left to right - in every case previous steps is filled so we go up to the
     * last cell. finally last dp element will have a solution.
     *
     * Then next optimization is to switch from 2D to 1D. On every step we just need one previous row, so keep only one
     * from previous grid row.
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        if (rows == 0) return 0;
        int cols = grid[0].length;

        int[] dp = new int[cols];
        dp[0] = grid[0][0];
        for (int c = 1; c < cols; c++) {
            dp[c] = dp[c - 1] + grid[0][c];
        }

        for (int r = 1; r < rows; r++) {
            dp[0] = dp[0] + grid[r][0];
            for (int c = 1; c < cols; c++) {
                dp[c] = Math.min(dp[c], dp[c - 1]) + grid[r][c];
            }
        }
        return dp[cols - 1];
    }
}
