package arrays;

/**
 * 1314. Matrix Block Sum
 * Medium
 *
 * Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements
 * mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
 * Output: [[12,21,16],[27,45,33],[24,39,28]]
 * Example 2:
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
 * Output: [[45,45,45],[45,45,45],[45,45,45]]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n, K <= 100
 * 1 <= mat[i][j] <= 100
 */
public class MatrixBlockSum {

    /**
     * Start with the square of the sum, move it to the right by one column and on each move decrement the left
     * column and increment by right column.
     * @param mat
     * @param K
     * @return
     */
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int rows = mat.length, cols = mat[0].length;

        int[][] res = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            int h = r + K + 1;
            //for the c = 0 start with the right-bottom part of the sum block
            for (int rr = Math.max(0, r - K); rr < Math.min(rows, h); rr++) {
                for (int c = 0; c < Math.min(cols, K + 1); c++) {
                    res[r][0]+=mat[rr][c];
                }
            }
            //on each next column
            for (int c = 1; c < cols; c++) {
                //save sum from the previous cell
                res[r][c] = res[r][c - 1];
                //minus prev col
                if (c - K - 1>= 0) {
                    for (int rr = Math.max(0, r - K); rr < Math.min(rows, h); rr++) {
                        res[r][c]-=mat[rr][c - K - 1];
                    }
                }
                //add next col
                if (c + K < cols) {
                    for (int rr = Math.max(0, r - K); rr < Math.min(rows, h); rr++) {
                        res[r][c]+=mat[rr][c + K];
                    }
                }
            }
        }
        return res;
    }
}
