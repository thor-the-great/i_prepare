package random_problems;

/**
 * 799. Champagne Tower
 * Medium
 *
 * We stack glasses in a pyramid, where the first row has 1 glass, the second row has 2 glasses, and so on until the
 * 100th row.  Each glass holds one cup (250ml) of champagne.
 *
 * Then, some champagne is poured in the first glass at the top.  When the top most glass is full, any excess liquid
 * poured will fall equally to the glass immediately to the left and right of it.  When those glasses become full,
 * any excess champagne will fall equally to the left and right of those glasses, and so on.  (A glass at the bottom
 * row has it's excess champagne fall on the floor.)
 *
 * For example, after one cup of champagne is poured, the top most glass is full.  After two cups of champagne are
 * poured, the two glasses on the second row are half full.  After three cups of champagne are poured, those two cups
 * become full - there are 3 full glasses total now.  After four cups of champagne are poured, the third row has the
 * middle glass half full, and the two outside glasses are a quarter full, as pictured below.
 *
 *
 *
 * Now after pouring some non-negative integer cups of champagne, return how full the j-th glass in the i-th row is
 * (both i and j are 0 indexed.)
 *
 *
 *
 * Example 1:
 * Input: poured = 1, query_glass = 1, query_row = 1
 * Output: 0.0
 * Explanation: We poured 1 cup of champange to the top glass of the tower (which is indexed as (0, 0)). There will be
 * no excess liquid so all the glasses under the top glass will remain empty.
 *
 * Example 2:
 * Input: poured = 2, query_glass = 1, query_row = 1
 * Output: 0.5
 * Explanation: We poured 2 cups of champange to the top glass of the tower (which is indexed as (0, 0)). There is one
 * cup of excess liquid. The glass indexed as (1, 0) and the glass indexed as (1, 1) will share the excess liquid
 * equally, and each will get half cup of champange.
 *
 *
 * Note:
 *
 * poured will be in the range of [0, 10 ^ 9].
 * query_glass and query_row will be in the range of [0, 99].
 *
 */
public class ChampagneTower {

    /**
     * Idea: to the simulation for every glass. The idea is - starting from the top each glass loses 2 part, each part
     * is [glass] - 1 / 2 (from each side). We compute how much liquid went down, for some glasses it will be combined.
     * catch 1: at the end we need to take min (1.0, glass) because for higher level glasses it can be more than 1.0
     * catch 2: we need only current and next layer of glasses
     *
     * @param poured
     * @param query_row
     * @param query_glass
     * @return
     */
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[2][100];
        dp[0][0] = (double) poured;
        for (int r = 0; r < query_row; r++) {
            for (int c =0; c <=r; c++) {
                double extra = (dp[0][c] - 1.0) / 2.0;
                if (extra > 0) {
                    dp[1][c] += extra;
                    dp[1][c + 1] += extra;
                }
            }
            dp[0] = dp[1];
            dp[1] = new double[100];
        }

        return Math.min(1.0, dp[0][query_glass]);
    }

    public static void main(String[] args) {
        ChampagneTower obj = new ChampagneTower();
        System.out.println(obj.champagneTower(6, 2,0));
    }
}
