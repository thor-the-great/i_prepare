/**
 * Matrix Max Sum Path with Dynamic Programming New
 *     Multi Dimensional Arrays Dynamic Programming
 * Given an m x n matrix filled with non-negative integers, use dynamic programming techniques to find the maximum sum
 * along a path from the top-left of the grid to the bottom-right. Return this maximum sum. The direction of movement
 * is limited to right and down.
 *
 * Example:
 * Input Matrix :
 *
 *     1 2 3
 *     4 5 6
 *     7 8 9
 *
 * Output  : 1 + 4 + 7 + 8 + 9 = 29
 *
 *
 * Note:
 * You may have previously solved the DFS variant of this problem. That won't work for large sized matrices - just
 * consider the size of the recursion tree for a 100x100 matrix! Dynamic Programming should afford a better solution.
 */
public class MatrixMaxSum {

    /**
     * Use DP - sweep from up-left going col by col. Each cell will be cost of this cell plus max of higher and
     * left ones. 0th row and 0th column can be filled in obvious way
     * @param grid
     * @return
     */
    public static int matrixMaxSumDP(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 1; r < rows; r++) {
            grid[r][0] += grid[r - 1][0];
        }

        for (int c = 1; c < cols; c++) {
            grid[0][c] += grid[0][c - 1];
        }

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                grid[r][c] += Math.max(grid[r - 1][c], grid[r][c - 1]);
            }
        }

        return grid[rows - 1][cols - 1];
    }
}
