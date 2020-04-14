package map;

import java.util.HashMap;
import java.util.Map;

/**
 * 525. Contiguous Array
 * Medium
 *
 * Given a binary array, find the maximum length of a contiguous subarray with equal
 * number of 0 and 1.
 *
 * Example 1:
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 * Example 2:
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * Note: The length of the given binary array will not exceed 50,000.
 */
public class ContiguousArray {

  /**
   * If we count 1 as +1 and 0 as -1 we can use just one var to see if we have balance from
   * current point to one of previous points. For that keep values of count in map (50.000 max)
   * and each next element check if same count has been met before. If so - number of elements
   * in between is <current index> - mag.get(count).
   * Catch - map (0,-1) to the map initially we have 0 count with -1 as index.
   * @param nums
   * @return
   */
  public int findMaxLength(int[] nums) {
    int count = 0;
    Map<Integer, Integer> map = new HashMap();
    map.put(0, -1);
    int max = 0;
    for (int i = 0; i < nums.length; i++) {
      count += (nums[i] == 1) ? 1 : -1;
      if (!map.containsKey(count)) {
        map.put(count, i);
      } else {
        max = Math.max(max, i - map.get(count));
      }
    }

    return max;
  }
}
