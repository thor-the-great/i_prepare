package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * 1443. Minimum Time to Collect All Apples in a Tree
 * Medium
 *
 * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some
 * apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the
 * minimum time in seconds you have to spend in order to collect all apples in the tree starting
 * at vertex 0 and coming back to this vertex.
 *
 * The edges of the undirected tree are given in the array edges, where edges[i] = [fromi, toi]
 * means that exists an edge connecting the vertices fromi and toi. Additionally, there is a
 * boolean array hasApple, where hasApple[i] = true means that vertex i has an apple, otherwise,
 * it does not have any apple.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,
 * false,true,true,false]
 * Output: 8
 * Explanation: The figure above represents the given tree where red vertices have an apple. One
 * optimal path to collect all apples is shown by the green arrows.
 * Example 2:
 *
 *
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,
 * false,false,true,false]
 * Output: 6
 * Explanation: The figure above represents the given tree where red vertices have an apple. One
 * optimal path to collect all apples is shown by the green arrows.
 * Example 3:
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,
 * false,false,false,false]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * edges.length == n-1
 * edges[i].length == 2
 * 0 <= fromi, toi <= n-1
 * fromi < toi
 * hasApple.length == n
 */
public class MinimumTimeToCollectAllApplesInTree {
  List<Integer>[] graph;
  List<Boolean> hasApple;
  boolean[] visited;

  public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
    //build graph as an array of adjacency lists
    graph = new ArrayList[n];
    for (int i = 0; i < n; i++) graph[i] = new ArrayList();
    for (int[] e : edges) graph[e[0]].add(e[1]);

    this.hasApple = hasApple;
    visited = new boolean[n];
    //traverse the graph in DFS manner, pass initial isRoot flag as true
    return dfs(0, true);
  }

  int dfs(int node, boolean isRoot) {
    //check if node has been visited
    if (visited[node])
      return 0;
    visited[node] = true;
    //visit all nodes in sub-tree
    int subtreeCost = 0;
    for (int adj : graph[node]) {
      subtreeCost += dfs(adj, false);
    }
    //if sub-tree is empty and current node is empty - return 0 cost
    if (subtreeCost == 0 && !hasApple.get(node))
      return 0;
    //return cost of sub-tree including current node
    return subtreeCost + (isRoot ? 0 : 2);
  }
}
