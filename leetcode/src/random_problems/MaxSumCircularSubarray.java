package random_problems;

/**
 * 918. Maximum Sum Circular Subarray
 * Medium
 *
 * Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.
 *
 * Here, a circular array means the end of the array connects to the beginning of the array.  (Formally, C[i] = A[i]
 * when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)
 *
 * Also, a subarray may only include each element of the fixed buffer A at most once.  (Formally, for a subarray
 * C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)
 *
 *
 *
 * Example 1:
 *
 * Input: [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3
 * Example 2:
 *
 * Input: [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
 * Example 3:
 *
 * Input: [3,-1,2,-1]
 * Output: 4
 * Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
 * Example 4:
 *
 * Input: [3,-2,2,-3]
 * Output: 3
 * Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
 * Example 5:
 *
 * Input: [-2,-3,-1]
 * Output: -1
 * Explanation: Subarray [-1] has maximum sum -1
 *
 *
 * Note:
 *
 * -30000 <= A[i] <= 30000
 * 1 <= A.length <= 30000
 */
public class MaxSumCircularSubarray {

    /**
     * Idea: answer is max of two - max sum of subarrays from 0 to N-1 (one interval) and max of array of 2 parts -
     * 0..i ___ j..N-1. Second part can be calculated as following - Sum [0...N-1] - Sum [i + 1..j - 1], where
     * Sum [0...N-1] is just sum and Sum [i + 1..j - 1] is max sum of subarray.
     * Sum [i + 1..j - 1] can be calculated as Kadane for elements *-1.
     * Catch - the whole 0..N-1 negatives will result in empty, so we need to calculate it twice - for 1..N-1 and
     * for 0..N-2.
     * @param A
     * @return
     */
    public int maxSubarraySumCircular(int[] A) {
        int N = A.length;
        if (N == 0)
            return 0;
        int sum = 0;
        int maxOneInterval = Integer.MIN_VALUE, curr = Integer.MIN_VALUE;
        for (int num : A) {
            curr = num + Math.max(0, curr);
            maxOneInterval = Math.max(maxOneInterval, curr);
            sum += num;
        }

        int maxTwoIntervals = sum + Math.max(kadaneNeg(A, 0, N - 2), kadaneNeg(A, 1, N -1));
        return Math.max(maxTwoIntervals, maxOneInterval);
    }

    int kadaneNeg(int[] arr, int start, int end) {
        int max = Integer.MIN_VALUE, curr = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            curr = -arr[i] + Math.max(0, curr);
            max = Math.max(max, curr);
        }
        return max;
    }
}
