package dp;

/**
 * 741. Cherry Pickup
Hard

You are given an n x n grid representing a field of cherries, each cell is one of three possible integers.

0 means the cell is empty, so you can pass through,
1 means the cell contains a cherry that you can pick up and pass through, or
-1 means the cell contains a thorn that blocks your way.
Return the maximum number of cherries you can collect by following the rules below:

Starting at the position (0, 0) and reaching (n - 1, n - 1) by moving right or down through valid path cells (cells with value 0 or 1).
After reaching (n - 1, n - 1), returning to (0, 0) by moving left or up through valid path cells.
When passing through a path cell containing a cherry, you pick it up, and the cell becomes an empty cell 0.
If there is no valid path between (0, 0) and (n - 1, n - 1), then no cherries can be collected.
 

Example 1:


Input: grid = [[0,1,-1],[1,0,-1],[1,1,1]]
Output: 5
Explanation: The player started at (0, 0) and went down, down, right right to reach (2, 2).
4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
Then, the player went left, up, up, left to return home, picking up one more cherry.
The total number of cherries picked up is 5, and this is the maximum possible.
Example 2:

Input: grid = [[1,1,-1],[1,-1,1],[-1,1,1]]
Output: 0
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 50
grid[i][j] is -1, 0, or 1.
grid[0][0] != -1
grid[n - 1][n - 1] != -1

https://leetcode.com/problems/cherry-pickup/
 */
public class CherryPickup {
    
    int[][] dir = new int[][] {
        {0, 1, 0}, 
        {1, 0, 0},
        {0, 1, 1},
        {1, 0, 1}
    };
    
    public int cherryPickup(int[][] grid) {
        //d = c1 + r1 = c2 + r2 => r2 = c1 - c2 + r1
        int N = grid.length; 
        //edge cases - can we even start and step at the last cell
        if (grid[0][0] == -1 || grid[N - 1][N - 1] == -1) {
            return 0;
        }
        
        int[][][] memo = new int[N][N][N];
        int res = dfs(grid, memo, 0, 0, 0);
        return res == -1 ? 0 : res;
    }
    
    int dfs(int[][] grid, int[][][] memo, int r1, int c1, int c2) {
        if (memo[r1][c1][c2] != 0) {
            return memo[r1][c1][c2];
        }
        int r2 = c1 - c2 + r1;
        int cur = grid[r1][c1];
        if (r1 != r2 && c1 != c2) {
            cur += grid[r2][c2];
        }
        int N = grid.length;
        if (r1 == N - 1 && c1 == N - 1 && c2 == N -1) {
            memo[r1][c1][c2] = cur;
            return cur;
        }
        
        int max = -1;
        //making moves
        for (int[] d : dir) {
            int newR1 = r1 + d[0], newC1 = c1 + d[1], newC2 = c2 + d[2];
            int newR2 = newR1 + newC1 - newC2;
            //sanity checks
            if ((newR1 < N && newR2 < N && newC1 < N && newC2 < N 
                && grid[newR1][newC1] != -1 && grid[newR2][newC2] != -1)) {
                max = Math.max(max, 
                               dfs(grid, memo, newR1, newC1, newC2));
            }
        }
        //check if we could move any farther from the current point
        if (max == -1) {
            cur = -1;
        } else {
            cur += max;
        }
        memo[r1][c1][c2] = cur;
        return cur;
    }
}
