package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1162. As Far from Land as Possible
 * Medium
 * Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents
 * land, find a water cell such that its distance to the nearest land cell is maximized and
 * return the distance.
 *
 * The distance used in this problem is the Manhattan distance: the distance between two cells
 * (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 *
 * If no land or water exists in the grid, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [[1,0,1],[0,0,0],[1,0,1]]
 * Output: 2
 * Explanation:
 * The cell (1, 1) is as far as possible from all the land with distance 2.
 * Example 2:
 *
 *
 *
 * Input: [[1,0,0],[0,0,0],[0,0,0]]
 * Output: 4
 * Explanation:
 * The cell (2, 2) is as far as possible from all the land with distance 4.
 *
 *
 * Note:
 *
 * 1 <= grid.length == grid[0].length <= 100
 * grid[i][j] is 0 or 1
 */
public class FarFromLandAsPossible {

  public int maxDistance(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;

    int res = -1;
    boolean[][] seen = new boolean[rows][cols];
    Queue<int[]> q = new LinkedList();
    //set all 1-s as visited and it them to queue
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (grid[r][c] == 1) {
          seen[r][c] = true;
          q.add(new int[] {r, c});
        }
      }
    }

    //from every cell with 1 start BFS
    while (!q.isEmpty()) {
      //BFS queue for the next level
      Queue<int[]> qNextLevel = new LinkedList();
      //iterate over queue content
      while (!q.isEmpty()) {
        int[] next = q.poll();
        int r1 = next[0], c1 = next[1];
        //check neighbours if there were not seen before
        if ( r1 > 0 && !seen[r1 - 1][c1]) {
          qNextLevel.add(new int[] {r1 - 1, c1});
          seen[r1 - 1][c1] = true;
        }
        if ( c1 > 0 && !seen[r1][ c1 - 1]) {
          qNextLevel.add(new int[] {r1, c1 - 1});
          seen[r1][c1 - 1] = true;
        }
        if ( r1 < rows - 1 && !seen[r1 + 1][ c1]) {
          qNextLevel.add(new int[] {r1 + 1, c1});
          seen[r1 + 1][c1] = true;
        }
        if ( c1 < cols - 1 && !seen[r1][ c1 + 1]) {
          qNextLevel.add(new int[] {r1, c1 + 1});
          seen[r1][c1 + 1] = true;
        }
      }
      //make nextLevel queue the current level queue
      q = qNextLevel;
      res++;
    }
    //check if we have visited something
    return res <= 0 ? -1 : res;
  }

}
