package path.amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.IntStream;

public class RedundantConnection {

    public static void main(String[] args) {
        RedundantConnection obj = new RedundantConnection();
        int[] res = obj.findRedundantConnection(new int[][] {{1,2}, {1,3}, {2,3}});
        System.out.print(res[0] + " " + res[1]);
    }

    public int[] findRedundantConnection(int[][] edges) {
        UF unFind = new UF(1001);
        for (int[] edge : edges) {
            if (!unFind.union(edge[0], edge[1]))
                return edge;
        }
        return new int[2];
    }

    /**
     * Form graph as array of adjacent nodes. Iterate over edges, check if one node from edge is
     * reachable starting from another node by doing DFS. If so - this node makes a cycle and can
     * be removed. Otherwise add the edge to graph (only after check)
     *
     * O(N^2) time, O(N) space
     * @param edges
     * @return
     */
    public int[] findRedundantConnectionGraph(int[][] edges) {
        int N = edges.length;

        List<Integer>[] g = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++)
            g[i] = new ArrayList();
        Set<Integer> seen = new HashSet();
        for (int[] edge : edges) {
            seen.clear();
            Stack<Integer> stack = new Stack();
            stack.push(edge[0]);
            boolean reachable = false;
            while (!stack.isEmpty()) {
                int next = stack.pop();
                if (seen.contains(next)) continue;
                if (next == edge[1]) {
                    reachable = true;
                    break;
                }
                seen.add(next);
                for (int adj : g[next]) {
                    stack.push(adj);
                }
            }
            if (reachable) {
                return edge;
            }
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }

        return new int[2];
    }

    class UF {
        int[] parent;
        int[] rank;

        UF(int numOfEdges) {
            parent = new int[numOfEdges];
            IntStream.range(0, numOfEdges).forEach(i->parent[i] = i);
            rank = new int[numOfEdges];
        }

        int find(int i) {
            if (parent[i] != i)
                parent[i] = find(parent[i]);
            return parent[i];
        }

        boolean union(int i, int j) {
            int ir = find(i);
            int jr = find(j);
            if (ir == jr)
                return false;
            else if (rank[ir] > rank[jr])
                parent[jr] = ir;
            else if (rank[ir] < rank[jr])
                parent[ir] = jr;
            else {
                parent[jr] = ir;
                rank[ir]++;
            }

            return true;
        }
    }
}
