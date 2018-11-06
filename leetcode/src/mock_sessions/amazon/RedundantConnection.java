package mock_sessions.amazon;

import java.util.Arrays;
import java.util.stream.IntStream;

public class RedundantConnection {

    public static void main(String[] args) {
        RedundantConnection obj = new RedundantConnection();
        int[] res = obj.findRedundantConnection(new int[][] {{1,2}, {1,3}, {2,3}});
        System.out.print(res[0] + " " + res[1]);
    }

    public int[] findRedundantConnection(int[][] edges) {
        UF unFind = new UF(1001);
        for (int[] edge : edges) {
            if (!unFind.union(edge[0], edge[1]))
                return edge;
        }
        return new int[2];
    }

    class UF {
        int[] parent;
        int[] rank;

        UF(int numOfEdges) {
            parent = new int[numOfEdges];
            IntStream.range(0, numOfEdges).forEach(i->parent[i] = i);
            rank = new int[numOfEdges];
        }

        int find(int i) {
            if (parent[i] != i)
                parent[i] = find(parent[i]);
            return parent[i];
        }

        boolean union(int i, int j) {
            int ir = find(i);
            int jr = find(j);
            if (ir == jr)
                return false;
            else if (rank[ir] > rank[jr])
                parent[jr] = ir;
            else if (rank[ir] < rank[jr])
                parent[ir] = jr;
            else {
                parent[jr] = ir;
                rank[ir]++;
            }

            return true;
        }
    }
}
