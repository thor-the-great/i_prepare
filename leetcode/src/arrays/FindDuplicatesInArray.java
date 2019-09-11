package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * 442. Find All Duplicates in an Array
 * Medium
 *
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and
 * others appear once.
 *
 * Find all the elements that appear twice in this array.
 *
 * Could you do it without extra space and in O(n) runtime?
 *
 * Example:
 * Input:
 * [4,3,2,7,8,2,3,1]
 *
 * Output:
 * [2,3]
 */
public class FindDuplicatesInArray {

  /**
   * We use indexes as additional storage. We iterate and mark elements as index that is a value of
   * current array element by put negative value. Next time we'll meet negative value it means we
   * met this elelemnt before so it's a duplicate
   * @param nums
   * @return
   */
  public List<Integer> findDuplicates(int[] nums) {
    List<Integer> res = new ArrayList();

    for (int i = 0; i < nums.length; i++) {
      int idx = Math.abs(nums[i]) - 1;
      if (nums[idx] < 0) {
        res.add(idx + 1);
      }
      nums[idx] = -nums[idx];
    }

    return res;
  }
}
