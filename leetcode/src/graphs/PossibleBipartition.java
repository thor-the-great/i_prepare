package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 886. Possible Bipartition
 * Medium
 *
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two
 * groups of any size.
 *
 * Each person may dislike some other people, and they should not go into the same group.
 *
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and
 * b into the same group.
 *
 * Return true if and only if it is possible to split everyone into two groups in this way.
 *
 *
 *
 * Example 1:
 *
 * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4], group2 [2,3]
 * Example 2:
 *
 * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * Example 3:
 *
 * Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 *
 *
 * Note:
 *
 * 1 <= N <= 2000
 * 0 <= dislikes.length <= 10000
 * 1 <= dislikes[i][j] <= N
 * dislikes[i][0] < dislikes[i][1]
 * There does not exist i != j for which dislikes[i] == dislikes[j].
 */
public class PossibleBipartition {

  /**
   * Do 2 colors coloring, if neighbours are of the same color - bi-partition is not possible. If
   * we traversed all nodes and haven't bumped into issues - bi-partition is possible, return
   * true
   * @param N
   * @param dislikes
   * @return
   */
  public boolean possibleBipartition(int N, int[][] dislikes) {
    //create graph
    List<Integer>[] graph = new ArrayList[N + 1];
    for (int[] d : dislikes) {
      if (graph[d[0]] == null) {
        graph[d[0]] = new ArrayList();
      }
      graph[d[0]].add(d[1]);

      if (graph[d[1]] == null) {
        graph[d[1]] = new ArrayList();
      }
      graph[d[1]].add(d[0]);
    }

    int[] colors = new int[N + 1];

    Stack<int[]> stack;
    for (int v = 1; v <= N; v++) {
      if (colors[v] == 0) {
        stack = new Stack();
        stack.push(new int[] {v, 1});

        while(!stack.isEmpty()) {
          int[] cur = stack.pop();
          int curColor = cur[1];
          if (colors[cur[0]] != 0) {
            if (colors[cur[0]] != curColor)
              return false;
            continue;
          }
          colors[cur[0]] = curColor;

          if (graph[cur[0]] != null) {
            int nextColor = (curColor == 1) ? 2 : 1;
            for (int adj : graph[cur[0]]) {
              stack.push(new int[] {adj, nextColor});
            }
          }
        }
      }
    }

    return true;
  }
}
