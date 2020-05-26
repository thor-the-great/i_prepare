package dp;

/**
 * 1035. Uncrossed Lines
 * Medium
 *
 * We write the integers of A and B (in the order they are given) on two separate horizontal lines.
 *
 * Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:
 *
 * A[i] == B[j];
 * The line we draw does not intersect any other connecting (non-horizontal) line.
 * Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one
 * connecting line.
 *
 * Return the maximum number of connecting lines we can draw in this way.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: A = [1,4,2], B = [1,2,4]
 * Output: 2
 * Explanation: We can draw 2 uncrossed lines as in the diagram.
 * We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line
 * from A[2]=2 to B[1]=2.
 * Example 2:
 *
 * Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
 * Output: 3
 * Example 3:
 *
 * Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
 * Output: 2
 *
 *
 * Note:
 *
 * 1 <= A.length <= 500
 * 1 <= B.length <= 500
 * 1 <= A[i], B[i] <= 2000
 */
public class UncrossedLines {

  /**
   * [1,4,2]
   * [1,2,4]
   *
   * dp
   * [1, 1, 1]
   * [1, 1, 2]
   * [1, 2, 2]
   *
   * 5 [ 2,5,1,2,5]
   * 6 [10,5,2,1,5,2]
   *
   * [0, 0, 1, 1, 1, 1]
   * [0, 1, 1, 1, 2, 2]
   * [0, 0, 0, 2, 2, 2]
   * [0, 0, 1, 2, 2, 3]
   * [0, 1, 1, 2, 3, 3]
   *
   *
   * [1,3,7,1,7,5]
   * [1,9,2,5,1]
   *
   * [1, 0, 0, 0, 1]
   * [1, 0, 0, 0, 1]
   * [1, 0, 0, 0, 1]
   * [1, 0, 0, 0, 1]
   * [1, 1, 1, 1, 1]
   * [1, 1, 1, 2, 2]
   *
   * The Longest Common Subsequence
   * Time O(N^2), Space O(N^2)
   * @param A
   * @param B
   * @return
   */
  public int maxUncrossedLines(int[] A, int[] B) {
    int rows = A.length + 1, cols = B.length + 1;
    int[][] dp = new int[rows][cols];

    for (int r = 1; r < rows; r++) {
      for (int c = 1; c < cols; c++) {
        if (A[r - 1] == B[c - 1]) {
          dp[r][c] = 1 + dp[r - 1][c - 1];
        } else {
          dp[r][c] = Math.max(dp[r - 1][c], dp[r][c - 1]);
        }
      }
    }
    return dp[rows - 1][cols - 1];
  }
}
