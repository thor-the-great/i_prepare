/**
 * We have a grid on m x n size. We can move only right or down. Count the number of possible
 * paths that exist in the grid
 */
public class CountAllPaths {

  /**
   * Use DP.
   * If we sweep from top-left to bot-right corner for every next cell the number of paths we can
   * there is is sum of paths from the cell 1 square higher and one cell to the left:
   * p[r][c] = p[r - 1][c] + p[r][c - 1]
   * We can fill top row and left column - every cell there will have only 1 possible path.
   * Start sweeping from the second row/col, calculate paths for each cell as we go.
   * Final answer will be in the [m-1][n-1] cell
   * @param m
   * @param n
   * @return
   */
  public int countPaths(int m, int n){
    if (m <= 0 || n<=0)
      return 0;
    int[][] dp = new int[m][n];
    for (int r = 0; r < m; r++) {
      dp[r][0] = 1;
    }

    for (int c = 0; c < n; c++) {
      dp[0][c] = 1;
    }

    for (int r = 1; r < m; r++) {
      for (int c = 1; c < n; c++) {
        dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
      }
    }

    return dp[m - 1][n -1];
  }
}
