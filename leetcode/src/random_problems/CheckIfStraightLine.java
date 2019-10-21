package random_problems;

/**
 * 1232. Check If It Is a Straight Line
 * Difficulty: Easy
 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point.
 * Check if these points make a straight line in the XY plane.
 *
 * Example 1:
 * Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * Output: true
 * Example 2:
 * Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * Output: false
 *
 * Constraints:
 *
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates contains no duplicate point.
 */
public class CheckIfStraightLine {

    /**
     * Slpope must be the same between any two points. Get slope for 0 and 1 points, then check if it's the same for
     * 0 and i-th points
     * @param coordinates
     * @return
     */
    public boolean checkStraightLine(int[][] coordinates) {
        //get slope for 0 and 1 points
        double slope = getSlope(coordinates[0], coordinates[1]);
        //scan array starting from point 2
        for (int i = 2; i < coordinates.length; i++) {
            //check if slope for 0 and i-th point is the same as first slope
            if (getSlope(coordinates[0], coordinates[i]) != slope)
                //if it's different slope - condition is broken
                return false;
        }
        return true;
    }

    double getSlope(int[] c1, int[] c2) {
        //get slope for 2 points
        return (c1[0] == c2[0]) ? Double.POSITIVE_INFINITY : (double)(c1[1] - c2[1])/(c1[0] - c2[0]);
    }
}
