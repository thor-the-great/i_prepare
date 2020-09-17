package random_problems;

/**
 * 1041. Robot Bounded In Circle
Medium

On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:

"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degress to the right.
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

 

Example 1:

Input: "GGLLGG"
Output: true
Explanation: 
The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
Example 2:

Input: "GG"
Output: false
Explanation: 
The robot moves north indefinitely.
Example 3:

Input: "GL"
Output: true
Explanation: 
The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 

Note:

1 <= instructions.length <= 100
instructions[i] is in {'G', 'L', 'R'}

https://leetcode.com/problems/robot-bounded-in-circle/

 */
public class RobotBoundedInCircle {
    /**
     * Idea is - if the robot returned back to 0,0 - it will be going in circles.
     * Another option is that if it's not pointing north - in such case it will eventually hit the 0,0. 
     * So the problem is to calculate the final position and the direction. 
     * @param instructions
     * @return
     */
    public boolean isRobotBounded(String instructions) {
        int[] dir = new int[] {1, 0};
        int[] pos = new int[] {0, 0};
        
        for (int i = 0; i < instructions.length(); i++) {
            char ch = instructions.charAt(i);
            if (ch == 'G') {
                pos[0]+=dir[0];
                pos[1]+=dir[1];
            } else {
                nextDirection(ch, dir);
            }    
        }
        //check the final position and direction
		//for direction check if rows is anything but 1, but if it's not then at least cols is not 0 - otherwise this is north for sure
        return (pos[0] == 0 && pos[1] == 0) || (dir[0] != 1 || dir[1] != 0);
    }
    
    void nextDirection(char ch, int[] dir) {
        if (dir[0] != 0) {
            dir[1] = ch == 'L' ? -dir[0] : dir[0];
            dir[0] = 0;
        } else {
            dir[0] = ch == 'L' ? dir[1] : -dir[1];
            dir[1] = 0;
        }
    }
}
