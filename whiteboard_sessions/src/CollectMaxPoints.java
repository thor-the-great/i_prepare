import java.util.Arrays;

public class CollectMaxPoints {

    /**
     * We have a grid MxN with following legend :
     *  1  - point
     *  0  - can walk, no points
     *  -1 - can't walk (block)
     *
     *  We start @ 0,0 and go to the right,bottom cell (N-1, M-1), taking only right and down. After that we need to
     *  return back to 0,0 now taking left and ups only.
     *
     *  We collecting points on the way. We need to maximize our paths to and from so we collect max possible points
     *  on the roundtrip.
     *
     *  Notes:
     *  - when point is collected it turns into 0
     *  - point in a cell can be collected only once
     *
     * @param grid
     * @return
     */
    int maxCost(int[][] grid) {
        //idea is we have to track paths simultaneously, otherwise we can maximize only one but not two. Good thing is -
        //we can combine back path with initial pretending that for back path we going from end to beginning , so we
        //kind of have two paths at the same time.
        //so we starting from 0,0 and maximize two paths at the same time.
        //we use DP to save work. We use only 3 dimensions because we can calculate col2 if we know others - length of
        // path is the same for 1st and 2nd.
        int rows = grid.length;
        int cols = grid[0].length;

        int cost = 0;
        if (grid[0][0] == -1) return cost;
        if ((grid[0][0] == 1)) {
            cost++;
            grid[0][0] = 0;
        }
        if ((grid[rows - 1][cols - 1] == 1)) {
            cost++;
            grid[rows - 1][cols - 1] = 0;
        }
        int[][][] dp = new int[rows][cols][rows];
        for(int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        cost += solve(rows, cols, grid, dp, 0, 0, 0);
        return Math.max(cost, 0);
    }

    int solve(int rows, int cols, int[][] grid,
              int[][][] dp, int row1, int col1, int row2) {
        int col2 = (row1 + col1) - (row2);
        if (row1 == rows - 1 && col1 == cols - 1 && row2 == rows - 1 &&  col2 == cols - 1) {
            return 0;
        }
        if (row1 >= rows || col1 >= cols || row2 >= rows || col2 >= cols) {
            return Integer.MIN_VALUE;
        }
        if (dp[row1][col1][row2] != -1)
            return dp[row1][col1][row2];

        int ch1 = Integer.MIN_VALUE, ch2 = Integer.MIN_VALUE, ch3 = Integer.MIN_VALUE, ch4 = Integer.MIN_VALUE;

        // If path 1 is moving right and path 2 is moving down.
        if (isInGrid(rows, cols, row1, col1 + 1) && isInGrid(rows, cols, row2 + 1, col2) &&
                grid[row1][col1 + 1] != -1 && grid[row2 + 1][col2] != -1)
            ch1 = cost(grid, row1, col1 + 1, row2 + 1, col2) +
                    solve(rows, cols, grid, dp, row1, col1 + 1, row2 + 1);

        // If path 1 is moving right and path 2 is moving right.
        if (isInGrid(rows, cols, row1, col1 + 1) && isInGrid(rows, cols, row2, col2 + 1) &&
                grid[row1][col1 + 1] != -1 &&
                grid[row2][col2 + 1] != -1)
            ch2 = cost(grid, row1, col1 + 1, row2, col2 + 1) +
                    solve(rows, cols, grid, dp, row1, col1 + 1, row2);

        // If path 1 is moving down and path 2 is moving right.
        if (isInGrid(rows, cols, row1 + 1, col1) && isInGrid(rows, cols, row2, col2 + 1) &&
                grid[row1 + 1][col1] != -1 &&
                grid[row2][col2 + 1] != -1)
            ch3 = cost(grid, row1 + 1, col1, row2, col2 + 1) +
                    solve(rows, cols, grid, dp, row1 + 1, col1, row2);

        // If path 1 is moving down and path 2 is moving down.
        if (isInGrid(rows, cols, row1 + 1, col1 ) && isInGrid(rows, cols, row2 + 1, col2) &&
                grid[row1 + 1][col1] != -1 &&
                grid[row2 + 1][col2] != -1)
            ch4 = cost(grid, row1 + 1, col1, row2 + 1, col2) +
                    solve(rows, cols, grid, dp, row1 + 1, col1, row2 + 1);

        // Returning the maximum of 4 options.
        return dp[row1][col1][row2] = Arrays.stream(new int[] {ch1, ch2, ch3, ch4}).max().getAsInt();
    }

    private boolean isInGrid(int rows, int cols, int row, int col) {
        return (row < rows && col < cols);
    }

    private int cost(int[][] grid, int row1, int col1, int row2, int col2) {
        if (row1 == row2 && col1 == col2) {
            return grid[row1][col1] == 1 ? 1 : 0;
        }
        int cost = 0;
        if (grid[row1][col1] == 1) cost++;
        if (grid[row2][col2] == 1) cost++;
        return cost;
    }

    public static void main(String[] args) {
        CollectMaxPoints obj = new CollectMaxPoints();
        int[][] grid1 = new int[][] {
                {1,  1,  1, 1},
                {0, -1, -1, 0},
                {0, -1, -1, 0},
                {1,  1,  1, 1}
        };
        System.out.println("Max points paths for grid1 : " + obj.maxCost(grid1));

        int[][] grid2 = new int[][] {
                {0,  1,  0,  1, 0},
                {1, -1, -1, -1, 0},
                {1,  0,  1,  0, 1},
                {0, -1, -1, -1, 1},
                {0,  1,  0,  1, 0}
        };
        System.out.println("Max points paths for grid2 : " + obj.maxCost(grid2));
    }
}
