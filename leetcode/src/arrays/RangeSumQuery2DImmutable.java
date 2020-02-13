package arrays;

/**
 * 304. Range Sum Query 2D - Immutable
 * Medium
 *
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner
 * (row1, col1) and lower right corner (row2, col2).
 *
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which
 * contains sum = 8.
 *
 * Example:
 * Given matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * Note:
 * You may assume that the matrix does not change.
 * There are many calls to sumRegion function.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class RangeSumQuery2DImmutable {

    /**
     * Do prefix sum by row, then for every query do row computes
     */
    class NumMatrix {

        int[][] m;
        public NumMatrix(int[][] matrix) {
            m = matrix;
            for (int r = 0; r < m.length; r++) {
                for (int c = 1; c < m[0].length; c++) {
                    m[r][c] += m[r][c - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int res = 0;
            for (int r = row1; r <= row2; r++) {
                res+= m[r][col2];
                if (col1 > 0)
                    res-= m[r][col1 - 1];
            }
            return res;
        }
    }
}
