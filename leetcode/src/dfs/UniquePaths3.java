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
    rows = grid.length;
    cols = grid[0].length;
    this.grid = grid;
    count = 0;

    int toVisit = 0;
    int startR = 0, startC = 0;

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (grid[r][c] == -1) continue;

        if (grid[r][c] == 1) {
          startC = c;
          startR = r;
        }
        toVisit++;
      }
    }

    dfs(startR, startC, toVisit);

    return count;
  }

  void dfs(int r, int c, int toVisit) {
    toVisit--;
    if (toVisit < 0)
      return;
    if (grid[r][c] == 2 && toVisit == 0) {
      count++;
      return;
    }
    if (grid[r][c] == -1)
      return;

    grid[r][c] = 3;

    if (r > 0 && grid[r - 1][c] != 3) {
      int state = grid[r - 1][c];
      dfs(r - 1, c, toVisit);
      grid[r - 1][c] = state;
    }
    if (c > 0 && grid[r][c - 1] != 3) {
      int state = grid[r][c - 1];
      dfs(r, c - 1, toVisit);
      grid[r][c - 1] = state;
    }
    if (r < rows - 1 && grid[r + 1][c] != 3) {
      int state = grid[r + 1][c];
      dfs(r + 1, c, toVisit);
      grid[r + 1][c] = state;
    }
    if (c < cols - 1 && grid[r][c + 1] != 3) {
      int state = grid[r][c + 1];
      dfs(r, c + 1, toVisit);
      grid[r][c + 1] = state;
    }
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
