package dfs;

/**
 * 980. Unique Paths III
 * Hard
 *
 * On a 2-dimensional grid, there are 4 types of squares:
 *
 * 1 represents the starting square.  There is exactly one starting square.
 * 2 represents the ending square.  There is exactly one ending square.
 * 0 represents empty squares we can walk over.
 * -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that
 * walk over every non-obstacle square exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * Example 2:
 *
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * Example 3:
 *
 * Input: [[0,1],[2,0]]
 * Output: 0
 * Explanation:
 * There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 *
 *
 * Note:
 *
 * 1 <= grid.length * grid[0].length <= 20
 */
public class UniquePaths3 {

  int count = 0;
  int[][] grid;
  int rows, cols;

  /**
   * Idea - first count number of cell we have to walk, excluding obstacles. Then do the DFS keeping the
   * number of cells we walked. If we reach the end and count == 0 - count it as a valid walk.
   * @param grid
   * @return
   */
  public int uniquePathsIII(int[][] grid) {
    int rows = grid.length, cols = grid[0].length;
    int startR = -1, startC = -1;
    int steps = 0;
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        //if anything where we can walk - save important values
        if (grid[r][c] != -1) {
          ++steps;
          if (grid[r][c] == 1) {
            startR = r;
            startC = c;
          }
        }
      }
    }
    return dfs(startR, startC, grid, steps);
  }

  int dfs(int r, int c, int[][] grid, int steps) {
    //base case 1 - we reached the final cell
    if (grid[r][c] == 2) {
      //if we visited all cell add one path to result
      if (steps == 1) {
        return 1;
      }
      //if not all cells were visited ignore this path
      return 0;
    }
    //if cell has been visited return
    if (grid[r][c] == -1)
      return 0;
    //good cell found on the path - mark it as visited and trying all neighbours
    grid[r][c] = -1;
    int res = 0;
    //visit neighbours in all foure directions
    if (r - 1 >= 0) {
      res += dfs(r - 1, c, grid, steps - 1);
    }
    if (c - 1 >= 0) {
      res += dfs(r, c - 1, grid, steps - 1);
    }
    if (r + 1 < grid.length) {
      res += dfs(r + 1, c, grid, steps - 1);
    }
    if (c + 1 < grid[0].length) {
      res += dfs(r, c + 1, grid, steps - 1);
    }
    //unmark this cell from visited so it can be visited by other paths
    grid[r][c] = 0;
    //return number of paths that we get after visiting this cell
    return res;
  }

  public static void main(String[] args) {
    int[][] grid = new int[][] {
        {1,0,0,0},
        {0,0,0,0},
        {0,0,2,-1}};

    UniquePaths3 obj = new UniquePaths3();
    System.out.println(obj.uniquePathsIII(grid));

    grid = new int[][] {
        {1,0,0,0},
        {0,0,0,0},
        {0,0,0,2}};

    System.out.println(obj.uniquePathsIII(grid));
  }
}
