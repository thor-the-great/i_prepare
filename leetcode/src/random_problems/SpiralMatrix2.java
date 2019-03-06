package random_problems;

/**
 * 59. Spiral Matrix II
 * Medium
 *
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 *
 * Example:
 *
 * Input: 3
 * Output:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 */
public class SpiralMatrix2 {

    /**
     * Just do the iterations
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];

        int r1 = 0; int r2 = n - 1;
        int c1 = 0; int c2 = n - 1;
        int num = 1;

        while (r1 <= r2 && c1 <= c2) {
            for (int i = c1; i <= c2; i++)
                res[r1][i] = num++;
            r1++;

            for (int i = r1; i <= r2; i++)
                res[i][c2] = num++;
            c2--;

            for (int i = c2; i >= c1; i--)
                res[r2][i] = num++;
            r2--;

            for (int i = r2; i >= r1; i--)
                res[i][c1] = num++;
            c1++;
        }

        return res;
    }
}
