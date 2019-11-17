package dp;

/**
 * 1027. Longest Arithmetic Sequence
 * Medium
 *
 * Given an array A of integers, return the length of the longest arithmetic subsequence in A.
 *
 * Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1,
 * and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
 *
 * Example 1:
 *
 * Input: [3,6,9,12]
 * Output: 4
 * Explanation:
 * The whole array is an arithmetic sequence with steps of length = 3.
 * Example 2:
 *
 * Input: [9,4,7,2,10]
 * Output: 3
 * Explanation:
 * The longest arithmetic subsequence is [4,7,10].
 * Example 3:
 *
 * Input: [20,1,15,3,10,5,8]
 * Output: 4
 * Explanation:
 * The longest arithmetic subsequence is [20,15,10,5].
 *
 *
 * Note:
 *
 * 2 <= A.length <= 2000
 * 0 <= A[i] <= 10000
 *
 */
public class LongestArithmeticSubsequence {

    /**
     * DP solution, dp[i][j] is the longest sequence of length i with dif j.
     * Iterate over every sub-array from 0 to i and check diff A[i] - A[j], also
     * count how many such element we've seen before.
     * Catch - because different can be negative we need limits from -10.000 to 10.000
     * @param A
     * @return
     */
    public int longestArithSeqLength(int[] A) {
        int res = 0;
        //array to keep differences and sequence length. dp[i][j] meaning is
        //sequence of length i and difference j. Because of the problem restrictions we can
        //allocate array for differences beforehand (0 <= A[i] <= 10000)
        int[][] dp = new int[A.length][20001];

        //set the right border of the sequence
        for (int i = 0; i < A.length; i++) {
            //start checking sequences by moving left border, so sequences from 0..i to i-1..i will
            //be checked
            for (int j = 0; j < i; j++) {
                //get the difference i and j elements, apply 10.000 shift so we can use array indexes
                int dif = (A[i] - A[j]) + 10000;
                //check if we have seen this difference before, if not - make it of length 2 (i and j makes at least
                //2 elements sequence)
                dp[i][dif] = (dp[j][dif] == 0 ? 1 : dp[j][dif]) + 1;
                //keep running max so we don't have to scan our dp matrix at the end
                res = Math.max(res, dp[i][dif]);
            }
        }
        return res;
    }
}
