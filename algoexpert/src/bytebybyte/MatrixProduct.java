package bytebybyte;

/**
 * Coding Interview Question: Matrix Product
 * Matrix Product: Coding Interview Question
 *
 * Question
 * Given a matrix, find the path from top left to bottom right with the greatest product by moving only down and right.
 *
 * eg.
 *
 * [1, 2, 3]
 * [4, 5, 6]
 * [7, 8, 9]
 *
 * 1 -> 4 -> 7 -> 8 -> 9
 * 2016
 *
 * [-1, 2, 3]
 * [4, 5, -6]
 * [7, 8, 9]
 *
 * -1 -> 4 -> 5 -> -6 -> 9
 * 1080
 *
 * [1, 2, 3]
 * [4, 5, 6]
 * [7, 8, 9]
 *
 * 1 -> 4 -> 7 -> 8 -> 9
 * 2016
 *
 * [-1, 2, 3]
 * [4, 5, -6]
 * [7, 8, 9]
 *
 * -1 -> 4 -> 5 -> -6 -> 9
 * 1080
 */
public class MatrixProduct {

    /**
     * Doing DP, catch is to keep min and max matrix because grid can have negative values. Each step try min and max
     * at the same time
     * @param matrix
     * @return
     */
    int product(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[][] min = new int[rows][cols], max = new int[rows][cols];
        min[0][0] = matrix[0][0];
        max[0][0] = matrix[0][0];

        for (int r = 1; r < rows; r++) {
            max[r][0] = max[r - 1][0] * matrix[r][0];
            min[r][0] = min[r - 1][0] * matrix[r][0];
        }

        for (int c = 1; c < cols; c++) {
            max[0][c] = max[0][c - 1] * matrix[0][c];
            min[0][c] = min[0][c - 1] * matrix[0][c];
        }

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                max[r][c] = Math.max(
                        Math.max(
                            matrix[r][c]*max[r - 1][c],
                            matrix[r][c]*max[r][c - 1]),
                        Math.max(
                                matrix[r][c]*min[r - 1][c],
                                matrix[r][c]*min[r][c - 1])
                        );
                min[r][c] = Math.min(
                        Math.min(
                                matrix[r][c]*max[r - 1][c],
                                matrix[r][c]*max[r][c - 1]),
                        Math.min(
                                matrix[r][c]*min[r - 1][c],
                                matrix[r][c]*min[r][c - 1])
                );
            }
        }

        return Math.max(max[rows - 1][cols - 1], min[rows - 1][cols -1]);
    }

    public static void main(String[] args) {
        MatrixProduct obj = new MatrixProduct();
        System.out.println(
                obj.product(new int[][] {
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}})
        );

        System.out.println(
                obj.product(new int[][] {
                        {-1, 2, 3},
                        {4, 5, -6},
                        {7, 8, 9}})
        );
    }
}
