package random_problems;

/**
 * 974. Subarray Sums Divisible by K
 * Medium
 *
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
 *
 * Example 1:
 *
 * Input: A = [4,5,0,-2,-3,1], K = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by K = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 *
 * Note:
 *
 * 1 <= A.length <= 30000
 * -10000 <= A[i] <= 10000
 * 2 <= K <= 10000
 */
public class SubarraySumsDivisiblebyK {

    /**
     * Idea: start from array of prefixes (sums).
     * Then next idea - if sum is mod K = x for two such sums then mod K for two elements in sums is the same.
     * So check how many different mod K values we have in sums - put it to counts
     * Then compile answer - for every match it's possible to compile (c - 1)*c/2 arrays.
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK(int[] A, int K) {
        int[] sums = new int[A.length + 1];
        for (int i = 0; i < A.length; i++) {
            sums[i + 1] = A[i] + sums[i];
        }

        int[] counts = new int[K];
        for (int s : sums) {
            int m = s % K;
            if (s < 0) {
                m += K;
                m %= K;
            }
            counts[m]++;
        }

        int res = 0;
        for (int count : counts) {
            if (count > 1) {
                res += count * (count - 1) / 2;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        SubarraySumsDivisiblebyK obj = new SubarraySumsDivisiblebyK();
        System.out.println(obj.subarraysDivByK(new int[] {4,5,0,-2,-3,1}, 5));
    }
}
