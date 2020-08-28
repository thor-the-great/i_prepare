package dfs;

/**
 * 1559. Detect Cycles in 2D Grid
Hard

Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.

 

Example 1:



Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
Output: true
Explanation: There are two valid cycles shown in different colors in the image below:

Example 2:



Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
Output: true
Explanation: There is only one valid cycle highlighted in the image below:

Example 3:



Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
Output: false
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m <= 500
1 <= n <= 500
grid consists only of lowercase English letters.

https://leetcode.com/problems/detect-cycles-in-2d-grid/
 */
public class DetectCyclesIn2dGrid {

    /**
     * Do DFS, marking visited points and keep previous point. If visit again the same point with the same 
     * character and it's not the previous one - this is a cycle
     */
    public boolean containsCycle(char[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!visited[r][c] && dfs(grid, r, c, grid[r][c], -1, -1, visited)) {
                    return true;
                } 
            }
        }
        return false;
    }
    
    boolean dfs(char[][] grid, int r, int c, char ch, int prevR, int prevC, boolean[][] visited) {
        visited[r][c] = true;
        boolean isCycle = false;
        if (!isCycle && r - 1 >= 0 && (r - 1 != prevR || c != prevC) && grid[r - 1][c] == ch) {
            if (visited[r - 1][c])
                return true;
            isCycle |= dfs(grid, r - 1, c, ch, r, c, visited);
        }
        if (!isCycle && c - 1 >= 0 && (r != prevR || c - 1 != prevC) && grid[r][c - 1] == ch) {
            if (visited[r][c - 1])
                return true;
            isCycle |= dfs(grid, r, c - 1, ch, r, c, visited);
        }
        if (!isCycle && r + 1 < grid.length && (r + 1 != prevR || c != prevC) && grid[r + 1][c] == ch) {
            if (visited[r + 1][c])
                return true;
            isCycle |= dfs(grid, r + 1, c, ch, r, c, visited);
        }
        if (!isCycle && c + 1 < grid[0].length && (r != prevR || c + 1 != prevC) && grid[r][c + 1] == ch) {
            if (visited[r][c + 1])
                return true;
            isCycle |= dfs(grid, r, c + 1, ch, r, c, visited);
        }
        return isCycle;
    }
}