package arrays;

/**
 * 554. Brick Wall
Medium

There is a rectangular brick wall in front of you with n rows of bricks. The ith row has some number of bricks each of the same 
height (i.e., one unit) but they can be of different widths. The total width of each row is the same.

Draw a vertical line from the top to the bottom and cross the least bricks. If your line goes through the edge of a brick, 
then the brick is not considered as crossed. You cannot draw a line just along one of the two vertical edges of the wall, 
in which case the line will obviously cross no bricks.

Given the 2D array wall that contains the information about the wall, return the minimum number of crossed bricks after 
drawing such a vertical line. 

Example 1:

Input: wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
Output: 2

Example 2:

Input: wall = [[1],[1],[1]]
Output: 3

 

Constraints:

    n == wall.length
    1 <= n <= 104
    1 <= wall[i].length <= 104
    1 <= sum(wall[i].length) <= 2 * 104
    sum(wall[i]) is the same for each row i.
    1 <= wall[i][j] <= 231 - 1


 * 
 * https://leetcode.com/problems/brick-wall/
 */
public class BrickWall {
    
    /**
     * Idea is that combined length will give the bricks edge that lets line pass
     * If look as a column the number of the same length segments gives number of times
     * line passes, so the num of rows - num of bricks edges will give the number of bricks 
     * cross 
     * 
     * O(n*m) time
     * O(m) space
     * 
     * @param wall
     * @return
     */
    public int leastBricks(List<List<Integer>> wall) {
        int rows = wall.size();
        if (rows == 0)
            return 0;
        
        Map<Integer, Integer> freq = new HashMap();
        int maxFreq = 0;
        for (List<Integer> oneRow : wall) {
            int cur = 0;
            for (int c = 0; c < oneRow.size() - 1; c++) {
                cur += oneRow.get(c);
                int next = 1;
                if (freq.containsKey(cur)) {
                    next+=freq.get(cur);
                } 
                freq.put(cur, next);
                maxFreq = Math.max(maxFreq, next);
            }
        }
        return rows - maxFreq;
    }    
}
