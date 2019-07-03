package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinSpanningTree {

    List<DiGraph.Edge> kruskalMST(DiGraph g) {
        List<DiGraph.Edge> mst = new ArrayList<>();
        UnioFind uf = new UnioFind(g.getV());

        List<DiGraph.Edge> allEdges = g.adjAllEdges();
        Comparator<DiGraph.Edge> comp = Comparator.comparingInt(o -> o.weight);
        Collections.sort(allEdges, comp);

        for (DiGraph.Edge e : allEdges) {
            int from = e.from;
            int to = e.to;

            if (uf.find(from) != uf.find(to)) {
                mst.add(e);
                uf.union(from, to);
            }

            if (mst.size() == g.getV() - 1)
                break;
        }

        return mst;
    }

    class UnioFind {
        int[] parent;
        int[] rank;

        UnioFind(int v) {
            parent = new int[v];
            for (int i = 0; i < v; i++) {
                parent[i] = i;
            }
            rank = new int[v];
        }

        int find(int u) {
            while (parent[u] != u)
                u = parent[u];

            return u;
        }

        void union(int u, int v) {
            int p1 = find(u);
            int p2 = find(v);

            if (p1 != p2) {
                int r1 = rank[p1];
                int r2 = rank[p2];
                if (r1 > r2) {
                    parent[p2] = p1;
                } else if (r2 > r1) {
                    parent[p1] = p2;
                } else {
                    parent[p1] = p2;
                    rank[p2]++;
                }
            }
        }
    }

    public static void main(String[] args) {
        MinSpanningTree obj = new MinSpanningTree();
        List<DiGraph.Edge> mst1 = obj.kruskalMST(GraphUtils.getDiGraphWeighted5());
        System.out.print(GraphUtils.getDiGraphWeighted5());
        System.out.println("Min spanning tree: ");
        mst1.forEach(e-> System.out.print(e.toString() + " "));

        mst1 = obj.kruskalMST(GraphUtils.getDiGraphWeighted6());
        System.out.print("\n" + GraphUtils.getDiGraphWeighted6());
        System.out.println("Min spanning tree: ");
        mst1.forEach(e-> System.out.print(e.toString() + " "));
    }
}
