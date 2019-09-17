package random_problems;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 57. Campus Bikes
 * Medium
 *
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each
 * worker and bike is a 2D coordinate on this grid.
 *
 * Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose
 * the (worker, bike) pair with the shortest Manhattan distance between each other, and assign
 * the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest
 * Manhattan distance, we choose the pair with the smallest worker index; if there are multiple
 * ways to do that, we choose the pair with the smallest bike index). We repeat this process
 * until there are no available workers.
 *
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1
 * .y - p2.y|.
 *
 * Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the
 * i-th worker is assigned to.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * Output: [1,0]
 * Explanation:
 * Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So
 * the output is [1, 0].
 * Example 2:
 *
 *
 *
 * Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 * Output: [0,2,1]
 * Explanation:
 * Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus
 * Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
 *
 *
 * Note:
 *
 * 0 <= workers[i][j], bikes[i][j] < 1000
 * All worker and bike locations are distinct.
 * 1 <= workers.length <= bikes.length <= 1000
 */
public class CampusBikes {

  /**
   * Need to calculate distance between each worker and bike, then sort them asc and iterate. It will
   * work with priority queue, but slow due to sorting.
   * Catch is the limit of possible distances. It's just 2000, so we can use counting sort.
   * @param workers
   * @param bikes
   * @return
   */
  public int[] assignBikes(int[][] workers, int[][] bikes) {
    //this array will hold pairs sorted by distance - index in array is distance and list of ints
    //is the list of pairs of worker-bike that have this distance\
    ArrayList<int[]>[] dist = new ArrayList[2001];
    int wlen = workers.length;
    int blen = bikes.length;
    //this will hold the resutling array
    int[] res = new int[wlen];
    //this is for us to check if bike has been used
    boolean[] seenBikes = new boolean[blen];
    //fill result array with -1 we can identify worker that hasn't been processed
    Arrays.fill(res, -1);

    //fill array of distances with pairs of bike-worker. Because for loops start with bike and then
    //with worker the condition of the problems satisfied - worker with smaller index will be always
    //first, then bikes
    for (int w = 0; w < wlen; w++) {
      for (int b = 0; b < blen; b++) {
        int d = Math.abs(workers[w][0] - bikes[b][0]) + Math.abs(workers[w][1] - bikes[b][1]);
        if (dist[d] == null) {
          dist[d] = new ArrayList();
        }
        dist[d].add(new int[] {b, w});
      }
    }

    //this counts how many workers has been assigned so far
    int c = 0;

    for (int d = 0; d <= 2000; d++) {
      if (c == blen)
        break;
      //if we haven't met any pair with this distance
      if (dist[d] == null) continue;
      for (int[] pair : dist[d]) {
        //check if worker hasn't been assigned yet and if bike hasn't been used
        if (res[pair[1]] == -1 && !seenBikes[pair[0]]) {
          res[pair[1]] = pair[0];
          seenBikes[pair[0]] = true;
          //increment count of workers with bikes and check if need to break from loop
          c++;
          if (c == blen)
            break;
        }
      }
    }
    return res;
  }
}
