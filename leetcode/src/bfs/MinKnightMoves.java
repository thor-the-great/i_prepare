package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1197. Minimum Knight Moves
 * Medium
 *
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 *
 * A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction,
 * then one square in an orthogonal direction.
 *
 *
 *
 * Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 2, y = 1
 * Output: 1
 * Explanation: [0, 0] → [2, 1]
 * Example 2:
 *
 * Input: x = 5, y = 5
 * Output: 4
 * Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 *
 *
 * Constraints:
 *
 * |x| + |y| <= 300
 */
public class MinKnightMoves {

    //store possible moves from one point as an array of changes in coordinates
    static int[][] d = new int[][] {
            {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}
    };

    public int minKnightMoves(int x, int y) {
        //we can invert the sign of the number - it doesn't affect the result
        x = Math.abs(x); y = Math.abs(y);
        //store seen cells
        Set<Integer> seen = new HashSet();
        //this is queue for the BFS, initialize it with 0,0 position
        Queue<Integer> q = new LinkedList();
        q.add(0);
        //this will store the number of moves
        int res = 0;
        //start BFS
        while (!q.isEmpty()) {
            //on each step we only poll number of cells that we have in the queue now.
            //everything added after this will be counted at the next step
            int size = q.size();
            for (int i = 0; i < size; i++) {
                //get the encoded num, convert it to coordinated and check if it's our target
                int next = q.poll();
                int curX = (next>>10), curY = next - (curX<<10);
                if (curX == x && curY == y)
                    return res;
                //if not the target - make all possible moves
                for (int k = 0; k < d.length; k++) {
                    //each next move
                    int x1 = curX + d[k][0], y1 = curY + d[k][1];
                    //encode the move to store it in the set of visited cells
                    int curEnc = y1 + (x1<<10);
                    if (x1 >= -2 && y1 >= -2 && seen.add(curEnc)) {
                        q.add(curEnc);
                    }
                }
            }
            //after we done with all moves from all points stored in te queue at the beggining -
            //increment the step counter
            res++;
        }
        return -1;
    }
}
