package arrays;

/**
 * 1252. Cells with Odd Values in a Matrix
 * Easy
 *
 * Given n and m which are the dimensions of a matrix initialized by zeros and given an array indices where
 * indices[i] = [ri, ci]. For each pair of [ri, ci] you have to increment all cells in row ri and column ci by 1.
 *
 * Return the number of cells with odd values in the matrix after applying the increment to all indices.
 *
 * Example 1:
 *
 *
 * Input: n = 2, m = 3, indices = [[0,1],[1,1]]
 * Output: 6
 * Explanation: Initial matrix = [[0,0,0],[0,0,0]].
 * After applying first increment it becomes [[1,2,1],[0,1,0]].
 * The final matrix will be [[1,3,1],[1,3,1]] which contains 6 odd numbers.
 * Example 2:
 *
 *
 * Input: n = 2, m = 2, indices = [[1,1],[0,0]]
 * Output: 0
 * Explanation: Final matrix = [[2,2],[2,2]]. There is no odd number in the final matrix.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 50
 * 1 <= m <= 50
 * 1 <= indices.length <= 100
 * 0 <= indices[i][0] < n
 * 0 <= indices[i][1] < m
 */
public class CellsWithOddValues {

    /**
     * Collect count of increments for each row and cell, where rows and cells represented by 2 arrays. Then for each
     * row check every cell in it and if the sum of r,c is odd - do res++. Because we interested in odd or even number
     * we can use booleans.
     * @param n
     * @param m
     * @param indices
     * @return
     */
    public int oddCells(int n, int m, int[][] indices) {
        //create array of flags for rows and columns, false indicates even
        boolean[] rows = new boolean[n], cols = new boolean[m];
        //for every indices pair switch the flag
        for (int[] ind : indices) {
            rows[ind[0]] = !rows[ind[0]];
            cols[ind[1]] = !cols[ind[1]];
        }
        int res = 0;
        //now iterate over every cell and check if combined flag is true - this means that only one of
        //numbers is odd
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (rows[r]^cols[c])
                    res++;
            }
        }
        return res;
    }
}
