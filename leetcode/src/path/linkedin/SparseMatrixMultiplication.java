package path.linkedin;

/**
 * 311. Sparse Matrix Multiplication
 * Medium
 *
 * Given two sparse matrices A and B, return the result of AB.
 *
 * You may assume that A's column number is equal to B's row number.
 *
 * Example:
 *
 * Input:
 *
 * A = [
 *   [ 1, 0, 0],
 *   [-1, 0, 3]
 * ]
 *
 * B = [
 *   [ 7, 0, 0 ],
 *   [ 0, 0, 0 ],
 *   [ 0, 0, 1 ]
 * ]
 *
 * Output:
 *
 *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 *                   | 0 0 1 |
 */
public class SparseMatrixMultiplication {

    /**
     * Idea - check how every element of result matrix is formed. if we going rows-columns nested loop is for columns
     * then every first element will be A[0][1], then A[1][0] etc. It means by checking it for 0 we can cut the whole
     * iteration
     * @param A
     * @param B
     * @return
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int rows1 = A.length;
        int cols1 = A[0].length;
        int cols2 = B[0].length;
        int[][] res = new int[rows1][cols2];
        for (int r = 0; r < rows1; r++) {
            for (int c = 0; c < cols1; c++) {
                if (A[r][c] != 0) {
                    for (int i = 0; i < cols2; i++) {
                        res[r][i] += A[r][c]*B[c][i];
                    }
                }
            }
        }
        return res;
    }
}
