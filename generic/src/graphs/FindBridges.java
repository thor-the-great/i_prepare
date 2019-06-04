package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  *  Identifies bridge edges and prints them out. This decomposes
 *  *  a directed graph into two-edge connected components.
 *  *  Runs in O(E + V) time.
 *  *
 *  *  Key quantity:  low[v] = minimum DFS preorder number of v
 *  *  and the set of vertices w for which there is a back edge (x, w)
 *  *  with x a descendant of v and w an ancestor of v.
 *  *
 *  *  Note: code assumes no parallel edges, e.g., two parallel edges
 *  *  would be (incorrectly) identified as bridges.
 */
public class FindBridges {

    int timer = 0;
    List<int[]> res;

    int[] low;// low[v] = lowest preorder of any vertex connected to v
    int[] pre;// pre[v] = order in which dfs examines v

    List<int[]> findBridge(SimpleGraph graph) {

        res = new ArrayList<>();

        low = new int[graph.getV()];
        pre = new int[graph.getV()];

        Arrays.fill(low, -1);
        Arrays.fill(pre, -1);

        timer = 0;

        for (int v = 0; v < graph.getV(); v++) {
            if (pre[v] == -1)
                dfs(graph, v, v);
        }

        return res;
    }

    void dfs(SimpleGraph g, int parentVertexToWhichAdjucentTo, int vertexToVisit) {
        pre[vertexToVisit] = timer++;
        low[vertexToVisit] = pre[vertexToVisit];
        for (int adj : g.adj(vertexToVisit)) {
            if (pre[adj] == -1) {
                dfs(g, vertexToVisit, adj);
                low[vertexToVisit] = Math.min(low[vertexToVisit], low[adj]);
                if (low[adj] == pre[adj]) {
                   res.add(new int[] {vertexToVisit, adj});
                }
            }
            else if (adj != parentVertexToWhichAdjucentTo) // update low number - ignore reverse of edge leading to v
                low[vertexToVisit] = Math.min(low[vertexToVisit], pre[adj]);
        }
    }

    public static void main(String[] args) {
        FindBridges obj = new FindBridges();
        SimpleGraph graph;
        List<int[]> bridges;

        graph = GraphUtils.getUnGraph1();
        System.out.println(graph);
        bridges = obj.findBridge(graph);
        System.out.print("Bridges: ");
        bridges.forEach(b->System.out.print(Arrays.toString(b) + ", "));
        System.out.print("\n");

        graph = GraphUtils.getUnGraph2();
        System.out.println(graph);
        bridges = obj.findBridge(graph);
        System.out.print("Bridges: ");
        bridges.forEach(b->System.out.print(Arrays.toString(b) + ", "));
        System.out.print("\n");

        graph = GraphUtils.getUnGraph3();
        System.out.println(graph);
        bridges = obj.findBridge(graph);
        System.out.print("Bridges: ");
        bridges.forEach(b->System.out.print(Arrays.toString(b) + ", "));
        System.out.print("\n");
    }
}
