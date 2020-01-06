package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1192. Critical Connections in a Network
 * Hard
 *
 * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network
 * where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server
 * directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some server unable to reach some other server.
 *
 * Return all critical connections in the network in any order.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * n-1 <= connections.length <= 10^5
 * connections[i][0] != connections[i][1]
 * There are no repeated connections.
 */
public class CriticalConnectionsInNetwork {

    List<Integer>[] graph;
    List<List<Integer>> res;
    int[] disc;
    int[] low;
    int time = 0;

    /**
     * Build the graph and use Tarjan's algorithm to count strongly connected components, edges that are not in those
     * components are our critical links.
     * @param n
     * @param connections
     * @return
     */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        res = new ArrayList();
        //discovered nodes
        disc = new int[n];
        //low link values for each node
        low = new int[n];
        //initially all nodes are not discovered
        Arrays.fill(disc, -1);

        //build the graph
        graph = new ArrayList[n];
        for (int v = 0; v < n; v++) {
            graph[v] = new ArrayList();
        }
        //create edges (connections)
        for (List<Integer> con : connections) {
            int v1 = con.get(0), v2 = con.get(1);
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        //start tarjan's algo
        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                dfs(i, i);
            }
        }
        return res;
    }

    void dfs(int u, int pre) {
        //increment time and assign this value to discovered and initially to low-link array
        disc[u] = low[u] = ++time;
        //not check all adjacent nodes - do the dfs
        for (int j : graph[u]) {
            //don't check the parent
            if (j != pre) {
                //if not discovered yet
                if (disc[j] == -1) {
                    //visit the child
                    dfs(j, u);
                    //update low-link value
                    low[u] = Math.min(low[u], low[j]);
                    //if low link of the child remains higher than discovered of the current node - there is no
                    //cycle (this is not scc) and the link is critical
                    if (low[j] > disc[u]) {
                        //this is critical
                        res.add(Arrays.asList(u, j));
                    }
                }
                //if we hve visited this node before - check if we need to update it's low-link value
                else {
                    low[u] = Math.min(low[u], disc[j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        CriticalConnectionsInNetwork obj = new CriticalConnectionsInNetwork();
        List<List<Integer>> connections;
        int n;
        n = 4;
        connections = List.of(List.of(0,1),List.of(1,2),List.of(2,0),List.of(1,3));
        System.out.println(obj.criticalConnections(n, connections));
    }
}
