package dp;

/**
 * 1259. Handshakes That Don't Cross
 * Hard
 *
 * You are given an even number of people num_people that stand around a circle and each person shakes hands with
 * someone else, so that there are num_people / 2 handshakes total.
 *
 * Return the number of ways these handshakes could occur such that none of the handshakes cross.
 *
 * Since this number could be very big, return the answer mod 10^9 + 7
 *
 *
 *
 * Example 1:
 *
 * Input: num_people = 2
 * Output: 1
 * Example 2:
 *
 *
 *
 * Input: num_people = 4
 * Output: 2
 * Explanation: There are two ways to do it, the first way is [(1,2),(3,4)] and the second one is [(2,3),(4,1)].
 * Example 3:
 *
 *
 *
 * Input: num_people = 6
 * Output: 5
 * Example 4:
 *
 * Input: num_people = 8
 * Output: 14
 *
 *
 * Constraints:
 *
 * 2 <= num_people <= 1000
 * num_people % 2 == 0
 */
public class HandshakeThatDontCross {

    /**
     * Draw a picture - if you choose one guy the handshake possible will be with every 2-nd element (odds are skipped
     * because this way we'll leave one guy without shake. Then for every of those two parts calculate the num of
     * shakes
     *
     * For for n people, there are n/2 way for perosn 1 to shake hand. If person 1 shake hand with person k, there are
     * count(2 to k-1)*count(k+1 to n) scenarios
     * If we store an array cache where cache[i] denotes numbers of way when there are i people. Then:
     * count(2 to k-1)*count(k+1 to n) = cache[k-2]*cache[n-k]
     */
    public int numberOfWays(int num_people) {
        long mod = (long)1_000_000_007;
        long[] dp = new long[(num_people/2) + 1];
        dp[0] = 1;

        for (int num = 1; num <= num_people/2; num++) {
            for (int j = 0; j < num; j++) {
                dp[num] = (dp[num] + dp[j]*dp[num - j - 1]) % mod;
            }
        }

        return (int)dp[num_people/2];
    }
}
