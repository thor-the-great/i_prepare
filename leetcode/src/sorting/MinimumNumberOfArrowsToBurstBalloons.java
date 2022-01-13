package sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 452. Minimum Number of Arrows to Burst Balloons
Medium

There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.

Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.

Given the array points, return the minimum number of arrows that must be shot to burst all balloons.

Example 1:

Input: points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
Example 2:

Input: points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4
Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
Example 3:

Input: points = [[1,2],[2,3],[3,4],[4,5]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
 

Constraints:

1 <= points.length <= 105
points[i].length == 2
-231 <= xstart < xend <= 231 - 1

https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/submissions/
 */
public class MinimumNumberOfArrowsToBurstBalloons {

    /**
     * Greedy approach should work, in case some arrow cannot burst next baloon it's gonna be a new arrow, so there is nothing to optimize.
     * order points by startx coordinate, 
     * - starting with point 0 keep startx and endx coordinates as the interval that one arrow covers
     * - for each next point check if it's startx <= then interval endx. If it's - update endx if needed (min of (endx, current point endx))
     * if it > - throw this interval (+1 to the result) and start a new one (new arrow)
     */
    public int findMinArrowShots(int[][] points) {
        Comparator<int[]> comp = (a1, a2) -> Integer.compare(a1[0],a2[0]);
        Arrays.sort(points, comp);
        
        int res = 1;
        int right = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > right) {
                res++;
                right = points[i][1];
            } else {
                right = Math.min(right, points[i][1]);
            }
        }
        return res;
    }
}
