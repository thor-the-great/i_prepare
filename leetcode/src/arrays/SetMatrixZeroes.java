package arrays;

/**
 * 73. Set Matrix Zeroes
 * Medium
 *
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 *
 * Example 1:
 *
 * Input:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * Output:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * Example 2:
 *
 * Input:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * Output:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 * Follow up:
 *
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class SetMatrixZeroes {

    /**
     * Keep track of whole row and/or column in a separate array. Set it for whole column/row, then 0-fy it effectively
     * For the constant space we can utilize original matrix
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;

        boolean[] rr = new boolean[rows], cc = new boolean[cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 0) {
                    rr[r] = true;
                    cc[c] = true;
                }
            }
        }
        for (int r = 0; r < rows; r++) {
            if (rr[r]) {
                for (int c = 0; c < cols; c++) {
                    matrix[r][c] = 0;
                }
            }
        }
        for (int c = 0; c < cols; c++) {
            if (cc[c]) {
                for (int r = 0; r < rows; r++) {
                    matrix[r][c] = 0;
                }
            }
        }
    }
}
