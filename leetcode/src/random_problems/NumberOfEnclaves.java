package random_problems;

/**
 * 1020. Number of Enclaves
 * Medium
 * <p>
 * Given a 2D array A, each cell is 0 (representing sea) or 1 (representing land)
 * <p>
 * A move consists of walking from one land square 4-directionally to another land square, or off the boundary of
 * the grid.
 * <p>
 * Return the number of land squares in the grid for which we cannot walk off the boundary of the grid in any number
 * of moves.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 3
 * Explanation:
 * There are three 1s that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary.
 * Example 2:
 * <p>
 * Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * Output: 0
 * Explanation:
 * All 1s are either on the boundary or can reach the boundary.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 500
 * 1 <= A[i].length <= 500
 * 0 <= A[i][j] <= 1
 * All rows have the same size.
 */
public class NumberOfEnclaves {

    int rows, cols;
    int[][] arr;

    public int numEnclaves(int[][] A) {
        arr = A;
        rows = arr.length;
        if (rows == 0)
            return 0;

        cols = arr[0].length;

        //checking row 0and row N - 1
        for (int c = 0; c < cols; c++) {
            dfs(0, c);
            dfs( rows - 1, c);
        }

        for (int r = 1; r < rows - 1; r++) {
            dfs( r, 0);
            dfs( r, cols - 1);
        }


        int res = 0;
        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
                if (arr[r][c] == 1)
                    res++;
            }
        }

        return res;
    }

    void dfs(int r, int c) {
        if (arr[r][c] == 1) {
            arr[r][c] = 0;
            if (r > 0) {
                dfs(r - 1, c);
            }
            if (c > 0) {
                dfs(r, c - 1);
            }
            if (r < rows - 1) {
                dfs(r + 1, c);
            }
            if (c < cols - 1) {
                dfs(r, c + 1);
            }
        }
    }
}
