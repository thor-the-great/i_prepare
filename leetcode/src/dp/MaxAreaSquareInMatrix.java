package dp;

/**
 * 221. Maximal Square
 * Medium
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's
 * and return its area.
 *
 * Example:
 *
 * Input:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * Output: 4
 */
public class MaxAreaSquareInMatrix {

  /**
   * Use DP for this - iterate and find min of squares based on previously checked ones. Save
   * length of the side. Keep second matrix to save lengths
   * @param matrix
   * @return
   */
    public int maximalSquare(char[][] matrix) {
      int rows = matrix.length;
      if (rows == 0)
        return 0;
      int cols = matrix[0].length;
      int[][] dp = new int[rows + 1][cols + 1];
      int res = 0;
      for (int r = 1; r <= rows; r++) {
        for (int c = 1; c <= cols; c++) {
          if (matrix[r - 1][c - 1] == '1') {
            dp[r][c] = Math.min(dp[r - 1][c],
                Math.min(dp[r][c - 1], dp[r - 1][c - 1])) + 1;
            res = Math.max(res, dp[r][c]);
          }
        }
      }

      return res*res;
    }
}
