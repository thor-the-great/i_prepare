package path.google;

import java.util.HashSet;
import java.util.Set;

/**
 * Robot Room Cleaner
 * Given a robot cleaner in a room modeled as a grid.
 *
 * Each cell in the grid can be empty or blocked.
 *
 * The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
 *
 * When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
 *
 * Design an algorithm to clean the entire room using only the 4 given APIs shown below.
 *
 * interface Robot {
 *   // returns true if next cell is open and robot moves into the cell.
 *   // returns false if next cell is obstacle and robot stays on the current cell.
 *   boolean move();
 *
 *   // Robot will stay on the same cell after calling turnLeft/turnRight.
 *   // Each turn will be 90 degrees.
 *   void turnLeft();
 *   void turnRight();
 *
 *   // Clean the current cell.
 *   void clean();
 * }
 * Example:
 *
 * Input:
 * room = [
 *   [1,1,1,1,1,0,1,1],
 *   [1,1,1,1,1,0,1,1],
 *   [1,0,1,1,1,1,1,1],
 *   [0,0,0,1,0,0,0,0],
 *   [1,1,1,1,1,1,1,1]
 * ],
 * row = 1,
 * col = 3
 *
 * Explanation:
 * All grids in the room are marked by either 0 or 1.
 * 0 means the cell is blocked, while 1 means the cell is accessible.
 * The robot initially starts at the position of row=1, col=3.
 * From the top left corner, its position is one row below and three columns right.
 * Notes:
 *
 * The input is only given to initialize the room and the robot's position internally. You must solve this problem
 * "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room
 * layout and the initial robot's position.
 * The robot's initial position will always be in an accessible cell.
 * The initial direction of the robot will be facing up.
 * All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
 * Assume all four edges of the grid are all surrounded by wall.
 *
 */
public class RobotRoomCleaning {

    interface Robot {
        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        public boolean move();
        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        public void turnLeft();
        public void turnRight();
        // Clean the current cell.
        public void clean();
    }

    Set<Integer> visited = new HashSet();
    Robot robo;

    public void cleanRoom(Robot robot) {
        //idea is to use DFS (backtracking) and keep track of visited cell based on "fake" coordinates. Start tracking
        //robot from 0,0 and keep track of direction so we know where we going.
        //as visited points we keep number represented the point - (r<<15 + c).
        visited.clear();
        robo = robot;
        //directions are 0 - up, 1 - right, 2 - down, 3 - left
        visit(0, 0, 0);
    }

    private void visit(int r, int c, int d) {
        int point = (r << 15) + c;
        if (visited.contains(point)) return;

        robo.clean();
        visited.add(point);

        for (int i = 0; i < 4; i++) {
            if (robo.move()) {
                if (d == 0) {
                    visit(r - 1, c, d);
                } else if (d == 1) {
                    visit(r, c + 1, d);
                } else if (d == 2) {
                    visit(r + 1, c, d);
                } else {
                    visit(r, c - 1, d);
                }
                //go back (do backtracking)
                robo.turnLeft();
                robo.turnLeft();
                robo.move();
                robo.turnRight();
                robo.turnRight();
            }

            robo.turnRight();
            d++;
            d = d % 4;
        }
    }
}
