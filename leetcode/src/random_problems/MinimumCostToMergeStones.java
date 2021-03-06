package random_problems;

/**
 * 1000. Minimum Cost to Merge Stones
 * Hard
 *
 * There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.
 *
 * A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is equal to the
 * total number of stones in these K piles.
 *
 * Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [3,2,4,1], K = 2
 * Output: 20
 * Explanation:
 * We start with [3, 2, 4, 1].
 * We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
 * We merge [4, 1] for a cost of 5, and we are left with [5, 5].
 * We merge [5, 5] for a cost of 10, and we are left with [10].
 * The total cost was 20, and this is the minimum possible.
 * Example 2:
 *
 * Input: stones = [3,2,4,1], K = 3
 * Output: -1
 * Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is
 * impossible.
 * Example 3:
 *
 * Input: stones = [3,5,1,2,6], K = 3
 * Output: 25
 * Explanation:
 * We start with [3, 5, 1, 2, 6].
 * We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
 * We merge [3, 8, 6] for a cost of 17, and we are left with [17].
 * The total cost was 25, and this is the minimum possible.
 *
 *
 * Note:
 *
 * 1 <= stones.length <= 30
 * 2 <= K <= 30
 * 1 <= stones[i] <= 100
 *
 */
public class MinimumCostToMergeStones {

    /**
     * Idea:
     * if we have 2 piles only - the cost will be the sum of every pile no matter what. Our task is to divide on
     * 2 piles optimally
     *
     * @param stones
     * @param K
     * @return
     */
    public int mergeStones(int[] stones, int K) {
        int N = stones.length;

        if ((N - 1) % (K - 1) != 0) return -1;

        int[][] dp = new int[N + 1][N + 1];
        int[] pref = new int[N + 1];
        for (int i = 1; i <= N; i++)
            pref[i] = pref[i - 1] + stones[i - 1];

        for (int l = K; l <= N; l++) {
            for (int i = 1; i <= N - l + 1; i++) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int mid = i; mid < j; mid+= (K - 1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid + 1][j]);
                }
                if ((j - i) % (K - 1) == 0)
                    dp[i][j] += pref[j] - pref[i - 1];
            }
        }

        return dp[1][N];
    }

}
