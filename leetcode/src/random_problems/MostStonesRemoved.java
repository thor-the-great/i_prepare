package random_problems;

import java.util.*;

public class MostStonesRemoved {

    public int removeStones(int[][] stones) {
        //return removeStonesBFSGraph(stones);
        return removeStonesUnionFind(stones);
    }

    public int removeStonesUnionFind(int[][] stones) {
        int N = stones.length;
        int MAX = 10_000;
        UF uf = new UF(2*MAX);
        for (int[] stone : stones) {
            uf.union(stone[0], stone[1] + MAX);
        }

        int compCount = 0;
        boolean[] checked = new boolean[2*MAX];
        for (int[] stone : stones) {
            int component = uf.find(stone[0]);
            if (!checked[component]) {
                compCount++;
                checked[component] = true;
            }
        }
        return N - compCount;
    }

    class UF {
        int[] parent;
        int[] rank;

        UF(int N) {
            parent = new int[N];
            for (int i =0; i < N; i++) {
                parent[i] = i;
            }
            rank = new int[N];
        }

        int find(int x) {
            int xx = x;
            while (xx != parent[xx])
                xx = parent[xx];
            return xx;
        }

        void union(int x, int y) {
            int xr = find(x);
            int yr = find(y);
            if (xr == yr) return;
            if (rank[xr] > rank[yr]) {
                parent[yr] = xr;
            } else if (rank[xr] < rank[yr]) {
                parent[xr] = yr;
            } else {
                parent[yr] = xr;
                rank[xr]+=1;
            }
        }
    }

    public int removeStonesBFSGraph(int[][] stones) {
        if (stones.length == 0) return 0;
        Map<Integer, List<Integer>> graph = new HashMap();
        for (int i = 0; i < stones.length; i++) {
            for (int j = i + 1; j < stones.length; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    int s1 = (stones[i][0] << 14) + stones[i][1];
                    int s2 = (stones[j][0] << 14) + stones[j][1];
                    graph.putIfAbsent(s1, new ArrayList());
                    graph.get(s1).add(s2);
                    graph.putIfAbsent(s2, new ArrayList());
                    graph.get(s2).add(s1);
                }
            }
        }

        Set<Integer> visited = new HashSet();
        int res = 0;
        Set<Integer> vertexes = graph.keySet();
        for (int vertex : vertexes) {
            if (!visited.contains(vertex)) {
                int prevVisited = visited.size();
                Queue<Integer> q = new LinkedList();
                q.add(vertex);
                visited.add(vertex);
                while(!q.isEmpty()) {
                    int v = q.poll();
                    if (graph.containsKey(v)) {
                        List<Integer> adj = graph.get(v);
                        for (int oneAdj : adj) {
                            if (!visited.contains(oneAdj)) {
                                q.add(oneAdj);
                                visited.add(oneAdj);
                            }
                        }
                    }
                }
                int compCount = visited.size() - prevVisited;
                res += compCount - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MostStonesRemoved obj = new MostStonesRemoved();
        System.out.println(obj.removeStones(new int[][] {
                {0,0},
                {0,1},
                {1,0},
                {1,2},
                {2,1},
                {2,2}
        }));
    }
}
