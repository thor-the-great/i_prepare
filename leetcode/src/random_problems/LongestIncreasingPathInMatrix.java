package random_problems;

/**
 * 329. Longest Increasing Path in a Matrix
 * Hard
 *
 * Given an integer matrix, find the length of the longest increasing path.
 *
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT
 * move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 *
 * Input: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 * Input: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class LongestIncreasingPathInMatrix {
  /**
   * Do the DFS on the each cell. Save currently longest path in the cache matrix
   * @param matrix
   * @return
   */
  int[][] dir = new int[][] {
      {-1, 0}, {0,1}, {1, 0}, {0, -1}
  };

  public int longestIncreasingPath(int[][] matrix) {
    int rows = matrix.length;
    if (rows == 0)
      return 0;
    int cols = matrix[0].length;
    int[][] memo = new int[rows][cols];
    int res = 0;
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        res = Math.max(res, dfs(matrix, r, c, memo));
      }
    }
    return res;
  }

  int dfs(int[][] matrix, int r, int c, int[][] memo) {
    if (memo[r][c] > 0)
      return memo[r][c];

    for (int[] d : dir) {
      int newR = r + d[0], newC = c + d[1];
      if (newR >= 0 && newC >= 0 && newR < matrix.length && newC < matrix[0].length && matrix[newR][newC] > matrix[r][c]) {
        memo[r][c] = Math.max(memo[r][c], dfs(matrix, newR, newC, memo));
      }
    }
    ++memo[r][c];
    return memo[r][c];
  }
}
