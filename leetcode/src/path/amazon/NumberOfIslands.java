package path.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Idea is to handle grid as graph and use Union Find to find connected components.
 * Create Union Find as flattened version of the grid, connect islands on each step and calculated connected components
 *
 */
public class NumberOfIslands {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList();
        UF uf = new UF(n*m);
        List<Integer> overlap = new ArrayList();
        for (int[] point : positions) {
            int r = point[0];
            int c = point[1];
            overlap.clear();
            if (r - 1 >= 0 && uf.isValid((r - 1) * n + c))
                overlap.add((r - 1) * n + c);
            if (r + 1 < m && uf.isValid((r + 1) * n + c))
                overlap.add((r + 1) * n + c);
            if (c - 1 >= 0 && uf.isValid(r * n + c - 1))
                overlap.add(r * n + c - 1);
            if (c + 1 < n && uf.isValid(r * n + c + 1))
                overlap.add(r * n + c + 1);

            int index = r*n + c;
            uf.setParent(index);
            for (int over : overlap)
                uf.union(over, index);
            res.add(uf.count);
        }
        return res;
    }

    class UF {
        int[] parent;
        int[] rank;
        int count = 0;

        UF(int numOfEdges) {
            parent = new int[numOfEdges];
            IntStream.range(0, numOfEdges).forEach(i->parent[i] = -1);
            rank = new int[numOfEdges];
            count = 0;
        }

        boolean isValid(int i) {
            return parent[i] >=0;
        }

        void setParent(int p) {
            parent[p] = p;
            count++;
        }

        int find(int i) {
            if (parent[i] != i)
                parent[i] = find(parent[i]);
            return parent[i];
        }

        void union(int i, int j) {
            int ir = find(i);
            int jr = find(j);
            if (ir != jr) {
                if (rank[ir] > rank[jr])
                    parent[jr] = ir;
                else if (rank[ir] < rank[jr])
                    parent[ir] = jr;
                else {
                    parent[jr] = ir;
                    rank[ir]++;
                }
                count--;
            }
        }
    }
}
