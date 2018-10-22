/**
 * Grid is filled with coins, you start at left-up and must endup at right-bottom.
 * It can move only right and down
 * Find path that gives max amount of coins
 */
public class CoinCollector_DP {

    int maxCoins(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows + 1][cols + 1];

        for(int r = rows - 1; r >= 0; r--) {
            for(int c = cols - 1; c >= 0; c--) {
                if (r == rows - 1 && c == cols - 1)
                    dp[r][c] = grid[r][c];
                else {
                    dp[r][c] = grid[r][c] + Math.max(dp[r + 1][c], dp[r][c + 1]);
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        CoinCollector_DP obj = new CoinCollector_DP();
        int[][] grid = new int[][] {
                {4, 7, 15},
                {6, 6,  1},
                {1, 25, 3}
        };
        System.out.println(obj.maxCoins(grid));

        grid = new int[][] {
                {4,  17, 15},
                {6,  26,  1},
                {6,   5,  3},
                {2,  11,  8}
        };
        System.out.println(obj.maxCoins(grid));
    }
}
