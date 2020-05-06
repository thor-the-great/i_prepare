package arrays;

/**
 * Majority Element
 * Solution
 * Given an array of size n, find the majority element. The majority element is the element that
 * appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 */
public class MajorityElement {

  /**
   * Voting algoritm - pick the candidate and start counting, each match + 1, otherwise - 1.
   * when reached 0 - change the candidate
   *
   * @param nums
   * @return
   */
  public int majorityElement(int[] nums) {
    int count = 0;
    Integer candidate = null;
    for (int n : nums) {
      if (count == 0)
        candidate = n;

      if (n == candidate)
        ++count;
      else
        --count;
    }

    return candidate;
  }
}
