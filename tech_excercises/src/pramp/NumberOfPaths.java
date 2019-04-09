package pramp;

/**
 * Number of Paths
 * You’re testing a new driverless car that is located at the Southwest (bottom-left) corner of an n×n grid. The car
 * is supposed to get to the opposite, Northeast (top-right), corner of the grid. Given n, the size of the grid’s
 * axes, write a function numOfPathsToDest that returns the number of the possible paths the driverless car can take.
 *
 * alt the car may move only in the white squares
 *
 * For convenience, let’s represent every square in the grid as a pair (i,j). The first coordinate in the pair
 * denotes the east-to-west axis, and the second coordinate denotes the south-to-north axis. The initial state of
 * the car is (0,0), and the destination is (n-1,n-1).
 *
 * The car must abide by the following two rules: it cannot cross the diagonal border. In other words, in every step
 * the position (i,j) needs to maintain i >= j. See the illustration above for n = 5. In every step, it may go one
 * square North (up), or one square East (right), but not both. E.g. if the car is at (3,1), it may go to
 * (3,2) or (4,1).
 *
 * Explain the correctness of your function, and analyze its time and space complexities.
 *
 * Example:
 *
 * input:  n = 4
 *
 * output: 5 # since there are five possibilities:
 *           # “EEENNN”, “EENENN”, “ENEENN”, “ENENEN”, “EENNEN”,
 *           # where the 'E' character stands for moving one step
 *           # East, and the 'N' character stands for moving one step
 *           # North (so, for instance, the path sequence “EEENNN”
 *           # stands for the following steps that the car took:
 *           # East, East, East, North, North, North)
 * Constraints:
 *
 * [time limit] 5000ms
 *
 * [input] integer n
 *
 * 1 ≤ n ≤ 100
 * [output] integer
 */
public class NumberOfPaths {

    /**
     * Optimal DP approach - path to every cell (r,c) is a combination of paths from (c - 1, r) and (r + 1, c).
     * This leads to a formula
     *
     * p(r, c) = p(c-1, r) + p (r+1, c)
     *
     * we can fill the bottom row with 1 - we can reach it in only one possible way. Sweeping from bottom to top going
     * from left to right.
     *
     * Catch - need to sweep only to the right of the diagonal, so column starts + 1 for every higher row.
     *
     * @param n
     * @return
     */
    static int numOfPathsToDest(int n) {
        // your code goes here
        int[][] dp = new int[n][n];
        for (int c = 0; c < n; c++) {
            dp[n - 1][c] = 1;
        }

        for (int r = n - 2; r >= 0; r--) {
            for (int c = (n - 1 - r); c < n; c++) {
                dp[r][c] = dp[r + 1][c] + dp[r][c - 1];
            }
        }

        return dp[0][n-1];
    }

    //non-optimal way - do the recursive
    static int res = 0;

    static int numOfPathsToDestRec(int n) {
        // your code goes here
        res = 0;
        helper(n - 1, n - 1, n);
        return res;
    }

    static void helper(int r, int c, int n) {
        //restriction
        if (r < c)
            return;

        if (r == 0 && c == 0) {
            res++;
            return;
        }
        //incrementing row
        if (r > 0) {
            helper(r - 1, c, n);
        }

        //increment column
        if (c > 0) {
            helper(r, c - 1, n);
        }
    }
}
