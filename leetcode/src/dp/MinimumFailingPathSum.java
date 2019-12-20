package dp;

/**
 * 931. Minimum Falling Path Sum
 * Medium
 *
 * Given a square array of integers A, we want the minimum sum of a falling path through A.
 *
 * A falling path starts at any element in the first row, and chooses one element from each row.
 * The next row's choice must be in a column that is different from the previous row's column by
 * at most one.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,2,3],[4,5,6],[7,8,9]]
 * Output: 12
 * Explanation:
 * The possible falling paths are:
 * [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
 * [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
 * [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
 * The falling path with the smallest sum is [1,4,7], so the answer is 12.
 *
 *
 *
 * Note:
 *
 * 1 <= A.length == A[0].length <= 100
 * -100 <= A[i][j] <= 100
 */
public class MinimumFailingPathSum  {

    /**
     * Do DP - for every r-1 row going to the next row will be this r,c cost plus the min among 3 neighboring cell in r-th row.
     * Thus we can build a recursion A[r][c] = A[r - 1][c] + Min(A[r][c - 1], A[r][c], A[r][c + 1])
     * @param A
     * @return
     */
    public int minFallingPathSum(int[][] A) {
        int rows = A.length, cols = A[0].length;
        int[][] dp = new int[rows][cols];
        for (int r = 0; r < rows - 1; r++) {
            for (int c = 0; c < cols; c++) {
                dp[r][c] = Integer.MAX_VALUE;
            }
        }
        for (int c = 0; c < cols; c++) {
            dp[rows - 1][c] = A[rows - 1][c];
        }


        for (int r = rows - 2; r >= 0; r--) {
            for (int c = 0; c < cols; c++) {
                int min = dp[r + 1][c];
                if (c - 1 >= 0)
                    min = Math.min(min, dp[r + 1][c - 1]);
                if (c + 1 < cols)
                    min = Math.min(min, dp[r + 1][c + 1]);
                dp[r][c] = Math.min(dp[r][c], A[r][c] + min);
            }
        }

        int res = dp[0][0];
        for (int c = 1; c < cols; c++) {
            res = Math.min(res, dp[0][c]);
        }
        return res;
    }
}
