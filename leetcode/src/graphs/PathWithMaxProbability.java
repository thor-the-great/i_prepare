package graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1514. Path with Maximum Probability
 * Medium
 *
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
 *
 * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
 *
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
 * Output: 0.25000
 * Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
 * Example 2:
 *
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
 * Output: 0.30000
 * Example 3:
 *
 *
 *
 * Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
 * Output: 0.00000
 * Explanation: There is no path between 0 and 2.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 10^4
 * 0 <= start, end < n
 * start != end
 * 0 <= a, b < n
 * a != b
 * 0 <= succProb.length == edges.length <= 2*10^4
 * 0 <= succProb[i] <= 1
 * There is at most one edge between every two nodes.
 */
public class PathWithMaxProbability {

  /**
   * Dijkstra algorithm, start from start, do until reach end or until priority queue became empty
   *
   * @param n
   * @param edges
   * @param succProb
   * @param start
   * @param end
   * @return
   */
  public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
    List<Edge>[] graph = new ArrayList[n];
    for (int i = 0; i < edges.length; i++) {
      int[] edge = edges[i];
      if (graph[edge[0]] == null) {
        graph[edge[0]] = new ArrayList();
      }
      graph[edge[0]].add(new Edge(edge[1], succProb[i]));

      if (graph[edge[1]] == null) {
        graph[edge[1]] = new ArrayList();
      }
      graph[edge[1]].add(new Edge(edge[0], succProb[i]));
    }

    double[] paths = new double[n];
    paths[start] = 1.0;

    Comparator<Integer> comp = (i1, i2) -> Double.compare(paths[i2], paths[i1]);
    PriorityQueue<Integer> pq = new PriorityQueue(comp);
    pq.add(start);

    while(!pq.isEmpty()) {
      int node = pq.poll();
      if (node == end) {
        return paths[end];
      }

      if (graph[node] != null) {
        for (Edge e : graph[node]) {
          if (paths[e.to] < paths[node]*e.prob) {
            paths[e.to] = paths[node]*e.prob;
            pq.add(e.to);
          }
        }
      }
    }

    return 0.0;
  }

  class Edge {
    double prob;
    int to;
    int from;

    Edge(int to, double prob) {
      this.to = to;
      this.prob = prob;
    }
  }
}
