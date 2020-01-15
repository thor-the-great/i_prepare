package dp;

/**
 * 1066. Campus Bikes II
 * Medium
 *
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D
 * coordinate on this grid.
 *
 * We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their
 * assigned bike is minimized.
 *
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 *
 * Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * Output: 6
 * Explanation:
 * We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6.
 * Example 2:
 *
 *
 *
 * Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 * Output: 4
 * Explanation:
 * We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2, bike 2 to worker 2 or worker 1.
 * Both assignments lead to sum of the Manhattan distances as 4.
 *
 *
 * Note:
 *
 * 0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
 * All worker and bike locations are distinct.
 * 1 <= workers.length <= bikes.length <= 10
 */
public class CampusBikes2 {

    /**
     * Start from initial solution with backtracking. After analysis it appears that some paths are repeated, so we
     * cam memorize it. Use map where key is the "used" bikes state and value is min distance. Then we can
     * convert map to array due to small range < 10 of bikes and workers possible
     * @param workers
     * @param bikes
     * @return
     */
    public int assignBikes(int[][] workers, int[][] bikes) {
        return helper(workers, bikes, 0, new boolean[bikes.length], new int[1<<bikes.length]);
    }

    int helper(int[][] workers, int[][] bikes, int w, boolean[] bikesV, int[] memo) {
        //base case - when all workers checked return 0
        if (w == workers.length)
            return 0;
        //this is key for current selected bikes state. because there are no more then 10 we can use a single int to
        //store state of every bike as a single bit
        int key = key(bikesV);
        //if we checked this state before - just take it from cache
        if (memo[key] > 0)
            return memo[key];
        //start checking all unused bikes
        memo[key]=Integer.MAX_VALUE;
        for (int bb = 0; bb < bikes.length; bb++) {
            if (bikesV[bb])
                continue;
            //found unused one, mark as used, increment worker count and check the next one
            bikesV[bb] = true;
            int dd = helper(workers, bikes, w + 1, bikesV, memo) + dist(workers, bikes, w, bb);
            bikesV[bb] = false;
            //save this distance if it's a min distance so far
            memo[key] = Math.min(dd, memo[key]);
        }
        return memo[key];
    }

    int dist(int[][] workers, int[][] bikes, int w, int b) {
        return Math.abs(bikes[b][0] - workers[w][0]) + Math.abs(bikes[b][1] - workers[w][1]);
    }

    int key(boolean[] visited) {
        //this is state of the currently used bikes in form of a bit mask in a single integer
        int res = 0;
        int mask = 1;
        for (int i = 0; i < visited.length; i++)   {
            if (visited[i]) {
                res |= mask;
            }
            mask<<=1;
        }
        return res;
    }
}
