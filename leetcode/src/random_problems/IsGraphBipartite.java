package random_problems;

import java.util.*;

/**
 * 785. Is Graph Bipartite?
 * Medium
 *
 * Given an undirected graph, return true if and only if it is bipartite.
 *
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that
 * every edge in the graph has one node in A and another node in B.
 *
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j
 * exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges:
 * graph[i] does not contain i, and it doesn't contain any element twice.
 *
 * Example 1:
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation:
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 * Example 2:
 * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * Output: false
 * Explanation:
 * The graph looks like this:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * We cannot find a way to divide the set of nodes into two independent subsets.
 *
 *
 * Note:
 *
 * graph will have length in range [1, 100].
 * graph[i] will contain integers in range [0, graph.length - 1].
 * graph[i] will not contain i or duplicate values.
 * The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 *
 */
public class IsGraphBipartite {

	/**
	 * Idea: using vertex coloring technique. Iterate over the vertexes and color every one that has been colored yet.
	 * If adjacent one has been colored already - compare that we are not going to color it to a different color. If so
	 * - out graph isn't bipartite
	 * @param graph
	 * @return
	 */
	public boolean isBipartiteIterative(int[][] graph) {
		int N = graph.length;
		if (N == 0)
			return false;
							        
		int[] colors = new int[N];
		Arrays.fill(colors, -1);
		Stack<Integer> s = new Stack();
		for (int v = 0; v < N; v++) {  
			if (colors[v] == -1) {
				s.clear();
				s.push(v);
				while (!s.isEmpty()) {
					int vert = s.pop();
					int[] adj = graph[vert];
					int otherColor = colors[vert] == 1 ? 0 : 1;
					for (int adjV : adj) {
						if (colors[adjV] == -1) {
							colors[adjV] = otherColor;
							s.push(adjV);    
						} else if (colors[adjV] == colors[vert])
							return false;
					}
				}
			}
		}
		return true;                
	}

	int[][] g;
	int[] colors;
	boolean isBipartite;

	public boolean isBipartiteRecursive(int[][] graph) {
		int N = graph.length;
		colors = new int[N];
		this.g = graph;
		isBipartite = true;

		for (int v = 0; v < N; v++) {
			if (colors[v] == 0)
				dfs(v, 1);
		}

		return isBipartite;

	}

	void dfs(int v, int color) {
		//paint this vertex
		colors[v] = color;
		int nextColor = color == 1 ? 2 : 1;
		for (int adjV : g[v]) {
			if (colors[adjV] == 0) {
				dfs(adjV, nextColor);
			} else if (colors[adjV] == colors[v]) {
				isBipartite = false;
				return;
			}
		}
	}
}
