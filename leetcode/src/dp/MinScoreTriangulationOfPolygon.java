package dp;

/**
 * 1039. Minimum Score Triangulation of Polygon
 * Medium
 *
 * Given N, consider a convex N-sided polygon with vertices labelled A[0], A[i], ..., A[N-1] in
 * clockwise order.
 *
 * Suppose you triangulate the polygon into N-2 triangles.  For each triangle, the value of that
 * triangle is the product of the labels of the vertices, and the total score of the
 * triangulation is the sum of these values over all N-2 triangles in the triangulation.
 *
 * Return the smallest possible total score that you can achieve with some triangulation of the
 * polygon.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: 6
 * Explanation: The polygon is already triangulated, and the score of the only triangle is 6.
 * Example 2:
 *
 *
 *
 * Input: [3,7,4,5]
 * Output: 144
 * Explanation: There are two triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5
 * + 3*4*7 = 144.  The minimum score is 144.
 * Example 3:
 *
 * Input: [1,3,1,4,1,5]
 * Output: 13
 * Explanation: The minimum score triangulation has score 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13.
 *
 *
 * Note:
 *
 * 3 <= A.length <= 50
 * 1 <= A[i] <= 100
 */
public class MinScoreTriangulationOfPolygon {

  /**
   * Use DP. For every triangulation possible we can divide with the triangle, then count min
   * of the triangle + polygon to the left + polygon to the right
   *
   * dp[i][j] is the score of the triangulation from points i to j.
   *
   * cost for (i,j) if we are splitting at k = cost of the triangle (i,j,k) + cost(i,k) + cost(k,j)
   *  from all k-s points select one that gives min cost
   *
   * @param A
   * @return
   */
  public int minScoreTriangulation(int[] A) {
    int N = A.length;
    if (N < 3)
      return 0;

    int[][] dp = new int[N][N];

    for (int gap = 0; gap < N; gap++) {
      for (int i = 0, j = gap; j < N; j++, i++) {
        if (j < i + 2)
          dp[i][j] = 0;
        else {
          dp[i][j] = Integer.MAX_VALUE;
          for (int k = i + 1; k < j; k++) {
            int val = dp[i][k] + dp[k][j] + (A[i]*A[j]*A[k]);
            dp[i][j] = Math.min(dp[i][j], val);
          }
        }
      }
    }
    return dp[0][N - 1];
  }
}
