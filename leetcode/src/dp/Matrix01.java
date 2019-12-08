package dp;

import java.util.Arrays;

/**
 * 542. 01 Matrix
 * Medium
 *
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 * Example 2:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,1,1]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,2,1]]
 *
 *
 * Note:
 *
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 */
public class Matrix01 {

    /**
     * DP - do the left-bottom sweep, then right-up and compare the min distance we can have from both approaches
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;

        int[][] res = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            Arrays.fill(res[r], 10_001);
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 0)
                    res[r][c] = 0;
                else {
                    if (r > 0)
                        res[r][c] = res[r - 1][c] + 1;
                    if (c > 0) {
                        res[r][c] = Math.min(res[r][c], res[r][c - 1] + 1);
                    }
                }
            }
        }

        for (int r = rows - 1; r >= 0; r--) {
            for (int c = cols - 1; c >= 0; c--) {
                if (r < rows - 1)
                    res[r][c] = Math.min(res[r][c], res[r + 1][c] + 1);
                if (c < cols - 1) {
                    res[r][c] = Math.min(res[r][c], res[r][c + 1] + 1);
                }
            }
        }

        return res;
    }
}
