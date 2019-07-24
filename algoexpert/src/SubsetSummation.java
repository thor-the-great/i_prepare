/**
 * Subset Summation New
 *     Recursion Arrays
 * Given an array of integers and a target number, determine if it is possible to choose a group
 * of integers from the array, such that the numbers in the group sum to the given target.
 *
 * Examples:
 * groupSum({1,2,3,6,5},10) ==> true
 *
 * groupSum({1,2,3,6,5},18) ==> false
 */
public class SubsetSummation {

  /**
   * DP approach - build the matrix of target x N, where each cell is a boolean. If it's true for [i, j]
   * it's possible to combine such combination of j elements that summarizes to the i.
   * Fill the 0 column and row based on a logic, then start sweeping the matrix from left to right,
   * top to bottom
   * @param arr
   * @param target
   * @return
   */
  public static boolean groupSumDP(int[] arr, int target) {
    int N = arr.length;
    boolean[][] dp = new boolean[target + 1][N + 1];
    //for anything that sums to 0 we are ok - just exclude everything
    for (int i = 0; i <= N; i++)
      dp[0][i] = true;
    //if we take everything except for 0 - we can't have it from any number of elements
    for (int i = 1; i <= target; i++)
      dp[i][0] = false;
    //do the main loop
    for (int i = 1; i <= target; i++) {
      for (int j = 1; j <= N; j++)  {
        //assume result for the j - 1 elements (we can just skip the j-th element
        dp[i][j] = dp[i][j - 1];
        //if j-th element is smaler that i - potentialy we can change the value of [i,j]
        if (i >= arr[j - 1]) {
          dp[i][j] = dp[i][j] || dp[i - arr[j - 1]][j - 1];
        }
      }
    }

    return dp[target][N];
  }

  /**
   * Idea - go by the array elements, on every steps do two steps -
   * include this element and do target = target - element
   * exclude this element - skip to the next one and keep target the same.
   * Complexity is 2^n (
   * @param arr
   * @param target
   * @return
   */
  public static boolean groupSumExp(int[] arr, int target) {
    return helper(arr, 0,target);
  }

  static boolean helper(int[] arr, int idx, int target) {
    if (idx >= arr.length)
      return false;
    if (target - arr[idx] == 0)
      return true;
    return helper(arr, idx + 1, target) || helper(arr, idx + 1, target - arr[idx]);
  }
}
