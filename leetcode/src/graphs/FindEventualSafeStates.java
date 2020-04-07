package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 802. Find Eventual Safe States
 * Medium
 *
 * In a directed graph, we start at some node and every turn, walk along a directed edge of the
 * graph.  If we reach a node that is terminal (that is, it has no outgoing directed edges), we
 * stop.
 *
 * Now, say our starting node is eventually safe if and only if we must eventually walk to a
 * terminal node.  More specifically, there exists a natural number K so that for any choice of
 * where to walk, we must have stopped at a terminal node in less than K steps.
 *
 * Which nodes are eventually safe?  Return them as an array in sorted order.
 *
 * The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.
 * The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a
 * directed edge of the graph.
 *
 * Example:
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Here is a diagram of the above graph.
 *
 * Illustration of graph
 *
 * Note:
 *
 * graph will have length at most 10000.
 * The number of edges in the graph will not exceed 32000.
 * Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph
 * .length - 1].
 *
 */
public class FindEventualSafeStates {

  /**
   * Problem - if we've visited the node either we'll go into inf loop, or we exclude correct node.
   * For that starting backwards from node with 0 outdegree. Those are safe. Then go to nodes that
   * only connected to that safe nodes - those are safe too. Keep doing this untill we're out of
   * nodes.
   * O(N + E) time for graph traversal, O(NlgN) to sort the answer
   * O(N) space
   * @param graph
   * @return
   */
  public List<Integer> eventualSafeNodes(int[][] graph) {
    int N = graph.length;

    Set<Integer>[] g = new HashSet[N];
    Set<Integer>[] rg = new HashSet[N];

    Queue<Integer> q = new LinkedList();
    for (int i = 0; i < N; i++){
      int[] e = graph[i];
      if (e.length == 0) {
        q.add(i);
      }
      for (int to : e ) {
        if (g[i] == null) g[i] = new HashSet();
        g[i].add(to);
        if (rg[to] == null) rg[to] = new HashSet();
        rg[to].add(i);
      }
    }

    List<Integer> res = new ArrayList();
    while(!q.isEmpty()) {
      int next = q.poll();
      res.add(next);

      if (rg[next] != null) {
        for (int adj : rg[next]) {
          g[adj].remove(next);
          if (g[adj].isEmpty()) {
            q.add(adj);
          }
        }
      }
    }
    Collections.sort(res);
    return res;
  }
}
