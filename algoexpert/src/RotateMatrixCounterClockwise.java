/**
 * Rotate a Square Image Counterclockwise New
 *     Multi Dimensional Arrays
 * You are given a square 2D image matrix where each integer represents a pixel. Write a method
 * rotateSquareImageCCW to rotate the image counterclockwise - in-place.
 *
 * This problem can be broken down into simpler sub-problems you've already solved earlier! Rotating an image
 * counterclockwise can be achieved by taking the transpose of the image matrix and then flipping it on its
 * horizontal axis. Source: en.wikipedia.org/wiki/Transpose
 *
 * Example:
 * Input image :
 * 1 0
 * 1 0
 * Modified to :
 * 0 0
 * 1 1
 */
public class RotateMatrixCounterClockwise {
    public static void rotateSquareImageCCW(int[][] matrix) {
        if (matrix == null)
            return;

        int N = matrix.length;

        //transpose
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }

        //flip horiz
        for (int r = 0; r < N/2; r++) {
            for (int c = 0; c < N; c++) {
                int t = matrix[r][c];
                matrix[r][c] = matrix[N - 1 - r][c];
                matrix[N - 1 - r][c] = t;
            }
        }
    }
}
