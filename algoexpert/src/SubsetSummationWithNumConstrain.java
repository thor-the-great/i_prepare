/**
 * Subset Summation with Number Constraint New
 *     Recursion Arrays Numbers
 *
 * Given an array of integers and a target integer, write a method groupSumWithNum to determine
 * if it is possible to choose a group of integers from the array such that the group sums to the
 * given target. An additional constraint is that the summation must include the integer
 * must_have if it is present in the array.
 *
 * Examples:
 * groupSumWithNum({1,2,3,6,5},5,10) ==> true
 *
 * groupSumWithNum({1,2,3,6,5},3,7) ==> false
 */
public class SubsetSummationWithNumConstrain {

  /**
   * Do recursive calls, on each step either add or skip current element.
   * for the must_have number we always include it.
   * 2^n time.
   * @param arr
   * @param must_have
   * @param target
   * @return
   */
  public static boolean groupSumWithNum(int[] arr, int must_have, int target) {
    return helper(arr, 0, must_have, target);

  }

  static boolean helper(int[] arr, int idx, int must_have, int target) {
    if (idx >= arr.length )
      return target == 0;

    if ( must_have == arr[idx]) {
      return helper(arr, idx + 1, must_have, target - must_have);
    }

    return helper(arr, idx + 1, must_have, target)
        || helper(arr, idx + 1, must_have, target - arr[idx]);
  }
}
