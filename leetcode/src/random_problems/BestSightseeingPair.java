package random_problems;

/**
 * 1021. Best Sightseeing Pair
 * Medium
 *
 * Given an array A of positive integers, A[i] represents the value of the i-th sightseeing spot, and two sightseeing
 * spots i and j have distance j - i between them.
 *
 * The score of a pair (i < j) of sightseeing spots is (A[i] + A[j] + i - j) : the sum of the values of the
 * sightseeing spots, minus the distance between them.
 *
 * Return the maximum score of a pair of sightseeing spots.
 *
 *
 *
 * Example 1:
 *
 * Input: [8,1,5,2,6]
 * Output: 11
 * Explanation: i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 *
 *
 * Note:
 *
 * 2 <= A.length <= 50000
 * 1 <= A[i] <= 1000
 *
 */
public class BestSightseeingPair {

    /**
     * Idea: on every step the weight of the spot is decrementing. The idea of finding normal pair is to find greatest
     * sum. Here we need to decrement cur max element on every step
     *
     * res=max( res, A[i]-j+i+A[j] ), the cur is maintaining the maximum of A[i]-j+i, A[j]
     *
     * @param A
     * @return
     */
    public int maxScoreSightseeingPair(int[] A) {
        int N = A.length;

        int res = 0, cur = 0;
        for (int i = 0; i < N; i++) {
            //find current best sum
            res = Math.max(res, A[i] + cur);
            //find running best
            cur = Math.max(cur, A[i]) - 1;
        }

        return res;
    }
}
