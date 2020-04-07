/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package grooking_coding_patterns.two_pointers;

import java.util.Arrays;

/**
 * Given an array of unsorted numbers and a target number, find a triplet in the array whose sum
 * is as close to the target number as possible, return the sum of the triplet. If there are more
 * than one such triplet, return the sum of the triplet with the smallest sum.
 *
 * Example 1:
 *
 * Input: [-2, 0, 1, 2], target=2
 * Output: 1
 * Explanation: The triplet [-2, 1, 2] has the closest sum to the target.
 * Example 2:
 *
 * Input: [-3, -1, 1, 2], target=1
 * Output: 0
 * Explanation: The triplet [-3, 1, 2] has the closest sum to the target.
 * Example 3:
 *
 * Input: [1, 0, 1, 1], target=100
 * Output: 3
 * Explanation: The triplet [1, 1, 1] has the closest sum to the target.
 */
public class TripletSumCloseToTarget {
  /**
   * Similar to triplet to zero sum problem. Sort elements so we can effectively search,
   * get i-th element, for it start nested loop with left and right pointers. Check sum of 3 elements,
   * keep the closest sum. If sum > target - move right pointer, otherwise - move left pointer.
   *
   * O(n^2) time - nlgn sorting and n^2 for nested loops
   * O(1) space
   * @param arr
   * @param targetSum
   * @return
   */
  public static int searchTriplet(int[] arr, int targetSum) {
    if (arr.length < 3)
      return -1;

    Arrays.sort(arr);
    int closest = arr[0] + arr[1] + arr[2];
    for (int i = 0; i < arr.length; i++) {
      int l = i + 1, r = arr.length - 1;
      while (l < r) {
        int sum = arr[l] + arr[r] + arr[i];
        if (sum == targetSum)
          return sum;

        if (Math.abs(targetSum - closest) > Math.abs(targetSum - sum)) {
          closest = sum;
        } else if (Math.abs(targetSum - closest) == Math.abs(targetSum - sum)) {
          closest = Math.min(closest, sum);
        }

        if (sum > targetSum) {
          --r;
        } else {
          ++l;
        }
      }
    }

    return closest;
  }
}
