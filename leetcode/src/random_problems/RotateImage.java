package random_problems;

/**
 * 48. Rotate Image
 * Medium
 *
 * You are given an n x n 2D matrix representing an image.
 *
 * Rotate the image by 90 degrees (clockwise).
 *
 * Note:
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate
 * another 2D matrix and do the rotation.
 *
 * Example 1:
 *
 * Given input matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 * Example 2:
 *
 * Given input matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 */
public class RotateImage {

    /**
     * Idea: We can exchange 4 elements in-place using one temporary variable. Do this in cycle for every "level"
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int N = matrix.length;
        int level = N / 2;

        for (int l = 0; l < level; l++) {
            int first = l;
            int last = N - l - 1;
            for (int i = first; i < last; i++) {
                int offset = i  - first;

                //save top-left
                int t = matrix[first][i];

                //move bot-left to top-left
                matrix[first][i] = matrix[last - offset][first];

                //move bot-right -> bot-left
                matrix[last - offset][first] = matrix[last][last - offset];

                //move up-right -> bot-right
                matrix[last][last - offset] = matrix[i][last];

                //move bot-left (tmp) -> up-right
                matrix[i][last] = t;
            }
        }
    }

    public static void main(String[] args) {
        RotateImage obj = new RotateImage();
        int[][] image;
        image = new int[][] {
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };
        obj.rotate(image);
        System.out.println(utils.StringUtils.int2DArrayToString(image));
        image = new int[][] {
                {1,  2,  3,  4},
                {5,  6,  7,  8},
                {9, 10, 11, 12},
                {13,14, 15, 16}
        };
        obj.rotate(image);
        System.out.println(utils.StringUtils.int2DArrayToString(image));
    }
}
