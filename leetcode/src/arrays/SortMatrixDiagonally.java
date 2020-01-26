package arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1329. Sort the Matrix Diagonally
 * Medium
 *
 * Given a m * n matrix mat of integers, sort it diagonally in ascending order from the top-left to the bottom-right
 * then return the sorted array.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 */
public class SortMatrixDiagonally {

    /**
     * We can identify diagonal elements by checking dif between r and c for cell - for each diagonal this diff will
     * be unique. We can group diagonal elements together, then sort then iterate over every sorted collection and
     * fill the result matrix.
     * I'm saving last checked index of each diagonal in a separate array, increment for every next diagonal element.
     * Original matrix can be reused to store results.
     *
     * Few catches:
     * number of diagonals will be rows + cols - 1, so we can can allocate array instead of using some dynamic DS like
     * list or map
     * to use array indexes the diagonal id must start from 0. This is not always the case because we are using diff
     * r - c. To avoid negative indexes we can get the min possible diff and use it as offset, so smallest value
     * will start from 0 and max will be r + cols - 1.
     * @param mat
     * @return
     */
    public int[][] diagonalSort(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        int max = rows  + cols - 1;
        List<Integer>[] m = new ArrayList[max];
        int[] mIdx = new int[max];
        //add elements of each diagonal to corresponding list
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int k =  r - c + cols - 1;
                if (m[k] == null) {
                    m[k] = new ArrayList();
                }
                m[k].add(mat[r][c]);
            }
        }

        for (List<Integer> el : m)
            Collections.sort(el);

        //fill resulting matrix
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int k = r - c + cols - 1;
                //get last used index for this diagonal
                int idx = mIdx[k];
                //get element of the sorted list for this diagonal and index
                mat[r][c] = m[k].get(idx);
                //increment current index for diagonal
                ++mIdx[k];
            }
        }
        return mat;
    }
}
