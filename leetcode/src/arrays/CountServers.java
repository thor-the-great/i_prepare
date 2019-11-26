package arrays;

/**
 * 1267. Count Servers that Communicate
 * Medium
 *
 * You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that
 * cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the
 * same row or on the same column.
 *
 * Return the number of servers that communicate with any other server.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: grid = [[1,0],[0,1]]
 * Output: 0
 * Explanation: No servers can communicate with others.
 * Example 2:
 *
 *
 *
 * Input: grid = [[1,0],[1,1]]
 * Output: 3
 * Explanation: All three servers can communicate with at least one other server.
 * Example 3:
 *
 *
 *
 * Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
 * Output: 4
 * Explanation: The two servers in the first row can communicate with each other. The two servers in the third column
 * can communicate with each other. The server at right bottom corner can't communicate with any other server.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 250
 * 1 <= n <= 250
 * grid[i][j] == 0 or 1
 */
public class CountServers {

    /**
     * Initially we count in one dimention, lets say - per row. Count servers, if count > 1 - increment result by the
     * count. Also we mark each server that can communicate and set it cell value to 2. The only cell we miss will be
     * the first one (at that point we just assign count = 1) - so store the col of the first server in this row in
     * a variable col.
     *
     * For other dimention (columns) it's easier - count separately 2-s and 1-s. If combined number is > 1 then update
     * result by count of 1s.
     *
     * O(nxm) time - iterate over the grid once each cell. O(1) space - no extra space, reuse grid
     * @param grid
     * @return
     */
    public int countServers(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int res = 0;
        //check each row, count how many good servers in each row
        for (int r = 0; r < rows; r++) {
            int count = 0; int col = -1;
            for (int c = 0; c < cols; c++) {
                //if this is server - increment count
                if (grid[r][c] == 1) {
                    ++count;
                    //if this is 2+ server in the row - mark every server cell as counted
                    if (count > 1)
                        grid[r][c] = 2;
                    else
                        col = c;
                }
            }
            //if there were any servers to communicate - add count to result, update
            //first one (we miss it on the first loop) as taken
            if (count > 1) {
                res += count;
                grid[r][col] = 2;
            }
        }
        //now check each column
        for (int c = 0; c < cols; c++) {
            //count has both taken and untaken, count2 - only untaken
            int count = 0; int count2 = 0;
            for (int r = 0; r < rows; r++) {
                if (grid[r][c] > 0) {
                    ++count;
                    if (grid[r][c] == 1)
                        ++count2;
                }
            }
            //if we've met any servers in this column - add count2 (untaken previously) to result
            if (count > 1 ) res += count2;
        }

        return res;
    }
}
