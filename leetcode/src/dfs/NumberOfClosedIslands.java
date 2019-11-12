package dfs;

/**
 * 1254. Number of Closed Islands
 * Medium
 *
 * Given a 2D g consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of
 * 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
 *
 * Return the number of closed islands.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: g = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * Output: 2
 * Explanation:
 * Islands in gray are closed because they are completely surrounded by water (group of 1s).
 * Example 2:
 *
 *
 *
 * Input: g = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * Output: 1
 * Example 3:
 *
 * Input: g = [[1,1,1,1,1,1,1],
 *                [1,0,0,0,0,0,1],
 *                [1,0,1,1,1,0,1],
 *                [1,0,1,0,1,0,1],
 *                [1,0,1,1,1,0,1],
 *                [1,0,0,0,0,0,1],
 *                [1,1,1,1,1,1,1]]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= g.length, g[0].length <= 100
 * 0 <= g[i][j] <=1
 */
public class NumberOfClosedIslands {
    int[][] g;

    /**
     * Mark edge islands as water. Then iterate via dfs and use union find to join islands
     * @param grid
     * @return
     */
    public int closedIsland(int[][] grid) {
        g = grid;
        int rows = g.length, cols = g[0].length;
        //mark edge land cells as water cells
        for (int r = 0; r < rows; ++r) {
            dfs(r, 0);
            dfs(r, cols - 1);
        }
        for (int c = 1; c < cols - 1; ++c) {
            dfs(0, c);
            dfs(rows - 1, c);
        }
        //init UF, now we can count the rest of islands without limit for the edge cells
        UnionFind uf = new UnionFind(g);
        //skip 0 and last row and column - we can't take land cell from there even if we meet one
        for (int r = 1; r < rows - 1; ++r) {
            for (int c = 1; c < cols - 1; ++c) {
                //if this is island - mark is as water and union neighbouring cells
                if (g[r][c] == 0) {
                    g[r][c] = 1;
                    int coord = r * cols + c;
                    //check four neighbours
                    if (r - 1 > 0 && g[r - 1][c] == 0)
                        uf.union(coord, coord - cols);
                    if (c - 1 > 0 && g[r][c - 1] == 0)
                        uf.union(coord, coord- 1);
                    if (r + 1 < rows - 1 && g[r + 1][c] == 0)
                        uf.union(coord, coord + cols);
                    if (c + 1 < cols - 1 && g[r][c + 1] == 0)
                        uf.union(coord, coord + 1);
                }
            }
        }
        return uf.count;
    }


    void dfs(int r, int c) {
        if (g[r][c] == 0) {
            g[r][c] = 1;
            if (r > 0) dfs(r - 1, c);
            if (c > 0) dfs(r, c - 1);
            if (r < g.length - 1) dfs(r + 1, c);
            if (c < g[0].length - 1) dfs(r, c + 1);
        }
    }
}

class UnionFind {
    int[] parent;
    int count = 0;
    int ranks[];

    UnionFind(int[][] g) {
        int rows = g.length, cols = g[0].length;
        parent = new int[rows * cols];
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                if (g[r][c] == 0) {
                    int i = (r * cols) + c;
                    parent[i] = i;
                    count++;
                }
            }
        }
        ranks = new int[rows * cols];
    }

    int find(int n) {
        if (n != parent[n])
            parent[n] = find(parent[n]);
        return parent[n];
    }

    void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            if (ranks[rootA] > ranks[rootB]) {
                parent[rootB] = rootA;
            } else if (ranks[rootB] > ranks[rootA]) {
                parent[rootA] = rootB;
            } else {
                parent[rootA] = rootB;
                ranks[rootB] += 1;
            }
            count--;
        }
    }
}