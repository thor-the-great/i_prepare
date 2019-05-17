package random_problems;

/**
 * 1037. Valid Boomerang
 * Easy
 *
 * 27
 *
 * 79
 *
 * Favorite
 *
 * Share
 * A boomerang is a set of 3 points that are all distinct and not in a straight line.
 *
 * Given a list of three points in the plane, return whether these points are a boomerang.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,1],[2,3],[3,2]]
 * Output: true
 * Example 2:
 *
 * Input: [[1,1],[2,2],[3,3]]
 * Output: false
 *
 *
 * Note:
 *
 * points.length == 3
 * points[i].length == 2
 * 0 <= points[i][j] <= 100
 */
public class ValidBoomerang {

  /**
   * Slopes of two points must bew same. Slope1 = y_dif1/x_dif1; Slope2 = y_dif2/x_dif2
   * per proportion => y_dif1*x_dif2 = x_dif1*y_dif2
   *
   * @param points
   * @return
   */
  public boolean isBoomerang(int[][] points) {
    return ((points[0][1] - points[1][1])*(points[1][0] - points[2][0]) !=
        (points[0][0] - points[1][0])*(points[1][1] - points[2][1]));
  }

  public static void main(String[] args) {
    ValidBoomerang obj = new ValidBoomerang();

    System.out.println(obj.isBoomerang(new int[][] {
        {1,1},{2,2},{3,3}
    }));

    System.out.println(obj.isBoomerang(new int[][] {
        {0,0},{0,2},{2,1}
    }));

    System.out.println(obj.isBoomerang(new int[][] {
        {0,0},{2,2},{1,0}
    }));
    //true

    System.out.println(obj.isBoomerang(new int[][] {
        {0,2},{2,1},{0,0}
    }));//true

    System.out.println(obj.isBoomerang(new int[][] {
        {1,0},{0,0},{2,0}
    }));//false
  }
}
