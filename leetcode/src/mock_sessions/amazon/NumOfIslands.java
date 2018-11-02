package mock_sessions.amazon;

import java.util.*;

public class NumOfIslands {
    public static void main(String[] args) {
        NumOfIslands obj = new NumOfIslands();
        System.out.println(obj.numIslands(new char[][] {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        }));
    }

    public int numIslands(char[][] grid) {
        return bfsReuseGrid(grid);
        //return bfsSetVisitedPointObjects(grid);
    }

    int[][] dir = new int[][] {
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
                    while(!q.isEmpty()) {
                        Point nextPoint = q.poll();
                        if (!visited.contains(nextPoint) && grid[nextPoint.x][nextPoint.y] == '1') {
                            visited.add(nextPoint);
                            for (int d = 0; d < dir.length; d++) {
                                int nextR = nextPoint.x + dir[d][0];
                                int nextC = nextPoint.y + dir[d][1];
                                boolean result;
                                if (nextR >=0 && nextR < rows && nextC >= 0 && nextC < cols)
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
                Point pointObj = (Point)o;
                return pointObj.x == this.x && pointObj.y == this.y;
            }
            return false;
        }

        public int hashCode(){
            return x*31^y;
        }
    }
}
