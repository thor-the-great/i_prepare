package cracking.recursion_dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RobotPath {

    List<int[]> getPath(boolean[][] maze) {
        List<int[]> path = new ArrayList<>();
        checkStep(maze, 0, 0, path, new boolean[maze.length][maze[0].length]);
        return path;
    }

    boolean checkStep(boolean[][] maze, int col, int row, List<int[]> path, boolean[][] visited) {
        if (col >= maze[0].length || row >= maze.length || !maze[row][col])
            return false;

        if (visited[row][col])
            return false;

        boolean finalStop = (col == maze[0].length - 1 && row == maze.length - 1);

        if (finalStop || checkStep(maze, col + 1, row, path, visited) || checkStep(maze, col, row + 1, path, visited)) {
            int[] nextPoint = new int[]{row, col};
            path.add(nextPoint);
            return true;
        }
        visited[row][col] = true;
        return false;
    }

    public static void main(String[] args) {
        RobotPath obj = new RobotPath();
        int row = 1000;
        int col = 1000;
        boolean[][] maze = new boolean[row][col];
        Random r = new Random();
        for(int i = 0; i < row; i++) {
            System.out.print("[ ");
            for(int j = 0; j < col; j++) {
                maze[i][j] = r.nextInt(100) > 5;
                System.out.print(maze[i][j] ? "_ " : "X ");
            }
            System.out.println(" ]");
        }
        List<int[]> path = obj.getPath(maze);
        System.out.println("");
        System.out.println("Path has " + path.size());
        /*System.out.println("");
        for(int i = 0; i < row; i++) {
            System.out.print("[ ");
            for(int j = 0; j < col; j++) {
                boolean isInPath = false;
                for (int[] point : path) {
                    if (point[0] == i && point[1] == j) {
                        System.out.print("8 ");
                        isInPath = true;
                        break;
                    }
                }
                if (!isInPath)
                    System.out.print(maze[i][j] ? "_ " : "X ");
            }
            System.out.println(" ]");
        }*/
    }
}
