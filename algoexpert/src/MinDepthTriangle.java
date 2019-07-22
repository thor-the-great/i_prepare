import java.util.ArrayList;
import java.util.List;

/**
 * Minimum Sum Path in a Triangle New
 *     Arrays Puzzles Multi Dimensional Arrays
 *
 * Given a 'triangle' as an ArrayList of ArrayLists of integers, with each list representing a level of the triangle,
 * find the minimum sum achieved by following a top-down path and adding the integer at each level along the path.
 * Movement is restricted to adjacent numbers from the top to the bottom.
 *
 * Note:
 * - You can only traverse through adjacent nodes while moving up or down the triangle.
 * - An adjacent node is defined as a node that is reached by moving down and left or down and right from a level. For eg, in the triangle shown below, if you are at the digit 3 in the second row, its adjacent nodes are 5 and 6
 *
 * Example:
 * Input Triangle:
 * [   [1],
 *    [2, 3],
 *   [4, 5, 6],
 * [7, 8, 9, 10],
 * ]
 *
 * Output : 14 (1->2->4->7)
 * Note: [..] denotes an ArrayList
 */
public class MinDepthTriangle {

    /**
     * Use dp, bottom-up. Each element represents initially the cost of going through that point. then start moving
     * upward. Each prev level point can add to the path itself plus the min of the one level below and below and to the
     * right.
     * Keep building the path unless we reach the top level that has only one cell.
     * Catch - shift the triangle to the left, this simplifies reasoning and calculations of the indexes
     * @param input
     * @return
     */
    public static int minTriangleDepth(ArrayList<ArrayList<Integer>> input) {
        if (input == null)
            return 0;

        int N = input.size();
        int[] dp = new int[N];
        //fill the dp array with the initial numbers - costs of the lowest level
        for (int i = 0; i < N; i++) {
            dp[i] = input.get(N-1).get(i);
        }

        //start sweeping bottom-up.
        for(int i = N - 2; i >= 0; i--) {
            List<Integer> row = input.get(i);
            for (int j = 0; j <= i; j++) {
                dp[j] = row.get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }

        return dp[0];
    }
}
