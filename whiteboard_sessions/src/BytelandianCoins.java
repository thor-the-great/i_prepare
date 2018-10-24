/**
 * You have coin of value N. It can be cashed to N cash value or exchanged to 3 coins:
 * n/2, n/3 and n/4, with whole number division (trancated). So in theory exchange is more profitable
 * but not always.
 *
 * Calculate max number you can get from value N coin using sequentially mix of exchanges and/or cash operations
 */
public class BytelandianCoins {

    /**
     * Use bottom-up DP, plus if you think about states - 2 3 and 4 are essentially 2^1, 3 and 2^2, so everything can
     * be expressed via 2 and 3. So DP matrix is 2D array of powers of 2 and 3.
     *
     * @param N
     * @return
     */
    int bestDeal(int N) {
        int i = (int) Math.ceil(Math.log(N)/Math.log(2));
        int j = (int) Math.ceil(Math.log(N) / Math.log(3));

        int[][] dp = new int[i + 3][j + 2];

        for (int ii = i; ii >= 0; ii--) {
            for (int jj = j; jj >= 0; jj--) {
                int num = N / (int) (Math.pow(2, ii) * Math.pow(3, jj));
                dp[ii][jj] = Math.max(num, dp[ii + 1][jj] + dp[ii][jj + 1] + dp[ii + 2][jj]);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        BytelandianCoins obj = new BytelandianCoins();
        System.out.println(obj.bestDeal(100));
    }
}
