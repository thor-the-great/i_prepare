package random_problems;

import java.util.Arrays;

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
     * Count identical nums excluding duplicates
     * @param A
     * @param B
     * @return
     */
    public int minDominoRotations(int[] A, int[] B) {
        int N = A.length;
        int[] countsA = new int[7];
        int[] countsB = new int[7];

        for (int i = 0; i < N; i++) {
            countsA[A[i]]+=1;
            if (B[i] != A[i])
                countsB[B[i]]+=1;
        }
        int min = -1;
        for (int i = 1; i <= 6; i++) {
            if (countsA[i] + countsB[i] >= N) {
                min = Math.min(countsA[i], countsB[i]);
                break;
            }
        }

        Arrays.fill(countsA, 0);
        Arrays.fill(countsB, 0);

        for (int i = 0; i < N; i++) {
            countsB[B[i]]+=1;
            if (A[i] != B[i])
                countsA[A[i]]+=1;
        }

        for (int i = 1; i <= 6; i++) {
            if (countsA[i] + countsB[i] >= N) {
                min = Math.min(min,
                        Math.min(countsA[i], countsB[i]));
                break;
            }
        }

        return min;
    }
}
