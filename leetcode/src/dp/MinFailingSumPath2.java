package dp;

/**
 * 1289. Minimum Falling Path Sum II
 * Hard
 *
 * Given a square grid of integers arr, a falling path with non-zero shifts is a choice of
 * exactly one element from each row of arr, such that no two elements chosen in adjacent rows
 * are in the same column.
 *
 * Return the minimum sum of a falling path with non-zero shifts.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: 13
 * Explanation:
 * The possible falling paths are:
 * [1,5,9], [1,5,7], [1,6,7], [1,6,8],
 * [2,4,8], [2,4,9], [2,6,7], [2,6,8],
 * [3,4,8], [3,4,9], [3,5,7], [3,5,9]
 * The falling path with the smallest sum is [1,5,7], so the answer is 13.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length == arr[i].length <= 200
 * -99 <= arr[i][j] <= 99
 */
public class MinFailingSumPath2 {

    /**
     * DP approach, do bottom-up.
     * for each r,c we can count best r + 1, c - for this count all elements between [r + 1, 0] and
     * [r + 1, cols] except for [r + 1][c], then it means to get to [r + 1][c] we can go only from [r][c],
     * so DP[r][c] = min(r + 1, 0...cols except for r + 1,c)
     *
     * @param arr
     * @return
     */
    public int minFallingPathSum(int[][] arr) {
        int rows = arr.length, cols = arr[0].length;
        int res = Integer.MAX_VALUE;
        //going from bottom to the top
        for (int r = rows - 1; r > 0; r--) {
            for (int c = 0; c < cols; c++) {
                //calculate best [r,  [0,c - 1]..[c + 1,cols]]
                int min = Integer.MAX_VALUE;
                int c1 = 0;
                for (; c1 < c; c1++) {
                    min = Math.min(min, arr[r][c1]);
                }
                for (c1 = c + 1; c1 < cols; c1++) {
                    min = Math.min(min, arr[r][c1]);
                }
                //for r - 1,c the best we can go from there is min
                arr[r - 1][c] = arr[r - 1][c] + min;
            }
            //for the first row we can calculate min as a result
            if (r - 1 == 0) {
                for (int c = 0; c < cols; c++)
                    res = Math.min(res, arr[0][c]);
            }
        }
        return res == Integer.MAX_VALUE ? arr[0][0] : res;
    }

}
