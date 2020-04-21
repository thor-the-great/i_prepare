package arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * 961. N-Repeated Element in Size 2N Array
 * Easy
 *
 * In a array A of size 2N, there are N+1 unique elements, and exactly one of these elements is
 * repeated N times.
 *
 * Return the element repeated N times.
 *
 * Example 1:
 *
 * Input: [1,2,3,3]
 * Output: 3
 * Example 2:
 *
 * Input: [2,1,2,5,3,2]
 * Output: 2
 * Example 3:
 *
 * Input: [5,1,5,2,5,3,5,4]
 * Output: 5
 *
 *
 * Note:
 *
 * 4 <= A.length <= 10000
 * 0 <= A[i] < 10000
 * A.length is even
 */
public class NRepeatedElementInSize2nArray {

  /**
   * Tricky logic here. Because all elements are unique if we take any two one will be the repeating
   * one. If we take another two - one from that pair will also be repeating one. So if we take
   * any 4 and check two elements will be repeating. This leads to solution - pick any 4 repeatedly
   * and check if there is a much of repeating elements.
   * @param A
   * @return
   */
  public int repeatedNTimes(int[] A) {
    for (int k = 1; k <=3; k++) {
      for (int i = 0; i < A.length - k; i++) {
        if (A[i] == A[i + k])
          return A[i];
      }
    }
    return -1;
  }

  /**
   * Similar idea but with set
   * @param A
   * @return
   */
  public int repeatedNTimesSet(int[] A) {
    Set<Integer> set = new HashSet();
    for (int i = 0; i < A.length; i++) {
      if (set.contains(A[i]))
        return A[i];
      set.add(A[i]);
      if (i >= 3)
        set.remove(A[i - 3]);
    }
    return -1;
  }
}
