/**
 * Grid is filled with coins, you start at left-up and must end up at right-bottom.
 * It can move right, down and up
 * Find path that gives max amount of coins
 */
public class CoinCollector3Directions_DP {

    final int UP = 0;
    final int DOWN = 1;
    final int LEFT = 2;

    int maxCoins(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][][] dp = new int[rows + 2][cols + 2][3];

        for(int r = rows; r > 0; r--) {
            for(int c = cols; c > 0; c--) {
                for (int d = 0; d < 3; d ++) {
                    if (r == rows && c == cols)
                        dp[r][c][d] = grid[r - 1][c - 1];
                    else {
                        switch (d) {
                            case UP:
                                dp[r][c][d] = grid[r - 1][c - 1] + Math.max(dp[r + 1][c][LEFT], dp[r][ c + 1][UP]);
                                break;
                            case LEFT:
                                dp[r][c][d] = grid[r- 1][c- 1] + Math.max(dp[r + 1][c][LEFT], Math.max(dp[r][ c + 1][UP], dp[r][c - 1][DOWN]));
                                break;
                            case DOWN:
                                dp[r][c][d] = grid[r-1][c-1] + Math.max(dp[r][c - 1][DOWN], dp[r][ c + 1][UP]);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }

        return Math.max(dp[1][1][UP], Math.max(dp[1][1][DOWN], dp[1][1][LEFT]));
    }

    public static void main(String[] args) {
        CoinCollector3Directions_DP obj = new CoinCollector3Directions_DP();
        int[][] grid = new int[][] {
                {4, 7, 35},
                {6, 6,  1},
                {25, 4, 3}
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
