package path.google;

import java.util.List;

/**
 * 120. Triangle
 * Medium
 *
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the
 * row below.
 *
 * For example, given the following triangle
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 *
 * Note:
 *
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the
 * triangle.
 */
public class PathInTriangle {

    /**
     * Idea: doing bottom-up DP. Path to each cell is path from higher level cell + min between 2 neighbors in current
     * layer
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int N = triangle.size();
        int[] dp = new int[N];
        List<Integer> lastLayer = triangle.get(N - 1);
        for (int i = 0; i < N; i++) {
            dp[i] = lastLayer.get(i);
        }
        for (int l = N -2; l >= 0; l--) {
            List<Integer> layer = triangle.get(l);
            for (int i = 0; i < layer.size(); i++) {
                dp[i] = Math.min(dp[i], dp[i + 1]) + layer.get(i);
            }
        }
        return dp[0];
    }
}
