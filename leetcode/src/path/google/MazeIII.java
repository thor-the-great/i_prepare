package path.google;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MazeIII {
    int rows;
    int cols;

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        rows = maze.length;
        cols = maze[0].length;

        Distance[][] d = new Distance[rows][cols];

        for (Distance[] row : d) {
            for (int i = 0; i < row.length; i++) {
                row[i] = new Distance(Integer.MAX_VALUE, "");
            }
        }

        d[ball[0]][ball[1]].d = 0;

        int[][] dirs = new int[][] {
                {0, 1}, {0, -1}, {1, 0}, {-1, 0}
        };
        char[] dirCodes = new char[] {'r', 'l', 'd', 'u'};

        Queue<int[]> q = new LinkedList();
        q.add(ball);
        while(!q.isEmpty()) {
            int[] point = q.poll();
            int r = point[0];
            int c = point[1];

            for (int i = 0; i < dirs.length; i++) {
                int steps = 0;
                int[] dir = dirs[i];
                char dirCode = dirCodes[i];

                int newR = r;
                int newC = c;

                while (isValid(newR + dir[0], newC + dir[1]) && maze[newR + dir[0]][newC + dir[1]] == 0) {
                    newR += dir[0];
                    newC += dir[1];
                    steps++;
                    //check if we reach the hole
                    if (newR == hole[0] && newC == hole[1]) {
                        int curDistance = d[r][c].d + steps;
                        Distance minDistance = d[hole[0]][hole[1]];
                        if (curDistance < minDistance.d) {
                            minDistance.d = curDistance;
                            minDistance.path = d[r][c].path + dirCode;
                        } else if (curDistance == minDistance.d) {
                            //if same distance - check lex order of paths
                            String curPath = d[r][c].path + dirCode;
                            if (curPath.compareTo(minDistance.path) < 0) {
                                minDistance.path = curPath;
                            }
                        }
                    }
                }

                if (d[r][c].d + steps < d[newR][newC].d) {
                    d[newR][newC] = new Distance(d[r][c].d + steps, d[r][c].path + dirCode);
                    q.add(new int[] {newR, newC});
                }
            }
        }

        Distance holeD = d[hole[0]][hole[1]];
        if (holeD.d == Integer.MAX_VALUE)
            return "impossible";
        else
            return holeD.path;
    }

    boolean isValid(int r, int c) {
        if (r >= 0 && r < rows && c >= 0 && c < cols)
            return true;
        return false;
    }

    public static void main(String[] args) {
        MazeIII obj = new MazeIII();
        int[][] maze;

        maze = new int[][] {
                {0,1,0,0,1,0,0,1,0,0},{0,0,1,0,0,1,0,0,1,0},{0,0,0,0,0,0,1,0,0,1},
                {0,0,0,0,0,0,1,0,0,1},{0,1,0,0,1,0,0,1,0,0},{0,0,1,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,1,0,0,0},{1,0,0,1,0,0,0,0,0,1},{0,1,0,0,1,0,0,1,0,0},
                {0,0,0,0,0,1,0,0,1,0}
        };
        System.out.println(obj.findShortestWay(maze, new int[] {2,4}, new int[] {7, 6}));

        maze = new int[][] {
                {0,0,0,0,0},{1,1,0,0,1},{0,0,0,0,0},{0,1,0,0,1},{0,1,0,0,0}
        };
        System.out.println(obj.findShortestWay(maze, new int[] {4,3}, new int[] {0, 1}));
    }
}

class Distance {
    int d;
    String path;

    Distance(int d, String p) {
        this.d = d;
        this.path = p;
    }
}
