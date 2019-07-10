import java.util.ArrayList;

/**
 * You're given a matrix on 0s and 1s, 0s is land piece and 1 is a river. River parts considered
 * adjacent horizontally or vertically, but not diagonally. Every adjucent piece of a river adds 1
 * to the river length.
 *
 * By analysing the whole map return the list of river lengths. Order doesn't matter
 */
public class RiverSizes {

  /**
   * Idea - just traversing the matrix using DFS. Mark visited river piece as 0 and add 1 to result,
   * return that variable, if it's 0 - return 0. Accumulate overall number and return.
   *
   * @param matrix
   * @return
   */
  public static ArrayList<Integer> riverSizes(int[][] matrix) {
    // Write your code here.
    ArrayList<Integer> res = new ArrayList();

    int rows = matrix.length;
    int cols = matrix[0].length;
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        int l = dfs(matrix, r, c);
        if (l > 0)
          res.add(l);
      }
    }

    return res;
  }

  static int dfs(int[][] matrix, int r, int c) {
    if (matrix[r][c] == 0)
      return 0;

    int l = 0;
    if (matrix[r][c] == 1) {
      matrix[r][c] = 0;
      l++;
    }

    if (r > 0)
      l+=dfs(matrix, r - 1, c);

    if (c > 0)
      l+=dfs(matrix, r, c - 1);

    if (r  < matrix.length - 1)
      l+=dfs(matrix, r + 1, c);

    if (c  < matrix[0].length - 1)
      l+=dfs(matrix, r, c + 1);

    return l;
  }
}
