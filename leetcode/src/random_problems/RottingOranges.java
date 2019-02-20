package random_problems;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 994. Rotting Oranges
 * Easy
 *
 * In a given grid, each cell can have one of three values:
 *
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible,
 * return -1 instead.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * Example 2:
 *
 * Input: [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only
 * happens 4-directionally.
 * Example 3:
 *
 * Input: [[0,2]]
 * Output: 0
 * Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 *
 * Note:
 *
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] is only 0, 1, or 2.
 */
public class RottingOranges {

    /**
     * Idea: do BFS from every starting point.
     * catch - need to track time passed for every cell
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] time = new int[rows][cols];

        Queue<Integer> q = new LinkedList();
        //fill all starting cells (those that are 2 from the beginning)
        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int cell = grid[r][c];
                if (cell == 1 || cell == 2) {
                    count++;
                    if (cell == 2) {
                        int idx = cols * r + c;
                        q.add(idx);
                    }
                }
            }
        }
        //start BFS, starting from starting points
        int res = 0;
        while (!q.isEmpty()) {
            //next unvisited point
            int idx = q.poll();
            //decrement count of visited cells
            count--;
            int r = idx / cols;
            int c = idx % cols;
            //form next time tick
            int t = time[r][c] + 1;
            //check neighbours one by one
            if (r > 0 && grid[r - 1][c] == 1) {
                grid[r - 1][c] = 2;
                time[r - 1][c] = t;
                q.add(idx - cols);
                res = t;
            }
            if (c > 0 && grid[r][c - 1] == 1) {
                grid[r][c - 1] = 2;
                time[r][c - 1] = t;
                q.add(idx - 1);
                res = t;
            }
            if (r < rows - 1 && grid[r + 1][c] == 1) {
                grid[r + 1][c] = 2;
                time[r + 1][c] = t;
                q.add(idx + cols);
                res = t;
            }
            if (c < cols - 1 && grid[r][c + 1] == 1) {
                grid[r][c + 1] = 2;
                time[r][c + 1] = t;
                q.add(idx + 1);
                res = t;
            }
        }
        //if we haven't visited all cell - it must be some unreachable
        if (count > 0)
            return -1;

        return res;
    }
}
