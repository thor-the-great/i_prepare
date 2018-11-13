package path.amazon;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Graph Valid Tree
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function
 * to check whether these edges make up a valid tree.
 *
 * Example 1:
 *
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 * Example 2:
 *
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the
 * same as [1,0] and thus will not appear together in edges.
 */
public class GraphValidTree {

    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList();
        IntStream.range(0, n).forEach(i->adjList.add(new ArrayList<>()));
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }
        //bfs
        Queue<Integer> q = new LinkedList();
        q.add(0);
        Queue<Integer> p = new LinkedList();
        p.add(-1);
        boolean[] visited = new boolean[n];
        while(!q.isEmpty()) {
            int vertex = q.poll();
            int parent = p.poll();
            for(int nextAdj : adjList.get(vertex)) {
                if (visited[nextAdj] && nextAdj != parent) //detect cycle
                    return false;
                if (!visited[nextAdj]) {
                    q.add(nextAdj);
                    p.add(vertex);
                }
            }
            visited[vertex] = true; //mark visit compalted
        }

        for (boolean visit : visited) {
            if (!visit)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        GraphValidTree obj = new GraphValidTree();
        System.out.println(obj.validTree(5, new int[][] {{0,1}, {0,2}, {0,3}, {1,4}}));//true

        System.out.println(obj.validTree(5, new int[][] {{0,1}, {1,2}, {2,3}, {1, 3}, {1,4}}));//false

        System.out.println(obj.validTree(3, new int[][] {{1, 0}, {2, 0}}));//true
    }
}
