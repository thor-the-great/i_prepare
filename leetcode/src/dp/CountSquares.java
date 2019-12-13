package dp;

/**
 * 1277. Count Square Submatrices with All Ones
 * Medium
 *
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 *
 * Example 1:
 *
 * Input: matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * Output: 15
 * Explanation:
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 * Example 2:
 *
 * Input: matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * Output: 7
 * Explanation:
 * There are 6 squares of side 1.
 * There is 1 square of side 2.
 * Total number of squares = 6 + 1 = 7.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 */
public class CountSquares {

    /**
     * This one based on Maximal Square https://leetcode.com/problems/maximal-square/.
     *
     * We can use original matrix to count numbers. We start sweeping from left-up and chek every cell to the left,
     * top and left-top and pick smallest number among those, then add 1 if the original cell was 1. For example:
     * 1, 1
     * 1, 1
     * when we scan [1,1] we do [1,1] = Math.min([0,1], [1,0], [0,0]) + 1 = 2. The res will be 3 at this point, it
     * became 3 + 2 = 5 which is correct - it counts the original 1 plus 1 of 2x2 matrix.
     *
     * O(nxm) time - proportional to number of cells, O(1) space - use orignal matrix for dp results.
     * @param matrix
     * @return
     */
    public int countSquares(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int res = 0;
        //update bottom-right cell of the each square matrix
        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                //check 3 neighboring cells, choose min and make + 1
                if (matrix[r][c] == 1) {
                    matrix[r][c] = 1 + Math.min(matrix[r - 1][c - 1],
                            Math.min(matrix[r - 1][c], matrix[r][c - 1]));
                }
                //accumulate this matrix to the final result
                res+=matrix[r][c];
            }
        }
        //count cells from 0-th row and 0th column - we didn't scan in in the main loop
        for (int c = 0; c < cols; c++) res+=matrix[0][c];
        for (int r = 1; r < rows; r++) res+=matrix[r][0];
        return res;
    }
}
