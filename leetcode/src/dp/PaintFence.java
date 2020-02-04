package dp;

/**
 * 276. Paint Fence
 * Easy
 *
 * There is a fence with n posts, each post can be painted with one of the k colors.
 *
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 *
 * Return the total number of ways you can paint the fence.
 *
 * Note:
 * n and k are non-negative integers.
 *
 * Example:
 *
 * Input: n = 3, k = 2
 * Output: 6
 * Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:
 *
 *             post1  post2  post3
 *  -----      -----  -----  -----
 *    1         c1     c1     c2
 *    2         c1     c2     c1
 *    3         c1     c2     c2
 *    4         c2     c1     c1
 *    5         c2     c1     c2
 *    6         c2     c2     c1
 */
public class PaintFence {

    /**
     * DP, count base cases up to 2 posts, then rest follows. Two main cases:
     * near posts are either same or different colors
     * @param n
     * @param k
     * @return
     */
    public int numWays(int n, int k) {
        //0 posts - no possible to paint at all
        if (n == 0)
            return 0;
        //1 post possible to point in any color
        if (n == 1)
            return k;
        //2 posts are possible to paint either same or different colors
        //same color - choose any of k, then same - k*1 = k
        //different - pick k, then one of the rest - k-1 = k*(k-1)
        int same = k, diff = k * (k -1);
        for (int i = 3; i <= n; i++) {
            int oldDiff = diff;
            //diff colors - possible from both same and diff,
            //just the post need to be k-1 color
            diff = (diff + same)*(k - 1);
            //same not possible with old same, just with old diff
            same = oldDiff;
        }
        return same + diff;
    }
}
