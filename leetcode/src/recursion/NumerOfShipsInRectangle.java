package recursion;

/**
 * 1274. Number of Ships in a Rectangle
 * Hard
 *
 * (This problem is an interactive problem.)
 *
 * On the sea represented by a cartesian plane, each ship is located at an integer point, and each integer point may
 * contain at most 1 ship.
 *
 * You have a function Sea.hasShips(topRight, bottomLeft) which takes two points as arguments and returns true if and
 * only if there is at least one ship in the rectangle represented by the two points, including on the boundary.
 *
 * Given two points, which are the top right and bottom left corners of a rectangle, return the number of ships present
 * in that rectangle.  It is guaranteed that there are at most 10 ships in that rectangle.
 *
 * Submissions making more than 400 calls to hasShips will be judged Wrong Answer.  Also, any solutions that attempt
 * to circumvent the judge will result in disqualification.
 *
 *
 *
 * Example :
 *
 *
 *
 * Input:
 * ships = [[1,1],[2,2],[3,3],[5,5]], topRight = [4,4], bottomLeft = [0,0]
 * Output: 3
 * Explanation: From [0,0] to [4,4] we can count 3 ships within the range.
 *
 *
 * Constraints:
 *
 * On the input ships is only given to initialize the map internally. You must solve this problem "blindfolded". In
 * other words, you must find the answer using the given hasShips API, without knowing the ships position.
 * 0 <= bottomLeft[0] <= topRight[0] <= 1000
 * 0 <= bottomLeft[1] <= topRight[1] <= 1000
 */
public class NumerOfShipsInRectangle {

    /**
     * divide each rectangle on 4 parts, check every part
     * @param sea
     * @param topRight
     * @param bottomLeft
     * @return
     */
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        int res = 0;
        //only continue with rectangles that are valid and have ships
        if (topRight[0] >= bottomLeft[0]
                && topRight[1] >= bottomLeft[1]
                && sea.hasShips(topRight, bottomLeft)) {

            //this is rect of 1 point only - return 1
            if (topRight[0] == bottomLeft[0]
                    && topRight[1] == bottomLeft[1])
                return 1;
            //center point
            int newX = (bottomLeft[0] + topRight[0])/2;
            int newY = (bottomLeft[1] + topRight[1])/2;

            //split into 4 squares
            //up-right
            res+= countShips(sea, topRight, new int[] {newX + 1, newY + 1});
            //up-left
            res+= countShips(sea,
                    new int[] {newX, topRight[1]},
                    new int[] {bottomLeft[0], newY + 1});

            //bottom-left
            res+= countShips(sea, new int[] {newX, newY}, bottomLeft);
            //bottom-right
            res+= countShips(sea,
                    new int[] {topRight[0], newY},
                    new int[] {newX + 1, bottomLeft[1]});
        }
        return res;
    }

    class Sea {
        public boolean hasShips(int[] topRight, int[] bottomLeft) {return true;}
    }
}
