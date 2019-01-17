package path.google;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 505. The Maze II
 * Medium
 *
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down,
 * left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 *
 * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at
 * the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position
 * (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.
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
 * Output: 12
 *
 * Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
 *              The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
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
 * Output: -1
 *
 * Explanation: There is no way for the ball to stop at the destination.
 *
 *
 *
 * Note:
 *
 * There is only one ball and one destination in the maze.
 * Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 * The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the
 * border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
public class MazeII {

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length;
        int cols = maze[0].length;
        int[][] distances = new int[maze.length][maze[0].length];
        for (int[] row: distances)
            Arrays.fill(row, Integer.MAX_VALUE);

        int[][] dirs = new int[][] {
                {0, 1}, {0, -1}, {1, 0}, {-1, 0}
        };

        distances[start[0]][start[1]] = 0;

        Queue<int[]> q = new LinkedList();
        q.add(start);
        while(!q.isEmpty()) {
            int[] point = q.poll();
            int r = point[0];
            int c = point[1];

            for (int[] dir : dirs) {
                int newR = r + dir[0];
                int newC = c + dir[1];
                int steps = 0;
                while (newR >=0 && newR < rows && newC >=0 && newC < cols && maze[newR][newC] == 0) {
                    newR += dir[0];
                    newC += dir[1];
                    steps++;
                }
                int rBack = newR - dir[0];
                int cBack = newC - dir[1];
                if (distances[r][c] + steps < distances[rBack][cBack]) {
                    distances[rBack][cBack] = distances[r][c] + steps;
                    q.add(new int[] {rBack, cBack});
                }
            }
        }
        return distances[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distances[destination[0]][destination[1]];
    }
}
