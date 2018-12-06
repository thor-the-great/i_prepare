package path.google;

/**
 * 463. Island Perimeter
 * Easy
 *
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 *
 *
 * Example:
 *
 * Input:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 *
 * Output: 16
 *
 * Explanation: The perimeter is the 16 yellow stripes in the image below:
 *
 */
public class IslandPerimeter {
    /**
     * Idea is - draw the picture. Count lands, each land has 4 borders. However every neighbour reduces number of
     * by 2 - one in this cell and another one in the neighbour. So count lands * 4 - potential max perimeter. Then count
     * neighbours and *2 - this is how many lost due to next cells.
     * Result is - 4*lands - 2*neighbours
     *
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        if (rows == 0) return 0;
        int cols = grid[0].length;

        int numOfLands = 0;
        int numOfBorders = 0;

        for (int r = 0; r < rows; r ++) {
            for (int c =0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    numOfLands++;
                    if (r < rows - 1 && grid[r + 1][c] == 1) {
                        numOfBorders++;
                    }
                    if (c < cols - 1 && grid[r][c + 1] == 1) {
                        numOfBorders++;
                    }
                }
            }
        }
        return (numOfLands * 4) - 2*numOfBorders;
    }
}
