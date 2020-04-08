package grooking_coding_patterns.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Quadruple Sum to Target (medium) #
 * Given an array of unsorted numbers and a target number, find all unique quadruplets in it,
 * whose sum is equal to the target number.
 * <p>
 * Example 1:
 * <p>
 * Input: [4, 1, 2, -1, 1, -3], target=1
 * Output: [-3, -1, 1, 4], [-3, 1, 1, 2]
 * Explanation: Both the quadruplets add up to the target.
 * Example 2:
 * <p>
 * Input: [2, 0, -1, 1, -2, 2], target=2
 * Output: [-2, 0, 2, 2], [-1, 0, 1, 2]
 * Explanation: Both the quadruplets add up to the target.
 */
public class QuadrupleSumToTarget {

  /**
   * Sort array first, start first loop, we decrease problem to sum of triplet, then run i + 1 loop - this
   * decreases problem to sum of two. To avoid duplicates check i- 1 and i elements
   * <p>
   * O(n^3) time, O(1) space
   *
   * @param arr
   * @param target
   * @return
   */
  public static List<List<Integer>> searchQuadruplets(int[] arr, int target) {
    List<List<Integer>> quadruplets = new ArrayList<>();
    Arrays.sort(arr);
    for (int i = 0; i < arr.length - 3; i++) {
      if (i == 0 || arr[i - 1] != arr[i]) {
        for (int j = i + 1; j < arr.length; j++) {
          if (arr[j] != arr[j - 1]) {
            int newT = target - arr[i] - arr[j];
            int l = j + 1, r = arr.length - 1;
            while (l < r) {
              if (arr[l] + arr[r] == newT) {
                List<Integer> list = Arrays.asList(arr[i], arr[j], arr[l], arr[r]);
                quadruplets.add(list);
                ++l;
                --r;
              } else if (arr[l] + arr[r] > newT) {
                --r;
              } else
                ++l;
            }
          }
        }
      }
    }
    return quadruplets;
  }
}
