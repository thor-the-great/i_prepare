import cracking.trees_graphs.DiGraph;
import graphs.GraphUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestPathsFromTopBottom {

    List<Integer> shortestPathsTopBottom(DiGraph g, List<Integer> top, List<Integer> bottom) {
        List<Integer> res = new ArrayList<>();
        //do the Floyd-Warshall
        long[][] dp = new long[g.getV()][g.getV()];
        for (long[] row : dp)
            Arrays.fill(row, Integer.MAX_VALUE);

        for (int i = 0; i < g.getV(); i++) {
            dp[i][i] = 0;
            List<DiGraph.Edge> adjList = g.adjEdges(i);
            for (DiGraph.Edge e : adjList) {
                int v = e.to;
                dp[i][v] = e.weight;
            }
        }
        for (int k = 0; k < g.getV(); k++) {
            for (int i = 0; i < g.getV(); i++) {
                for (int j = 0; j < g.getV(); j++) {
                    if (dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }
        for (int topV : top) {
            int min = Integer.MAX_VALUE;
            for (int botV : bottom) {
                min = (int) Math.min(min, dp[topV][botV]);
            }
            res.add(min == Integer.MAX_VALUE ? -1 : min);
        }
        return res;
    }

    public static void main(String[] args) {
        DiGraph g1 = GraphUtils.getDiGraphWeighted1();
        ShortestPathsFromTopBottom obj = new ShortestPathsFromTopBottom();
        List<Integer> paths = obj.shortestPathsTopBottom(g1, Arrays.asList(0, 1), Arrays.asList(4, 5));
        paths.forEach(i->System.out.print(i + " ")); //6 (0->2->1->4), 3 (1->3->4)
        System.out.print("\n");

        DiGraph g2 = GraphUtils.getDiGraphWeighted3();
        paths = obj.shortestPathsTopBottom(g2, Arrays.asList(0, 1), Arrays.asList(4));
        paths.forEach(i->System.out.print(i + " ")); //6 (0->2->1->4), 3 (1->3->4)
    }
}
