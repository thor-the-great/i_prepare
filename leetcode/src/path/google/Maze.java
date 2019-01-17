package path.google;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 490. The Maze
 * Medium
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left
 * or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 *
 * Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.
 *
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the
 * borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
 *
 *
 *
 * Example 1:
 *
 * Input 1: a maze represented by a 2D array
 *
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 *
 * Output: true
 *
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 *
 * Example 2:
 *
 * Input 1: a maze represented by a 2D array
 *
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
 *
 * Output: false
 *
 * Explanation: There is no way for the ball to stop at the destination.
 *
 *
 *
 * Note:
 *
 * There is only one ball and one destination in the maze.
 * Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 * The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
public class Maze {

    int rows;
    int cols;

    /**
     * Idea - do the BFS, at each end point check for destination. Re-use original matrix for "visited' flag
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        rows = maze.length;
        cols = maze[0].length;

        int[][] dirs = new int[][] {
                {0, 1}, {0, -1}, {1, 0}, {-1, 0}
        };

        Queue<int[]> q = new LinkedList();
        q.add(start);
        while(!q.isEmpty()) {
            int[] point = q.poll();
            int r = point[0];
            int c = point[1];
            if (destination[0] == r && destination[1] == c)
                return true;
            maze[r][c] = 2;
            for (int[] dir : dirs) {
                int newR = r;
                int newC = c;
                while (valid(newR + dir[0], newC+dir[1]) && maze[newR + dir[0]][newC+dir[1]] != 1) {
                    newR += dir[0];
                    newC += dir[1];
                }
                if (maze[newR][newC] != 2) {
                    q.add(new int[] {newR, newC});
                }
            }
        }
        return false;
    }

    boolean valid(int r, int c) {
        if (r >=0 && r < rows && c>= 0 && c < cols)
            return true;
        return false;
    }
}
