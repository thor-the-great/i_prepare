package mock_sessions.amazon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class NumOfIslands {
    int[][] dir = new int[][] {
            {0, -1}, {1, 0}, {0, 1}, {-1, 0}
    };

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
                                if (isValid(rows, cols, nextR, nextC)) {
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

    boolean isValid(int rows, int cols, int r, int c) {
        if (r >=0 && r < rows && c >= 0 && c < cols)
            return true;
        else
            return false;
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
