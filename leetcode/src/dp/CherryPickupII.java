package dp;

/**
 * 1463. Cherry Pickup II
Hard

You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents the number of cherries that you can collect from the (i, j) cell.

You have two robots that can collect cherries for you:

    Robot #1 is located at the top-left corner (0, 0), and
    Robot #2 is located at the top-right corner (0, cols - 1).

Return the maximum number of cherries collection using both robots by following the rules below:

    From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
    When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
    When both robots stay in the same cell, only one takes the cherries.
    Both robots cannot move outside of the grid at any moment.
    Both robots should reach the bottom row in grid.

 

Example 1:

Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
Output: 24
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
Total of cherries: 12 + 12 = 24.

Example 2:

Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
Output: 28
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
Total of cherries: 17 + 11 = 28.

 

Constraints:

    rows == grid.length
    cols == grid[i].length
    2 <= rows, cols <= 70
    0 <= grid[i][j] <= 100

https://leetcode.com/problems/cherry-pickup-ii/
 */
public class CherryPickupII {
    
    int rows = 0, cols = 0;
    int[][][] memo;
    int[][] grid;
    
    /**
     * DP problem, top-down (as we don't know the final cell)
     * 
     * dp[row][col1][col2] - state describes best cherries that possible to get at row "row" if robot one step on col1 and robot 2 step to col2. 
     * 
     * - both robots are only allow to be at the same row
     * - on each step need to check if robots are not in the same cell col1 != col2, otherwise don't double-count cherries
     * - save max to memo, this is dp part
     * - from each position there are 9 steps at max possible - 3 for each robots, combinations
     * 
     * O(rows*cols^2) time and space
     */
    public int cherryPickup(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        this.grid = grid;
        memo = new int[rows][cols][cols];
        return dfs(0, 0, cols - 1);
    }
    
    int dfs(int row, int col1, int col2) {
        if (row + 1 > rows) {
            return 0;
        }
        
        if (memo[row][col1][col2] > 0) {
            return memo[row][col1][col2];
        }
        
        int cur = grid[row][col1];
        if (col1 != col2) {
            cur+= grid[row][col2];
        }
        
        int max = 0;
        for (int move1 = -1; move1 <= 1; move1++) {
            int newCol1 = col1 + move1;
            if (newCol1 >= 0 && newCol1 < cols) {
                for (int move2 = -1; move2 <= 1; move2++) {
                    int newCol2 = col2 + move2;
                    if (newCol2 >= 0 && newCol2 < cols) {
                        max = Math.max(max,
                                       dfs(row + 1, newCol1, newCol2));
                    }
                }
            }
        }
        cur+=max;
        memo[row][col1][col2] = cur;
        return cur;
    }
}
