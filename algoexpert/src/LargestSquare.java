/**
 * Largest Square New
 *     Multi Dimensional Arrays Dynamic Programming
 *
 * Given a two dimensional matrix made up of 0's and 1's, find the largest square containing all 1's and return its
 * 'area'. The 'area' is simply the sum of all integers enclosed in the square.
 * Example:
 *
 * Input Matrix :
 *
 *   1101
 *   1101
 *   1111
 *
 * Output  : 4
 */
public class LargestSquare {

    /**
     * Idea - we can calculate the square size
     * 1 - if the current cell is 1
     * 2 - as min( [c - 1,r], [c, r - 1], [c-1,r-1]) + 1
     * First row and column can be filled with initial values
     * Sweep the matrix from top to bottom, left to right, record the max. Result is max*max - max is only the size of
     * the longest side of the square, but we need an area.
     * @param matrix
     * @return
     */
    public static int largestSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] h = new int[rows][cols];

        int max = 0;

        for (int r = 0; r < rows; r++) {
            h[r][0] = matrix[r][0] - '0';
            max = Math.max(max, h[r][0]);
        }

        for (int c = 0; c < cols; c++) {
            h[0][c] = matrix[0][c] - '0';
            max = Math.max(max, h[0][c]);
        }

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (matrix[r][c] == '1') {
                    h[r][c] = Math.min(
                            h[r - 1][c - 1],
                            Math.min(
                                    h[r - 1][c],
                                    h[r][c - 1]))
                            + 1;
                    max = Math.max(max, h[r][c]);
                }
            }
        }

        return max*max;
    }
}
