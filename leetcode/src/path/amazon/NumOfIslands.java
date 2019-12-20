package path.amazon;

import java.util.*;

public class NumOfIslands {
    public static void main(String[] args) {
        NumOfIslands obj = new NumOfIslands();
        System.out.println(obj.numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        }));
    }

    public int numIslands(char[][] grid) {
        //return bfsReuseGrid(grid);
        //return bfsSetVisitedPointObjects(grid);
        return numIslandsUnionFindBased(grid);
    }

    int[][] dir = new int[][]{
            {0, -1}, {1, 0}, {0, 1}, {-1, 0}
    };

    private int bfsReuseGrid(char[][] grid) {
        if (grid == null) return 0;
        int rows = grid.length;
        if (rows == 0) return 0;
        int cols = grid[0].length;
        if (cols == 0) return 0;

        int res = 0;
        Queue<Integer> q = new ArrayDeque();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '#';
                    //start BFS for this island
                    res++;
                    q.clear();
                    q.add(r);
                    q.add(c);
                    while (!q.isEmpty()) {
                        int nextRow = q.poll();
                        int nextCol = q.poll();
                        for (int d = 0; d < dir.length; d++) {
                            int nextR = nextRow + dir[d][0];
                            int nextC = nextCol + dir[d][1];
                            if ((nextR >= 0 && nextR < rows && nextC >= 0 && nextC < cols) && grid[nextR][nextC] == '1') {
                                q.add(nextR);
                                q.add(nextC);
                                grid[nextR][nextC] = '#';
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    private int bfsSetVisitedPointObjects(char[][] grid) {
        if (grid == null) return 0;
        int rows = grid.length;
        if (rows == 0) return 0;
        int cols = grid[0].length;
        if (cols == 0) return 0;

        Set<Point> visited = new HashSet();
        int res = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Point p = new Point(r, c);
                if (grid[r][c] == '1' && !visited.contains(p)) {
                    //start BFS for this island
                    res++;
                    Queue<Point> q = new LinkedList();
                    q.add(p);
                    while (!q.isEmpty()) {
                        Point nextPoint = q.poll();
                        if (!visited.contains(nextPoint) && grid[nextPoint.x][nextPoint.y] == '1') {
                            visited.add(nextPoint);
                            for (int d = 0; d < dir.length; d++) {
                                int nextR = nextPoint.x + dir[d][0];
                                int nextC = nextPoint.y + dir[d][1];
                                boolean result;
                                if (nextR >= 0 && nextR < rows && nextC >= 0 && nextC < cols)
                                    result = true;
                                else
                                    result = false;
                                if (result) {
                                    q.add(new Point(nextR, nextC));
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object o) {
            if (o instanceof Point) {
                Point pointObj = (Point) o;
                return pointObj.x == this.x && pointObj.y == this.y;
            }
            return false;
        }

        public int hashCode() {
            return x * 31 ^ y;
        }
    }

    /**
     * idea is to use Union Find DS to keep track of islands
     *
     * @param grid
     * @return
     */
    public int numIslandsUnionFindBased(char[][] grid) {
        int rows = grid.length;
        if (rows == 0)
            return 0;
        int cols = grid[0].length;

        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    //checking 4 cells nearby
                    int cur = r * cols + c;

                    if ((r + 1) < rows && grid[r + 1][c] == '1')
                        uf.union(cur, cur + cols);

                    if ((c + 1) < cols && grid[r][c + 1] == '1')
                        uf.union(cur, cur + 1);
                }
            }
        }

        return uf.count;
    }

    class UnionFind {

        int[] parent;
        int[] rank;
        int count;

        UnionFind(char[][] grid) {
            int rows = grid.length, cols = grid[0].length;
            parent = new int[rows*cols];

            int run = 0;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid[r][c] == '1') {
                        parent[run] = run;
                        ++count;
                    }
                    ++run;
                }
            }
            rank = new int[rows*cols];
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);

            return parent[x];
        }

        void union(int x, int y) {
            int xP = find(x), yP = find(y);

            if (xP != yP) {
                if (rank[xP] > rank[yP]) {
                    parent[yP] = xP;
                } else if (rank[yP] > rank[xP]) {
                    parent[xP] = yP;
                } else {
                    parent[yP] = xP;
                    ++rank[xP];
                }
                --count;
            }
        }
    }
}
