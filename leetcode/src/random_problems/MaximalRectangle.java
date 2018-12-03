package random_problems;

import java.util.Stack;

/**
 * 85. Maximal Rectangle
 * Hard
 *
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return
 * its area.
 *
 * Example:
 *
 * Input:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * Output: 6
 *
 */
public class MaximalRectangle {

    /**
     * Idea is following - base algo is calculation or max area rectangle for the histogram. For that
     * part we use Stack based solution.
     *
     * Second catch - we treat each row of the matrix as histogram array. Continue sweeping up-bottom row by row.
     * Each next row we re-calculate and keep track of max area. If previously 1-ed column became 0 - nullify it.
     * Otherwise - summarize it with previous columns.
     *
     */
    public int maximalRectangle(char[][] matrix) {
        int N = matrix[0].length;
        //calculate first row histo
        int[] flat = new int[N];
        for (int i =0; i < N; i++ ) {
            flat[i] = matrix[0][i] - '0';
        }
        //get max area of first row
        int res = getMaxArea(flat, N);
        for (int r = 1; r < matrix.length; r++) {
            //on each step recalculate flat version of histo, then re-calculate max area again
            recalcFlat(flat, matrix[r]);
            res = Math.max(res, getMaxArea(flat, N));
        }
        return res;
    }

    void recalcFlat(int[] flat, char[] nextRow) {
        for (int i = 0; i < flat.length; i++) {
            if (nextRow[i] == '1')
                flat[i] += 1;
            else
                flat[i] = 0;
        }
    }

    int getMaxArea(int[] arr, int N) {
        Stack<Integer> s = new Stack<>();
        s.push(-1);
        int res = 0;
        for(int i = 0; i < N; i++) {
            while(s.peek() != -1 && arr[i] <= arr[s.peek()]) {
                int p = s.pop();
                res = Math.max(res, arr[p] * (i - s.peek() - 1));
            }
            s.push(i);
        }
        while (s.peek() != -1) {
            int p = s.pop();
            res = Math.max(res, arr[p] * (N - s.peek() - 1));
        }
        return res;
    }

    public static void main(String[] args) {
        MaximalRectangle obj = new MaximalRectangle();
        System.out.println(obj.maximalRectangle(new char[][]{
                {'1', '0'}
        }));
    }
}
