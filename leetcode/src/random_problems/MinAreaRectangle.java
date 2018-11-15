package random_problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 939. Minimum Area Rectangle
 * Difficulty: Medium
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with
 * sides parallel to the x and y axes.
 *
 * If there isn't any rectangle, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
 * Output: 4
 * Example 2:
 *
 * Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 * Output: 2
 *
 *
 * Note:
 *
 * 1 <= points.length <= 500
 * 0 <= points[i][0] <= 40000
 * 0 <= points[i][1] <= 40000
 * All points are distinct.
 *
 */
public class MinAreaRectangle {

    /**
     * idea is - check only points that are
     * @param points
     * @return
     */
    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> m = new HashMap();
        for (int[] p : points) {
            m.putIfAbsent(p[0], new HashSet());
            m.get(p[0]).add(p[1]);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < i; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                if (p1[0] == p2[0] || p1[1] == p2[1])
                    continue;
                if (m.get(p1[0]).contains(p2[1]) && m.get(p2[0]).contains(p1[1])) {
                    min = Math.min(min, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]) );
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static void main(String[] args) {
        MinAreaRectangle obj = new MinAreaRectangle();
        int[][] points = new int[][] {
                {1,1},{1,3},{3,1},{3,3},{4,1},{4,3}
        };
        System.out.println(obj.minAreaRect(points));
    }
}
