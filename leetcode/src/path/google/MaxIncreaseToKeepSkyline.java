package path.google;

/**
 * 807. Max Increase to Keep City Skyline
 * Medium
 * In a 2 dimensional array grid, each value grid[i][j] represents the height of a building located there. We are
 * allowed to increase the height of any number of buildings, by any amount (the amounts can be different for
 * different buildings). Height 0 is considered to be a building as well.
 *
 * At the end, the "skyline" when viewed from all four directions of the grid, i.e. top, bottom, left, and right, must
 * be the same as the skyline of the original grid. A city's skyline is the outer contour of the rectangles formed
 * by all the buildings when viewed from a distance. See the following example.
 *
 * What is the maximum total sum that the height of the buildings can be increased?
 *
 * Example:
 * Input: grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]
 * Output: 35
 * Explanation:
 * The grid is:
 * [ [3, 0, 8, 4],
 *   [2, 4, 5, 7],
 *   [9, 2, 6, 3],
 *   [0, 3, 1, 0] ]
 *
 * The skyline viewed from top or bottom is: [9, 4, 8, 7]
 * The skyline viewed from left or right is: [8, 7, 9, 3]
 *
 * The grid after increasing the height of buildings without affecting skylines is:
 *
 * gridNew = [ [8, 4, 8, 7],
 *             [7, 4, 7, 7],
 *             [9, 4, 8, 7],
 *             [3, 3, 3, 3] ]
 */
public class MaxIncreaseToKeepSkyline {

    /**
     * Idea - find skyline separately for up-down and right-left. This would be maximum allowed height.
     * Then for every cell the maximum allowed would be minimum between up-down skyline and right-left skyline
     * Code below is more optimized version (for the sake of performance)
     * @param grid
     * @return
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int rows = grid.length;
        if (rows == 0) return 0;
        int cols = grid[0].length;

        int[] upDownSL = new int[cols];
        int[] rightLeftSL = new int[rows];
        //calculate skylines up-down and right-left separately
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rightLeftSL[r] = Math.max(rightLeftSL[r], grid[r][c]);
                upDownSL[c] = Math.max(upDownSL[c], grid[r][c]);
            }
        }
        //find max allowed increase (diff) based on min among 2 skylines cross
        int res = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                res += Math.min(rightLeftSL[r], upDownSL[c]) - grid[r][c];
            }
        }

        return res;
    }
}
