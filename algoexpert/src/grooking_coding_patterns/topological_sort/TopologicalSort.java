package grooking_coding_patterns.topological_sort;

import java.util.*;

/**
 * Topological Sort (medium)
 * Topological Sort of a directed graph (a graph with unidirectional edges) is a linear ordering
 * of its vertices such that for every directed edge (U, V) from vertex U to vertex V, U comes
 * before V in the ordering.
 *
 * Given a directed graph, find the topological ordering of its vertices.
 *
 * Example 1:
 *
 * Input: Vertices=4, Edges=[3, 2], [3, 0], [2, 0], [2, 1]
 * Output: Following are the two valid topological sorts for the given graph:
 * 1) 3, 2, 0, 1
 * 2) 3, 2, 1, 0
 *     3
 *     2
 *     0
 *     1
 * Example 2:
 *
 * Input: Vertices=5, Edges=[4, 2], [4, 3], [2, 0], [2, 1], [3, 1]
 * Output: Following are all valid topological sorts for the given graph:
 * 1) 4, 2, 3, 0, 1
 * 2) 4, 3, 2, 0, 1
 * 3) 4, 3, 2, 1, 0
 * 4) 4, 2, 3, 1, 0
 * 5) 4, 2, 0, 3, 1
 *     4
 *     2
 *     3
 *     0
 *     1
 * Example 3:
 *
 * Input: Vertices=7, Edges=[6, 4], [6, 2], [5, 3], [5, 4], [3, 0], [3, 1], [3, 2], [4, 1]
 * Output: Following are all valid topological sorts for the given graph:
 * 1) 5, 6, 3, 4, 0, 1, 2
 * 2) 6, 5, 3, 4, 0, 1, 2
 * 3) 5, 6, 4, 3, 0, 2, 1
 * 4) 6, 5, 4, 3, 0, 1, 2
 * 5) 5, 6, 3, 4, 0, 2, 1
 * 6) 5, 6, 3, 4, 1, 2, 0
 *
 * There are other valid topological ordering of the graph too.
 *     6
 *     4
 *     2
 */
public class TopologicalSort {

  public static List<Integer> sort(int vertices, int[][] edges) {
    List<Integer> sortedOrder = new ArrayList<>();
    //create an adjacency list view of the graph, collect indegree info
    ArrayList<Integer>[] g = new ArrayList[vertices];
    int[] indegree = new int[vertices];
    for (int[] e : edges) {
      if (g[e[0]] == null)
        g[e[0]] = new ArrayList();
      g[e[0]].add(e[1]);
      ++indegree[e[1]];
    }
    //add all indegree = 0 vertexes to the initial queue
    Queue<Integer> q = new LinkedList();
    for (int i = 0; i < vertices; i++) {
      if (indegree[i] == 0)
        q.add(i);
    }
    //start BFS style traversal of the graph from those 0 in-degree vertices, put every such
    //vertex to the result list
    while(!q.isEmpty()) {
      int v = q.poll();
      sortedOrder.add(v);
      if (g[v] != null) {
        for (int adj : g[v]) {
          --indegree[adj];
          if (indegree[adj] == 0) {
            q.add(adj);
          }
        }
      }
    }
    //it can be that order is not possible - return empty list in this case
    if (sortedOrder.size() != vertices)
      return new ArrayList();

    return sortedOrder;
  }

  public static void main(String[] args) {
    List<Integer> result = TopologicalSort.sort(4,
        new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
    System.out.println(result);

    result = TopologicalSort.sort(5, new int[][] { new int[] { 4, 2 }, new int[] { 4, 3 }, new int[] { 2, 0 },
        new int[] { 2, 1 }, new int[] { 3, 1 } });
    System.out.println(result);

    result = TopologicalSort.sort(7, new int[][] { new int[] { 6, 4 }, new int[] { 6, 2 }, new int[] { 5, 3 },
        new int[] { 5, 4 }, new int[] { 3, 0 }, new int[] { 3, 1 }, new int[] { 3, 2 }, new int[] { 4, 1 } });
    System.out.println(result);
  }
}