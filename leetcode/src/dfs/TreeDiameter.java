package dfs;

import java.util.ArrayList;

/**
 * 1245. Tree Diameter
 * Medium
 *
 * Given an undirected tree, return its diameter: the number of edges in a longest path in that
 * tree.
 *
 * The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between
 * nodes u and v.  Each node has labels in the set {0, 1, ..., edges.length}.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: edges = [[0,1],[0,2]]
 * Output: 2
 * Explanation:
 * A longest path of the tree is the path 1 - 0 - 2.
 * Example 2:
 *
 *
 *
 * Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
 * Output: 4
 * Explanation:
 * A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
 *
 *
 * Constraints:
 *
 * 0 <= edges.length < 10^4
 * edges[i][0] != edges[i][1]
 * 0 <= edges[i][j] <= edges.length
 * The given edges form an undirected tree.
 */
public class TreeDiameter {

  int D = 0;
  ArrayList<Integer>[] adjList;

  /**
   * Similar to finding the diameter of the binary tree, pick 0 as root and calculate
   * diameter
   * @param edges
   * @return
   */
  public int treeDiameter(int[][] edges) {
    //store the diameter in global var
    D = 0;
    //save graph as adjacency list
    adjList = new ArrayList[edges.length + 1];
    //fill adjacent nodes lists. Because it's undirected graph fill both directions
    //edge[0] -> edge[1] and edge[1] -> edge[0]
    for (int[] edge : edges) {
      if (adjList[edge[0]] == null)
        adjList[edge[0]] = new ArrayList();
      adjList[edge[0]].add(edge[1]);

      if (adjList[edge[1]] == null)
        adjList[edge[1]] = new ArrayList();
      adjList[edge[1]].add(edge[0]);
    }
    //calculate diameter by doing DFS
    helper(0, -1);
    return D;
  }

  int helper(int node, int parent) {
    //save two max paths
    int maxD = 0, max2D = 0;
    //base case - if there are no children - return 1, edge from parent to a leaf
    if (adjList[node] == null)
      return 1;
    //for every adjacent node
    for (int adj : adjList[node]) {
      //prevent the loop back
      if (adj != parent) {
        //make recursive call for child, then check if returned
        //path can be max or second max
        int adjD = helper(adj, node);
        if (adjD > maxD) {
          max2D = maxD;
          maxD = adjD;
        } else if (adjD > max2D) {
          max2D = adjD;
        }
      }
    }
    //diameter candidate is sum of two longest child paths
    D = Math.max(D, maxD + max2D);
    //return longest path including this node - meaning max of child paths + 1
    return maxD + 1;
  }
}
