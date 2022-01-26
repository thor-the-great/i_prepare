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
     *  [*, *, *, *,  *]
     *  [*,|*, *, *,| *]
     *  [*,|*, *, *,| *]
     *  [*,|*,_*,_*,| *]
     *  [*, *, *, *,  *]
     *  if we count all prefix sum square - wise then the answer will be:
     * sum of large square (r - k, c - k) ( r + k,c + k) minus two smaller rectangles (r - k - 1, c + k) and (r + k, c - k - 1) and plus smaller square (r - k, c - k) as it's substracted
     * two time when we do minus two rectangles
     * 
     * O(r x c) time
     * O(r x c) space
     */
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int rows = mat.length, cols = mat[0].length;
        
        int[][] sums = new int[rows][cols];
        sums[0][0] = mat[0][0];
        for (int r = 1; r < rows; r++) {
            sums[r][0] = sums[r - 1][0] + mat[r][0];
        }
        for (int c = 1; c < cols; c++) {
            sums[0][c] = sums[0][c - 1] + mat[0][c];
        }
        
        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                sums[r][c] = sums[r - 1][c] + sums[r][c - 1] + mat[r][c] - sums[r - 1][c - 1];
            }
        }
        
        int[][] res = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int upLeftR = Math.max(0, r - k), upLeftC = Math.max(0, c - k);
                int downRightR = Math.min(rows - 1, r + k), downRightC = Math.min(cols - 1, c + k);

                res[r][c] = sums[downRightR][downRightC];
                if (upLeftR > 0 && upLeftC > 0) {
                    res[r][c] += sums[upLeftR - 1][upLeftC - 1];
                }
                if (upLeftR > 0) {
                    res[r][c] -= sums[upLeftR - 1][downRightC];
                }
                if (upLeftC > 0) {
                    res[r][c] -= sums[downRightR][upLeftC - 1];
                }
            }
        }
        
        return res;
    }

    /**
     * Start with the square of the sum, move it to the right by one column and on each move decrement the left
     * column and increment by right column.
     * @param mat
     * @param K
     * @return
     */
    public int[][] matrixBlockSum2(int[][] mat, int K) {
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
