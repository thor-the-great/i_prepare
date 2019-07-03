/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package binary_search;

/**
 * 1064. Fixed Point
 * User Accepted: 479
 * User Tried: 479
 * Total Accepted: 489
 * Total Submissions: 536
 * Difficulty: Easy
 * Given an array A of distinct integers sorted in ascending order, return the smallest index i
 * that satisfies A[i] == i.  Return -1 if no such i exists.
 *
 *
 *
 * Example 1:
 *
 * Input: [-10,-5,0,3,7]
 * Output: 3
 * Explanation:
 * For the given array, A[0] = -10, A[1] = -5, A[2] = 0, A[3] = 3, thus the output is 3.
 * Example 2:
 *
 * Input: [0,2,5,8,17]
 * Output: 0
 * Explanation:
 * A[0] = 0, thus the output is 0.
 * Example 3:
 *
 * Input: [-10,-5,3,4,7,9]
 * Output: -1
 * Explanation:
 * There is no such i that A[i] = i, thus the output is -1.
 *
 *
 * Note:
 *
 * 1 <= A.length < 10^4
 * -10^9 <= A[i] <= 10^9
 */
public class FixedPoint {

  public int fixedPoint(int[] A) {
    int N = A.length;
    if (A[0] > 0 || A[N - 1] < N - 1)
      return -1;

    int l = 0, r = N - 1;
    while (l < r) {
      int m = (r + l) /2;
      if (A[m] == m)
        return m;
      else if (A[m] > m) {
        r = m;
      } else
        l = m + 1;
    }

    return -1;
  }
}
