package graphs;

import java.util.List;

/**
 * All shortest paths
 */
public class FloydWarshall {

    long[][] findShortestPaths(DiGraph g) {
        //distance from row to col
        long[][] res = new long[g.getV()][g.getV()];
        for(int r = 0; r < g.getV(); r++) {
            List<DiGraph.Edge> adjL = g.adjEdges(r);
            if (adjL != null && !adjL.isEmpty()) {
                for (DiGraph.Edge adjE : adjL) {
                    res[r][adjE.to] = adjE.weight;
                }
            }
            for (int c = 0; c < g.getV(); c++) {
                if (r != c && res[r][c] == 0) {
                    res[r][c] = Integer.MAX_VALUE;
                }
            }
        }

        for (int k = 0; k < g.getV(); k++) {
            for (int i = 0; i < g.getV(); i++) {
                for (int j = 0; j < g.getV(); j++) {
                    if (res[i][k] + res[k][j] < res[i][j]) {
                        res[i][j] = res[i][k] + res[k][j];
                    }
                }
            }
        }

        return res;
    }
    public static void main(String[] args) {
        FloydWarshall obj = new FloydWarshall();
        long[][] paths = obj.findShortestPaths(GraphUtils.getDiGraphWeighted1());
        for (long[] row : paths) {
            System.out.print("[ ");
            for (long p : row) {
                System.out.print(p + ", ");
            }
            System.out.print(" ]\n");
        }

        paths = obj.findShortestPaths(GraphUtils.getDiGraphWeighted4());
        for (long[] row : paths) {
            System.out.print("[ ");
            for (long p : row) {
                System.out.print(p + ", ");
            }
            System.out.print(" ]\n");
        }
    }
}
