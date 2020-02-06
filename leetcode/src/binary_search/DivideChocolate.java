package binary_search;

/**
 * 1231. Divide Chocolate
 * Hard
 *
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
 *
 * You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using
 * K cuts, each piece consists of some consecutive chunks.
 *
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
 *
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
 * Output: 6
 * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
 * Example 2:
 *
 * Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
 * Output: 1
 * Explanation: There is only one way to cut the bar into 9 pieces.
 * Example 3:
 *
 * Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
 * Output: 5
 * Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 *
 *
 * Constraints:
 *
 * 0 <= K < sweetness.length <= 10^4
 * 1 <= sweetness[i] <= 10^5
 */
public class DivideChocolate {

    /**
     * max possible sum can be max value = 10^4*10^5 = 10^9 divided by number of cuts + 1 - 10^9/(K + 1), min = 1
     * we can do binary search from 1.. to ..10^9/(K + 1). For each max sum we check on how many cuts we can cut -
     * iterate over array and cut, break to next cut when reach m. Count number of cuts, if cuts > K it means we
     * choose m to high, otherwise too small.
     *
     * @param sweetness
     * @param K
     * @return
     */
    public int maximizeSweetness(int[] sweetness, int K) {
        int l = 1, r = 1_000_000_000/(K + 1);
        while (l < r) {
            int m = (l + r + 1)/2;
            int cuts = 0, t = 0;
            for (int num : sweetness) {
                t+=num;
                if (t >= m) {
                    ++cuts;
                    if (cuts > K)
                        break;
                    t = 0;
                }
            }
            if (cuts > K)
                l = m;
            else
                r = m - 1;
        }
        return l;
    }
}
