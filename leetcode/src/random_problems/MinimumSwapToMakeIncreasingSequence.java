package random_problems;

/**
 * 801. Minimum Swaps To Make Sequences Increasing
 * Medium
 *
 * We have two integer sequences A and B of the same non-zero length.
 *
 * We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their
 * respective sequences.
 *
 * At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if
 * and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
 *
 * Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed
 * that the given input always makes it possible.
 *
 * Example:
 * Input: A = [1,3,5,4], B = [1,2,3,7]
 * Output: 1
 * Explanation:
 * Swap A[3] and B[3].  Then the sequences are:
 * A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
 * which are both strictly increasing.
 * Note:
 *
 * A, B are arrays with the same length, and that length will be in the range [1, 1000].
 * A[i], B[i] are integer values in the range [0, 2000].
 */
public class MinimumSwapToMakeIncreasingSequence {

    /**
     * Idea - keep 2 arrays of cost - one for keep existing value and second for the swapping. On i-th step
     * the cost is defined as cost at i-1 step plus the cost of current step.
     * If elements are sorted - first element stays the same and swapped will be prev + 1
     * if elements are reversed - first element = cost of prev swap, swapped elements - cost of original + 1
     * @param A
     * @param B
     * @return
     */
    public int minSwap(int[] A, int[] B) {
        int N = A.length;
        if (N <= 1)
            return 0;
        int[][] dp = new int[N][2];
        for (int i = 1; i < N; i++) {
            dp[i][0] = 2000;
            dp[i][1] = 2000;
        }

        dp[0][0] = 0;//value stays
        dp[0][1] = 1;//value swapped

        for (int i = 1 ; i < N ; i++) {
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][0]);
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][1] + 1);
            }

            if (A[i - 1] < B[i] && B[i - 1]< A[i]) {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][1]);
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + 1);
            }
        }

        return Math.min(dp[N - 1][0], dp[N - 1][1]);
    }
}
