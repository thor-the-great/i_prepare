package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1361. Validate Binary Tree Nodes
 * Medium
 *
 * You have n binary tree nodes numbered from 0 to n - 1 where node i has two children
 * leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly
 * one valid binary tree.
 *
 * If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
 *
 * Note that the nodes have no values and that we only use the node numbers in this problem.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
 * Output: true
 * Example 2:
 *
 *
 *
 * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
 * Output: false
 * Example 3:
 *
 *
 *
 * Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
 * Output: false
 * Example 4:
 *
 *
 *
 * Input: n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^4
 * leftChild.length == rightChild.length == n
 * -1 <= leftChild[i], rightChild[i] <= n - 1
 */
public class ValidateBinaryTreeNodes {
  /**
   * Create graph from two arrays, keep counting indegree. Nodes with indegree == 0 can be root,
   * but there mus be only one root. Put that root to queue and start BFS. Check that we have
   * visited all nodes in the end.
   * @param n
   * @param leftChild
   * @param rightChild
   * @return
   */
  public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
    List<Integer>[] g = new ArrayList[n];
    for (int i =0; i < n; i++)
      g[i] = new ArrayList();

    int[] indeg = new int[n];
    for (int i = 0; i < n; i++) {
      if (leftChild[i] != -1) {
        g[i].add(leftChild[i]);
        ++indeg[leftChild[i]];
      }

      if (rightChild[i] != -1) {
        g[i].add(rightChild[i]);
        ++indeg[rightChild[i]];
      }

      if (g[i].size() > 2)
        return false;
    }

    Queue<Integer> q = new LinkedList();
    boolean[] seen = new boolean[n]; int countSeen = 0;

    for (int i = 0; i < n; i++) {
      if (indeg[i] == 0) {
        if(!q.isEmpty())
          return false;
        else
          q.add(i);
      }
    }
    while (!q.isEmpty()) {
      int node = q.poll();
      if (seen[node])
        return false;
      seen[node] = true;
      ++countSeen;
      for (int adj : g[node]) {
        q.add(adj);
      }
    }
    return countSeen == n;
  }
}
