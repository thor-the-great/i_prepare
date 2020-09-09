package arrays;

/**
 * 1572. Matrix Diagonal Sum
 * Easy
 *
 * Given a square matrix mat, return the sum of the matrix diagonals.
 *
 * Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[1,2,3],
 *               [4,5,6],
 *               [7,8,9]]
 * Output: 25
 * Explanation: Diagonals sum: 1 + 5 + 9 + 3 + 7 = 25
 * Notice that element mat[1][1] = 5 is counted only once.
 * Example 2:
 *
 * Input: mat = [[1,1,1,1],
 *               [1,1,1,1],
 *               [1,1,1,1],
 *               [1,1,1,1]]
 * Output: 8
 * Example 3:
 *
 * Input: mat = [[5]]
 * Output: 5
 *
 *
 * Constraints:
 *
 * n == mat.length == mat[i].length
 * 1 <= n <= 100
 * 1 <= mat[i][j] <= 100
 */
public class MatrixDiagonalSum {

  /**
   * going by column, for each one sum two rows that are part of both diagonals
   * @param mat
   * @return
   */
  public int diagonalSum(int[][] mat) {
    int N = mat.length, res = 0;
    for (int i = 0; i < N; i++) {
      res += mat[i][i];
      res += mat[N - 1 - i][i];
    }
    //check if this is a special case and N is even. If so we counted middle element twice and need to compensate
    //this by subtracting fro mthe res sum
    if (N % 2 == 1)
      res -= mat[N/2][N/2];
    return res;
  }
}
