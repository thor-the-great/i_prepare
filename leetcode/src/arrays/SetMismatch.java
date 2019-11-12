package arrays;

/**
 * 645. Set Mismatch
 * Easy
 *
 * The set S originally contains numbers from 1 to n. But unfortunately, due to the data error,
 * one of the numbers in the set got duplicated to another number in the set, which results in
 * repetition of one number and loss of another number.
 *
 * Given an array nums representing the data status of this set after the error. Your task is to
 * firstly find the number occurs twice and then find the number that is missing. Return them in
 * the form of an array.
 *
 * Example 1:
 * Input: nums = [1,2,2,4]
 * Output: [2,3]
 * Note:
 * The given array size will in the range [2, 10000].
 * The given array's numbers won't have any order.
 */
public class SetMismatch {

  /**
   * Use array's indexes to store the duplicate info. Set the element to -el for those that we've
   * seen before
   * @param nums
   * @return
   */
  public int[] findErrorNums(int[] nums) {
    int[] res = new int[2];
    //find the duplicate number
    for (int i = 0; i < nums.length; i++) {
      //this is the value of the array element. Use it as index to lookup an element to check
      //if we've seen it before
      int idx = Math.abs(nums[i]);
      //if nums[idx - 1] > 0 - it means we haven't seen it before, negate it so next time we'll know
      //use idx - 1 because indexes are 0 based
      if (nums[idx - 1] > 0 )
        nums[idx - 1] *= -1;
      //it means we have seen the idx element before, so it's a duplicate
      else
        res[0] = idx;
    }
    //now searching for the missing one. Missing one will be > 0 because every
    //element we've seen we change the value to negative
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > 0 ) {
        res[1] = i + 1;
        break;
      }
    }
    return res;
  }
}
