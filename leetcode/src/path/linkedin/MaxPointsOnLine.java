package path.linkedin;

import util.Point;

public class MaxPointsOnLine {

    public int maxPoints(Point[] points) {
        int N = points.length;
        if (N <=2 )
            return N;
        int max = 0;
        for (int i = 0; i < N - 2; i ++) {
            for (int j = i + 1; j < N-1; j++) {
                int c = 2;
                
                double slope = (points[j].x == points[i].x) ? 0.0 :
                        (double)(points[j].y - points[i].y)/(points[j].x - points[i].x);
                for (int k = j + 1; k < N; k++) {
                    if (isSamePoint(points[k], points[i])) {
                        c++;
                        continue;
                    }
                    double checkSlope = (points[k].x == points[i].x) ? 0.0 :
                            (double)(points[k].y - points[i].y)/(points[k].x - points[i].x);
                    if (slope == checkSlope)
                        c++;
                }
                max = Math.max(max, c);
            }
        }
        return max;
    }

    boolean isSamePoint(Point p1, Point p2) {
        return p1.x == p2.x && p1.y == p2.y;
    }

    public static void main(String[] args) {
        MaxPointsOnLine obj = new MaxPointsOnLine();
        Point[] points;
        /*points = new Point[] {
                new Point(0, 0),
                new Point(1, 1),
                new Point(0, 0)
        };
        System.out.println(obj.maxPoints(points));
        */
        points = new Point[] {
                new Point(4, 0),
                new Point(4, -1),
                new Point(4, 5)
        };
        System.out.println(obj.maxPoints(points));
    }
}
