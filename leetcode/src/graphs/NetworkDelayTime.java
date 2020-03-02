/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package graphs;

import java.util.Arrays;

/**
 * 743. Network Delay Time
 * Medium
 *
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the
 * source node, v is the target node, and w is the time it takes for a signal to travel from
 * source to target.
 *
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive
 * the signal? If it is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * Output: 2
 *
 *
 * Note:
 *
 * N will be in the range [1, 100].
 * K will be in the range [1, N].
 * The length of times will be in the range [1, 6000].
 * All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
 * Accepted
 * 66,690
 * Submissions
 * 147,557
 */
public class NetworkDelayTime {

  /**
   * Do bellman-ford algo, time O(E*N), space O(N)
   * @param times
   * @param N
   * @param K
   * @return
   */
  public int networkDelayTime(int[][] times, int N, int K) {
    long[] dist = new long[N];
    Arrays.fill(dist, Integer.MAX_VALUE);

    dist[K - 1] = 0;
    for (int n = 0; n < N; n++) {
      for (int[] t : times) {
        dist[t[1] - 1] = Math.min(dist[t[1] - 1],
            dist[t[0] - 1] + t[2]);
      }
    }

    long res = Integer.MIN_VALUE;
    for (long d : dist) {
      res = Math.max(res, d);
    }

    return res == Integer.MAX_VALUE ? -1 : (int) res;
  }
}
