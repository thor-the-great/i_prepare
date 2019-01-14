package random_problems;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * 973. K Closest Points to Origin
 * Difficulty: Easy
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 *
 * Note:
 *
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */
public class KClosestPoints {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Point> pq = new PriorityQueue(K, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Long.compare(o2.d, o1.d);
            }
        });

        for (int i = 0; i < points.length; i++) {
            Point p = new Point(points[i][0], points[i][1]);
            pq.add(p);
            if (pq.size() > K) {
                pq.poll();
            }
        }

        int[][] res = new int[K][2];
        Iterator<Point> it = pq.iterator();
        int c = 0;
        while(it.hasNext()) {
            Point p = it.next();
            res[c] = p.c;
            c++;
        }
        return res;
    }

    class Point {
        int[] c;
        long d;

        Point(int x, int y) {
            c = new int[] {x, y};
            d = x*x + y*y;
        }
    }
}
