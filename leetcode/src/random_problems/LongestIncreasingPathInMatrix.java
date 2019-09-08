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

  static int[][] dir = new int [][] {
      {1, 0}, {-1, 0}, {0, 1}, {0, -1}
  };

  /**
   * Do the DFS on the each cell. Save currently longest path in the cache matrix
   * @param matrix
   * @return
   */
  public int longestIncreasingPath(int[][] matrix) {
    int max = 0;
    int rows = matrix.length;
    if ( rows == 0)
      return max;
    int cols = matrix[0].length;

    int[][] cache = new int[rows][cols];

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        int m = helper(r, c, matrix, cache);
        max = Math.max(max, m);
      }
    }

    return max;
  }

  int helper(int r, int c, int[][] matrix, int[][] cache) {

    if (cache[r][c] > 0) {
      return cache[r][c];
    }
    int next = -1;
    for ( int d = 0; d < 4; d++) {
      int rr = r + dir[d][0];
      int cc = c + dir[d][1];

      if (rr >= 0 && cc >= 0
          && rr < matrix.length && cc < matrix[0].length
          && matrix[rr][cc] > matrix[r][c]) {
        next = Math.max(next, helper(rr, cc, matrix, cache));
      }
    }

    cache[r][c] = Math.max(cache[r][c], next);
    return ++cache[r][c];
  }
}
