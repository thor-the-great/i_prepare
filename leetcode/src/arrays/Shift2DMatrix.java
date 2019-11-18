package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * 1260. Shift 2D Grid
 * Easy
 *
 * Given a 2D grid of size n * m and an integer k. You need to shift the grid k times.
 *
 * In one shift operation:
 *
 * Element at grid[i][j] becomes at grid[i][j + 1].
 * Element at grid[i][m - 1] becomes at grid[i + 1][0].
 * Element at grid[n - 1][m - 1] becomes at grid[0][0].
 * Return the 2D grid after applying shift operation k times.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * Output: [[9,1,2],[3,4,5],[6,7,8]]
 * Example 2:
 *
 *
 * Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
 * Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
 * Example 3:
 *
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
 * Output: [[1,2,3],[4,5,6],[7,8,9]]
 *
 *
 * Constraints:
 *
 * 1 <= grid.length <= 50
 * 1 <= grid[i].length <= 50
 * -1000 <= grid[i][j] <= 1000
 * 0 <= k <= 100
 */
public class Shift2DMatrix {

    /**
     * In fact the shift is like starting from (rows * cols - k) and make it 0 in a new shifted grid. Start from 0 in
     * new grid and calculate r,c that we need to take for it starting from (rows * cols - k)
     *
     * @param grid
     * @param k
     * @return
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> res = new ArrayList();
        int rows = grid.length, cols = grid[0].length;
        //fill rows of result with empty lists
        for (int r = 0; r < rows; r++)
            res.add(new ArrayList());
        //every rows*cols shifts grid became the same, so cut off unnecessary shifts
        k %= (rows*cols);

        int dim = rows * cols;
        //element at (start) will be at 0,0 in new grid
        int start = dim - k;
        //this is a counter for our new grid
        int idx = 0;
        for (int i = start; i < start + dim; i++) {
            //calculate row and cell from which we take an element for new grid
            int r = (i / cols)%rows, c = i % cols;
            //get proper new row and add new element
            res.get(idx / cols).add(grid[r][c]);
            idx++;
        }
        return res;
    }
}
