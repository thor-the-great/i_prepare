package graphs;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Dijkstra {



    long[] getShortestPaths(DiGraph g, int start, int end) {
        long[] res = new long[g.getV()];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[start] = 0;
        IndexMinPQ<Long> minPQ = new IndexMinPQ(g.getV());
        minPQ.insert(start, res[start]);
        int[] pathTo = new int[g.getV()];

        while(!minPQ.isEmpty()) {
            int u = minPQ.delMin();
            List<DiGraph.Edge> adjL = g.adjEdges(u);
            if (adjL != null && !adjL.isEmpty()) {
                for (DiGraph.Edge e :
                        adjL) {
                    int v = e.to;
                    if (res[v] > res[u] + e.weight) {
                        res[v] = res[u] + e.weight;
                        pathTo[v] = u;
                        if (minPQ.contains(v))
                            minPQ.decreaseKey(v, res[v]);
                        else
                            minPQ.insert(v, res[v]);
                    }
                }
            }
        }
        System.out.print("Shortest paths are : [ ");
        Arrays.stream(res).forEach(p->System.out.print(p + " "));
        System.out.print(" ]\n");
        Stack<Integer> path = new Stack();
        int p = end;
        while(p != start) {
            path.push(p);
            p = pathTo[p];
        }
        path.push(start);
        System.out.print("Path to " + end +" is : [ ");
        while (!path.isEmpty())
            System.out.print(path.pop() + " ");
        System.out.print(" ] ");
        return res;
    }

    public static void main(String[] args) {
        Dijkstra obj = new Dijkstra();
        obj.getShortestPaths(GraphUtils.getDiGraphWeighted1(), 0, 1);
    }
}
