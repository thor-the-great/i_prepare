package arrays;

/**
 * 1437. Check If All 1's Are at Least Length K Places Away
 * Medium
 *
 * Given an array nums of 0s and 1s and an integer k, return True if all 1's are at least k
 * places away from each other, otherwise return False.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: nums = [1,0,0,0,1,0,0,1], k = 2
 * Output: true
 * Explanation: Each of the 1s are at least 2 places away from each other.
 * Example 2:
 *
 *
 *
 * Input: nums = [1,0,0,1,0,1], k = 2
 * Output: false
 * Explanation: The second 1 and third 1 are only one apart from each other.
 * Example 3:
 *
 * Input: nums = [1,1,1,1,1], k = 0
 * Output: true
 * Example 4:
 *
 * Input: nums = [0,1,0,1], k = 1
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 0 <= k <= nums.length
 * nums[i] is 0 or 1
 */
public class CheckIfAll1sLeastLengthKPlacesAway {

  /**
   * Greedy approach, keep position of the last seen 1, when see next one - compare the distance
   * @param nums
   * @param k
   * @return
   */
  public boolean kLengthApart(int[] nums, int k) {
    int pos = 0, N = nums.length;
    //find first 1 in the array
    for (; pos < N && nums[pos] == 0; pos++){}
    //if no 1s in array exit
    if (pos == N)
      return true;
    //start iterating over rest of the elements
    int dist = 0;
    for (pos++; pos < N; pos++) {
      //if met next 1 - check the distance from the previous one
      if (nums[pos] == 1) {
        if (dist < k)
          return false;
        dist = 0;
      } else {
        //if it's 0 - increment the distance
        ++dist;
      }
    }
    return true;
  }
}
