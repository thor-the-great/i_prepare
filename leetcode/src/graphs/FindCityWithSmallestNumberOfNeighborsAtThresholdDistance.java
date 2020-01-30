package graphs;

/**
 * 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
 * Medium
 *
 * There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi,
 * weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given
 * the integer distanceThreshold.
 *
 * Return the city with the smallest number of cities that are reachable through some path and
 * whose distance is at most distanceThreshold, If there are multiple such cities, return the
 * city with the greatest number.
 *
 * Notice that the distance of a path connecting cities i and j is equal to the sum of the edges'
 * weights along that path.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
 * Output: 3
 * Explanation: The figure above describes the graph.
 * The neighboring cities at a distanceThreshold = 4 for each city are:
 * City 0 -> [City 1, City 2]
 * City 1 -> [City 0, City 2, City 3]
 * City 2 -> [City 0, City 1, City 3]
 * City 3 -> [City 1, City 2]
 * Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return
 * city 3 since it has the greatest number.
 * Example 2:
 *
 *
 *
 * Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
 * Output: 0
 * Explanation: The figure above describes the graph.
 * The neighboring cities at a distanceThreshold = 2 for each city are:
 * City 0 -> [City 1]
 * City 1 -> [City 0, City 4]
 * City 2 -> [City 3, City 4]
 * City 3 -> [City 2, City 4]
 * City 4 -> [City 1, City 2, City 3]
 * The city 0 has 1 neighboring city at a distanceThreshold = 2.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 100
 * 1 <= edges.length <= n * (n - 1) / 2
 * edges[i].length == 3
 * 0 <= fromi < toi < n
 * 1 <= weighti, distanceThreshold <= 10^4
 * All pairs (fromi, toi) are distinct.
 */
public class FindCityWithSmallestNumberOfNeighborsAtThresholdDistance {

    /**
     * Find shortest path from every city to every other city by using Floyd-Warshall algo.
     * Then check for every city distance to every other city and if it's less then threshold
     * save it as a running min along with the city index
     * @param n
     * @param edges
     * @param distanceThreshold
     * @return
     */
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        //array to hold distances between cities
        int[][] dp = new int[n][n];
        //init with infinity
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                //max value - as per statement we may have n^2 length max, so do 10x as a limit
                dp[r][c] = 100_000;
            }
        }
        //init existing edges
        for (int[] e : edges) {
            dp[e[0]][e[1]] = e[2];
            dp[e[1]][e[0]] = e[2];
        }
        //calculate shortest path with bellman-ford
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        //count cities with min reachable cities within the threshold
        int min = Integer.MAX_VALUE, city = -1;
        for (int i = 0; i < n; i++) {
            int c = 0;
            //for city i check every other city j
            for (int j = 0; j < n; j++) {
                //if the distance between i and j is <= distanceThreshold increment
                //count for this city
                if (i != j && dp[i][j] <= distanceThreshold)
                    ++c;
                //if num of cities increased higher than the current min - other iterations don't make sense
                if (c > min)
                    break;
            }
            //compare if we have found next min
            if (c <= min) {
                min = c;
                city = i;
            }
        }
        return city;
    }
}
