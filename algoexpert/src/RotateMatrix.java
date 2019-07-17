import utils.StringUtils;

/**
 * Rotate a Square Image Clockwise New
 *     Multi Dimensional Arrays
 * You are given a square 2D image matrix where each integer represents a pixel. Write a method
 * rotateSquareImageCW to rotate the image clockwise - in-place. This problem can be broken down
 * into simpler sub-problems you've already solved earlier! Rotating an image clockwise can be
 * achieved by taking the transpose of the image matrix and then flipping it on its vertical axis.
 *
 * Example:Input image :
 * 1 0
 * 1 0
 * Modified to :
 * 1 1
 * 0 0
 */
public class RotateMatrix {

    /**
     * Idea - algorithm has 2 steps.
     * 1 - transpose the matrix - this is easy, go in diagonals left-up to right-bot
     * 2 - flip the transposed matrix around vertical axis - go by columns, change
     * elements in same row between first and last columns, then move column pointers closer to
     * center
     * @param matrix
     */
    public static void rotateSquareImageCW(int[][] matrix) {
        if (matrix == null || matrix.length < 2)
            return;

        int N = matrix.length;
        //transpose
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //flip on vertical axis
        for (int c = 0; c < N/2; c++) {
            for (int r = 0; r < N; r++) {
                int tmp = matrix[r][c];
                matrix[r][c] = matrix[r][N - 1 - c];
                matrix[r][N - 1 - c] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        rotateSquareImageCW(matrix);

        System.out.println(StringUtils.int2DArrayToString(matrix));
    }
}
