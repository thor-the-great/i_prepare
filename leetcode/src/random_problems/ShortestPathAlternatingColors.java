/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package random_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1129. Shortest Path with Alternating Colors
 * Medium
 *
 * Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is
 * either red or blue, and there could be self-edges or parallel edges.
 *
 * Each [i, j] in red_edges denotes a red directed edge from node i to node j.  Similarly, each
 * [i, j] in blue_edges denotes a blue directed edge from node i to node j.
 *
 * Return an array answer of length n, where each answer[X] is the length of the shortest path
 * from node 0 to node X such that the edge colors alternate along the path (or -1 if such a path
 * doesn't exist).
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
 * Output: [0,1,-1]
 * Example 2:
 *
 * Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
 * Output: [0,1,-1]
 * Example 3:
 *
 * Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
 * Output: [0,-1,-1]
 * Example 4:
 *
 * Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
 * Output: [0,1,2]
 * Example 5:
 *
 * Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
 * Output: [0,1,1]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * red_edges.length <= 400
 * blue_edges.length <= 400
 * red_edges[i].length == blue_edges[i].length == 2
 * 0 <= red_edges[i][j], blue_edges[i][j] < n
 */
public class ShortestPathAlternatingColors {

  /**
   * Doing BFS, differenciate visited nodes by color using int[]. Use array of visited to
   * avoid cycles.
   *
   * @param n
   * @param red_edges
   * @param blue_edges
   * @return
   */
  public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
    int[] res = new int[n];

    List<Integer>[] redM = new List[n];
    List<Integer>[] blueM = new List[n];

    fillMap(redM, red_edges);
    fillMap(blueM, blue_edges);

    Arrays.fill(res, -1);
    boolean[][] seen = new boolean[n][3];
    Queue<int[]> q = new LinkedList();
    q.add(new int[] {0, 0 });
    int moves = 0;
    while (!q.isEmpty()) {
      int len = q.size();
      for (int i = 0; i < len; i++) {
        int[] cur = q.poll();
        if (seen[cur[0]][cur[1]]) {
          continue;
        }
        seen[cur[0]][cur[1]] = true;
        if (res[cur[0]] == -1)
          res[cur[0]] = moves;
        //red
        if (cur[1] == 0 || cur[1] == 1) {
          if (redM[cur[0]] != null) {
            for (int next : redM[cur[0]]) {
              //mqke next blue
              q.add(new int[] {next, 2});
            }
          }
        }
        //blue
        if (cur[1] == 0 || cur[1] == 2) {
          if (blueM[cur[0]] != null) {
            for (int next : blueM[cur[0]]) {
              //mqke next blue
              q.add(new int[] {next, 1});
            }
          }
        }
      }
      moves++;
    }

    return res;
  }

  void fillMap(List<Integer>[] redM, int[][] red_edges) {
    for (int[] red : red_edges) {
      int f = red[0];
      if (redM[f] == null){
        List<Integer> list =  new ArrayList();
        list.add(red[1]);
        redM[f] = list;
      }
      else
        redM[f].add(red[1]);
    }
  }
}
