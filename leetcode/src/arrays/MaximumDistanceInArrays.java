package arrays;

import java.util.List;

/**
 * 624. Maximum Distance in Arrays
 * Easy
 *
 * Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers
 * from two different arrays (each array picks one) and calculate the distance. We define the
 * distance between two integers a and b to be their absolute difference |a-b|. Your task is to
 * find the maximum distance.
 *
 * Example 1:
 * Input:
 * [[1,2,3],
 *  [4,5],
 *  [1,2,3]]
 * Output: 4
 * Explanation:
 * One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in
 * the second array.
 * Note:
 * Each given array will have at least 1 number. There will be at least two non-empty arrays.
 * The total number of the integers in all the m arrays will be in the range of [2, 10000].
 * The integers in the m arrays will be in the range of [-10000, 10000].
 *
 */
public class MaximumDistanceInArrays {

  /**
   * Candidates are only among first/last elements because they are min/max. To avoid using both min and max
   * from the same array need to keep track of mion and max in a separate variables
   * @param arrays
   * @return
   */
  public int maxDistance(List<List<Integer>> arrays) {
    int res = 0;
    //running min and max, use constrained values
    int min = 10001, max = -10001;

    for (int i = 0; i < arrays.size(); i++) {
      List<Integer> cur = arrays.get(i);
      //min and max elements for the current array
      int minEl = cur.get(0), maxEl = cur.get(cur.size() - 1);
      //get running max result among this max and prev min and prev max and
      //current min
      res = Math.max(res, Math.max(maxEl - min, max - minEl));
      //update running min/max
      min = Math.min(min, minEl);
      max = Math.max(max, maxEl);
    }
    return res;
  }
}
