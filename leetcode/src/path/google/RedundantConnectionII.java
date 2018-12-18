package path.google;

import java.util.LinkedList;

/**
 * 685. Redundant Connection II
 * Hard
 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all
 * other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which
 * has no parents.
 *
 * The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N),
 * with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not
 * an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a
 * directed edge connecting nodes u and v, where u is a parent of child v.
 *
 * Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple
 * answers, return the answer that occurs last in the given 2D-array.
 *
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given directed graph will be like this:
 *   1
 *  / \
 * v   v
 * 2-->3
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 * Output: [4,1]
 * Explanation: The given directed graph will be like this:
 * 5 <- 1 -> 2
 *      ^    |
 *      |    v
 *      4 <- 3
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 */
public class RedundantConnectionII {

    // there are 3 cases:
    //1. No loop, but there is one node who has 2 parents.
    //2. A loop, and there is one node who has 2 parents, that node must be inside the loop.
    //3. A loop, and every node has only 1 parent, return the last in the loop
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int N = edges.length;

        //build graph
        Graph g = new Graph(N);
        int[] count = new int[N + 1];
        int nodeWithTwoParent = - 1;
        int nodeLastParent = -1;

        for (int[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
            count[edge[1]]++;
            if (count[edge[1]] > 1) {
                nodeWithTwoParent = edge[1];
                nodeLastParent = edge[0];
                g.setNode(nodeWithTwoParent);
            }
        }
        //forming result
        int[] cycleCheckRes = g.isCycle();
        if (cycleCheckRes == null) { //case 1
            return new int[] {nodeLastParent, nodeWithTwoParent};
        } else {
            return cycleCheckRes;
        }
    }

    class Graph {
        int N;
        LinkedList<Integer> adj[];
        int nodeTwoParents = -1;
        int[] pair = {-1, -1};
        int idx;
        int[][] map;
        int lastIndexSoFar = -1;

        Graph(int n) {
            this.N = n;
            adj = new LinkedList[N + 1];
            for (int i =0; i <= N; i++)
                adj[i] = new LinkedList();
            map = new int[N + 1][N + 1];
        }

        void addEdge(int v, int w) {
            adj[v].add(w);
            map[v][w] = idx++;
        }

        void setNode(int node) {
            nodeTwoParents = node;
        }

        int[] isCycle() {
            boolean[] visited = new boolean[N + 1];
            boolean[] revisited = new boolean[N + 1];

            for (int i = 1; i <= N; i++) {
                if (isCycleUtil(i, visited, revisited))
                    break;
            }
            if (pair[0] == -1) { //case #1
                return null;
            } else {
                //cases #2 and #3
                return pair;
            }
        }

        boolean isCycleUtil(int i , boolean[] visited, boolean[] revisited){
            if(revisited[i]){
                return true;
            }

            if(visited[i]){
                return false;
            }

            visited[i] = true;
            revisited[i] = true;

            LinkedList<Integer> children = adj[i];
            for(Integer child: children){
                if(isCycleUtil(child, visited, revisited)){
                    if(nodeTwoParents != -1 ){//case 2
                        if( child == nodeTwoParents && pair[0] == -1 ){
                            pair[0] = i;
                            pair[1] = child;
                        }
                    }else{// case 3: return the last one in the edges that is in the cycle
                        if(map[i][child] > lastIndexSoFar && pair[0] == -1){
                            lastIndexSoFar = map[i][child];
                            pair[0] = i;
                            pair[1] = child;
                        }
                    }
                    return true;
                }
            }
            revisited[i] = false;
            return false;
        }
    }
}