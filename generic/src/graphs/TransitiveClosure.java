package graphs;

import cracking.trees_graphs.DiGraph;
import utils.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * This problem was asked by Microsoft.
 *
 * The transitive closure of a graph is a measure of which vertices are reachable from other vertices. It can be
 * represented as a matrix M, where M[i][j] == 1 if there is a path between vertices i and j, and otherwise 0.
 *
 * For example, suppose we are given the following graph in adjacency list form:
 *
 * graph = [
 *     [0, 1, 3],
 *     [1, 2],
 *     [2],
 *     [3]
 * ]
 * The transitive closure of this graph would be:
 *
 * [1, 1, 1, 1]
 * [0, 1, 1, 0]
 * [0, 0, 1, 0]
 * [0, 0, 0, 1]
 * Given a graph, find its transitive closure.
 */
public class TransitiveClosure {

    int[][] transitiveClosure(DiGraph g) {
        //return transitiveClosureDfs(g);
        return transitiveClosureFloydWarshall(g);
    }

    private int[][] transitiveClosureFloydWarshall(DiGraph g) {
        int N = g.getV();
        int[][] res = new int[N][N];

        for (int v = 0; v < N; v++) {
            res[v][v] = 1;
            if (g.adj(v) != null) {
                for (int adj : g.adj(v)) {
                    res[v][adj] = 1;
                }
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (res[i][j] == 0) {
                        if (res[i][k] == 1 && res[k][j] == 1)
                            res[i][j] = 1;
                    }
                }
            }
        }

        return res;
    }

    private int[][] transitiveClosureDfs(DiGraph g) {
        int N = g.getV();
        int[][] res = new int[N][N];

        //do dfs from every vertex
        Stack<Integer> s = new Stack<>();
        Set<Integer> visited = new HashSet();
        for (int i =0; i < N; i++) {
            s.clear();
            visited.clear();
            s.push(i);
            while(!s.isEmpty()) {
                int v = s.pop();
                if (!visited.contains(v) && res[i][v] == 0) {
                    res[i][v] = 1;
                    visited.add(v);
                    List<Integer> adjV = g.adj(v);
                    if (adjV != null && !adjV.isEmpty()) {
                        for (int j : adjV) {
                            s.push(j);
                        }
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        TransitiveClosure obj = new TransitiveClosure();
        DiGraph g = GraphUtils.getDiGraphWeighted5();
        int[][] trClosure = obj.transitiveClosure(g);
        System.out.println(g);
        System.out.println("transitive closure matrix");
        System.out.println(StringUtils.int2DArrayToString(trClosure));

        g = GraphUtils.getDiGraphWeighted7();
        trClosure = obj.transitiveClosure(g);
        System.out.println(g);
        System.out.println("transitive closure matrix");
        System.out.println(StringUtils.int2DArrayToString(trClosure));

        g = GraphUtils.getDiGraphWeighted8();
        trClosure = obj.transitiveClosure(g);
        System.out.println(g);
        System.out.println("transitive closure matrix");
        System.out.println(StringUtils.int2DArrayToString(trClosure));
    }
}
