package path.linkedin;

import util.Point;

import java.util.HashMap;
import java.util.Map;

/**
 * 149. Max Points on a Line
 * Hard
 *
 * Share
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 * Example 1:
 *
 * Input: [[1,1],[2,2],[3,3]]
 * Output: 3
 * Explanation:
 * ^
 * |
 * |        o
 * |     o
 * |  o
 * +------------->
 * 0  1  2  3  4
 * Example 2:
 *
 * Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 * Explanation:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 */
public class MaxPointsOnLine {
    /**
     * Idea - take one point and for it check slope for every other point, save the count of the same slope points to
     * the map. Also save count of same points and same X points (where slope is not defined). Then find max between
     * those values. Do the same for every point as a starting point
     *
     * @param points
     * @return
     */
    public int maxPoints(Point[] points) {
        int N = points.length;
        if (N <=2 )
            return N;
        int max = 0;
        Map<Double, Integer> m = new HashMap<>();
        for (int i = 0; i < N; i ++) {
            m.clear();
            int samePoint = 0;
            int sameX = 0;
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                if (isSamePoint(points[i], points[j])) {
                    samePoint++;
                    continue;
                }
                if (points[i].x == points[j].x) {
                    sameX++;
                    continue;
                }

                double slope =
                        (double)(points[j].x - points[i].x)/(points[j].y - points[i].y);

                m.put(slope, m.getOrDefault(slope, 0) + 1);
            }
            int maxLocal = sameX;
            for(double slope : m.keySet()) {
                maxLocal = Math.max(maxLocal, m.get(slope));
            }
            maxLocal += samePoint + 1;
            max = Math.max(max, maxLocal);
        }
        return max;
    }

    boolean isSamePoint(Point p1, Point p2) {
        return p1.x == p2.x && p1.y == p2.y;
    }

    public static void main(String[] args) {
        MaxPointsOnLine obj = new MaxPointsOnLine();
        Point[] points;
        points = new Point[] {
                new Point(0, 0),
                new Point(1, 1),
                new Point(0, 0)
        };
        System.out.println(obj.maxPoints(points));

        points = new Point[] {
                new Point(4, 0),
                new Point(4, -1),
                new Point(4, 5)
        };
        System.out.println(obj.maxPoints(points));

        points = new Point[] {
                new Point(1, 1),
                new Point(1, 1),
                new Point(2, 2),
                new Point(2, 2)
        };
        System.out.println(obj.maxPoints(points));

        points = new Point[] {
                new Point(0,0),
                new Point(94911151,94911150),
                new Point(94911152,94911151)
        };
        System.out.println(obj.maxPoints(points));
    }
}
