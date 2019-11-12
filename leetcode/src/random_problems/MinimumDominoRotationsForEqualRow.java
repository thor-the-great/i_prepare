package random_problems;

/**
 * 1007. Minimum Domino Rotations For Equal Row
 * Medium
 *
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile
 * with two numbers from 1 to 6 - one on each half of the tile.)
 *
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 *
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 *
 * If it cannot be done, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 * Output: 2
 * Explanation:
 * The first figure represents the dominoes as given by A and B: before we do any rotations.
 * If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by
 * the second figure.
 * Example 2:
 *
 * Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
 * Output: -1
 * Explanation:
 * In this case, it is not possible to rotate the dominoes to make one row of values equal.
 *
 *
 * Note:
 *
 * 1 <= A[i], B[i] <= 6
 * 2 <= A.length == B.length <= 20000
 */
public class MinimumDominoRotationsForEqualRow {

    /**
     * Check every value out of 1..6, check every option - four possible - either domino is set correctly, or swap required
     * or it's not possible. Keep min count of every swap for the number
     * @param A
     * @param B
     * @return
     */
    public int minDominoRotations(int[] A, int[] B) {
        int N = A.length;
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= 6; i++) {
            int c1 = 0, c2 = 0;
            for (int n = 0; n < N; n++) {
                if (A[n] != i && B[n] == i) {
                    c1++;
                } else if (A[n] == i && B[n] != i) {
                    c2++;
                } else if (A[n] == i && B[n] == i)
                    continue;
                else {
                    c1 = Integer.MAX_VALUE; c2 = Integer.MAX_VALUE;
                    break;
                }
            }
            res = Math.min(res, c1);
            res = Math.min(res, c2);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
