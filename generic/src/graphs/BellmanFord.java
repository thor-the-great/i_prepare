package graphs;

import cracking.trees_graphs.DiGraph;

import java.util.Arrays;
import java.util.List;

public class BellmanFord {

    long[] getShortestPaths(DiGraph g, int start) {
        long[] res = new long[g.getV()];

        Arrays.fill(res, Integer.MAX_VALUE);
        res[start] = 0;

        for (int k = 0; k < g.getV() - 1; k++) {
            List<DiGraph.Edge> allEdges = g.adjAllEdges();
            for (DiGraph.Edge e : allEdges) {
                if (res[e.from] + e.weight < res[e.to]) {
                    res[e.to] = res[e.from] + e.weight;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        BellmanFord obj = new BellmanFord();
        long[] paths = obj.getShortestPaths(GraphUtils.getDiGraphWeighted1(), 0);
        System.out.print("Shortest paths : [ ");
        Arrays.stream(paths).forEach(i->System.out.print(i + " "));
        System.out.print(" ]\n");

        paths = obj.getShortestPaths(GraphUtils.getDiGraphWeightedNeg1(), 0);
        System.out.print("Shortest paths : [ ");
        Arrays.stream(paths).forEach(i->System.out.print(i + " "));
        System.out.print(" ]\n");
    }
}
