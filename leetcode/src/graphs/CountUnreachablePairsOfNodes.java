package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/description/

 2316. Count Unreachable Pairs of Nodes in an Undirected Graph
Medium

You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.

Return the number of pairs of different nodes that are unreachable from each other.

 

Example 1:


Input: n = 3, edges = [[0,1],[0,2],[1,2]]
Output: 0
Explanation: There are no pairs of nodes that are unreachable from each other. Therefore, we return 0.
Example 2:


Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
Output: 14
Explanation: There are 14 pairs of nodes that are unreachable from each other:
[[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
Therefore, we return 14.
 

Constraints:

1 <= n <= 105
0 <= edges.length <= 2 * 105
edges[i].length == 2
0 <= ai, bi < n
ai != bi
There are no repeated edges.
 */
public class CountUnreachablePairsOfNodes {
    /**
     * Idea: traverse graph, collect number of components and number of nodes in each group, this can be done with BFS of DFS
     * When number of nodes in group counted we can compute number of unconnected pairs as (count * (numOfNodes - count))
     * Num of nodes will be decremented afer that on count, as we need only unique pairs 
     */
    public long countPairs(int n, int[][] edges) {
        boolean[] visited = new boolean[n];
        List<Integer>[] graph = new List[n];
        for (int[] e : edges) {
            int from = e[0], to = e[1];
            if (graph[from] == null) {
                graph[from] = new ArrayList();
            }
            graph[from].add(to);

            if (graph[to] == null) {
                graph[to] = new ArrayList();
            }
            graph[to].add(from);
        }
        long res = 0, remainingNodes = n;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                long count = 0;
                Queue<Integer> q = new LinkedList();
                q.add(i);
                List<Integer> group = new ArrayList();
                while (!q.isEmpty()) {
                    int size = q.size();
                    for (int s = 0; s < size; s++) {
                        int node = q.poll();
                        if (visited[node]) {
                            continue;
                        }
                        count++;
                        visited[node] = true;
                        if (graph[node] != null) {
                            for (int a : graph[node]) {
                                if (!visited[a])
                                    q.add(a);
                            }
                        }
                    }
                }
                //we have count nodes in this group
                res += (count * (remainingNodes - count));
                remainingNodes -= count;
            }
        }
        return res;
    }
}