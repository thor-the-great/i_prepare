package grooking_coding_patterns.graphs.topological_sort;

import java.util.*;

/**
 * Minimum Height Trees (hard) #
 * We are given an undirected graph that has characteristics of a k-ary tree. In such a graph, we can choose any
 * node as the root to make a k-ary tree. The root (or the tree) with the minimum height will be called Minimum
 * Height Tree (MHT). There can be multiple MHTs for a graph. In this problem, we need to find all those roots
 * which give us MHTs. Write a method to find all MHTs of the given graph and return a list of their roots.
 *
 * Example 1:
 *
 * Input: vertices: 5, Edges: [[0, 1], [1, 2], [1, 3], [2, 4]]
 * Output:[1, 2]
 * Explanation: Choosing '1' or '2' as roots give us MHTs. In the below diagram, we can see that the
 * height of the trees with roots '1' or '2' is three which is minimum.
 *     0
 *     1
 *     2
 *     3
 *     4
 *     1
 *     0
 *     2
 *     3
 *     4
 *     0
 *     1
 *     2
 *     3
 *     4
 *     2
 *     1
 *     4
 *     3
 *     0
 *     3
 *     1
 *     0
 *     2
 *     4
 *     4
 *     2
 *     1
 *     0
 *     3
 *  With '0' as the root
 *  With '1' as the root
 *  With '2' as the root
 *  With '3' as the root
 *  With '4' as the root
 *  Given graph ==>
 *  Height = 4
 *  Height = 4
 *  Height = 4
 *  Height = 3
 *  Height = 3
 * Example 2:
 *
 * Input: vertices: 4, Edges: [[0, 1], [0, 2], [2, 3]]
 * Output:[0, 2]
 * Explanation: Choosing '0' or '2' as roots give us MHTs. In the below diagram, we can see that the
 * height of the trees with roots '0' or '2' is three which is minimum.
 *  With '0' as the root
 *  With '1' as the root
 *  With '2' as the root
 *  With '3' as the root
 *  Given graph ==>
 *  Height = 4
 *  Height = 4
 *  Height = 3
 *  Height = 3
 *     0
 *     1
 *     2
 *     3
 *     1
 *     0
 *     2
 *     3
 *     2
 *     0
 *     3
 *     1
 *     3
 *     2
 *     0
 *     1
 *     0
 *     1
 *     2
 *     3
 * Example 3:
 *
 * Input: vertices: 4, Edges: [[0, 1], [1, 2], [1, 3]]
 * Output:[1]
 */
public class MinimumHeightTrees {

  /**
   * Idea - the best node root can't be a left. Prune all leafs until we have 1-2 nodes.
   * @param nodes
   * @param edges
   * @return
   */
  public static List<Integer> findTrees(int nodes, int[][] edges) {
    List<Integer> minHeightTrees = new ArrayList<>();
    if (nodes == 1) {
      minHeightTrees.add(0);
      return minHeightTrees;
    }

    Map<Integer, List<Integer>> graph = new HashMap();
    Map<Integer, Integer> indegree = new HashMap();

    for (int i = 0; i < nodes; i++) {
      indegree.put(i, 0);
      graph.put(i, new ArrayList());
    }

    for (int[] e : edges) {
      indegree.put(e[0], indegree.get(e[0]) + 1);
      indegree.put(e[1], indegree.get(e[1]) + 1);

      graph.get(e[0]).add(e[1]);
      graph.get(e[1]).add(e[0]);
    }

    Queue<Integer> q = new LinkedList();
    for(int i =0; i < nodes; i++) {
      if (indegree.get(i) == 1) {
        q.add(i);
      }
    }

    int count = nodes;
    while(count > 2) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        int v = q.poll();
        --count;
        for (int n : graph.get(v)) {
          indegree.put(n, indegree.get(n) - 1);
          if (indegree.get(n) == 1) {
            q.add(n);
          }
        }
      }
    }

    minHeightTrees.addAll(q);

    return minHeightTrees;
  }

  public static void main(String[] args) {
    List<Integer> result = MinimumHeightTrees.findTrees(5,
            new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 1, 3 }, new int[] { 2, 4 } });
    System.out.println("Roots of MHTs: " + result);

    result = MinimumHeightTrees.findTrees(4,
            new int[][] { new int[] { 0, 1 }, new int[] { 0, 2 }, new int[] { 2, 3 } });
    System.out.println("Roots of MHTs: " + result);

    result = MinimumHeightTrees.findTrees(4,
            new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 1, 3 } });
    System.out.println("Roots of MHTs: " + result);
  }
}
