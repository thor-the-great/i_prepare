package path.google;

/**
 * 240. Search a 2D Matrix II
 * Medium
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Example:
 *
 * Consider the following matrix:
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 *
 * Given target = 20, return false.
 *
 */
public class Search2DMatrixII {

    /**
     * Idea - use prune of space. Start from left-bottom cell. If it's greater than target we definitely will not
     * find element in this row nor in any other rows below. So we decrement rows. If cell is less - we can find
     * element in this row, but in a different column - so increment col.
     * This way complexity in O(rows + cols) - each of cols and rows can be changed at most cols and rows time
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0)
            return false;
        int cols = matrix[0].length;

        int r = rows - 1;
        int c = 0;
        int el;
        while (r >= 0 && c < cols) {
            el = matrix[r][c];
            if (el == target) {
                return true;
            } else if (el > target) {
                r--;
            } else {
                c++;
            }
        }
        return false;
    }
}
