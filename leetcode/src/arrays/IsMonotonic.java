
package arrays;

/**
 * 896. Monotonic Array
 * Easy
 *
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 *
 * An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone
 * decreasing if for all i <= j, A[i] >= A[j].
 *
 * Return true if and only if the given array A is monotonic.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,2,3]
 * Output: true
 * Example 2:
 *
 * Input: [6,5,4,4]
 * Output: true
 * Example 3:
 *
 * Input: [1,3,2]
 * Output: false
 * Example 4:
 *
 * Input: [1,2,4,5]
 * Output: true
 * Example 5:
 *
 * Input: [1,1,1]
 * Output: true
 *
 *
 * Note:
 *
 * 1 <= A.length <= 50000
 * -100000 <= A[i] <= 100000
 */
public class IsMonotonic {


  public boolean isMonotonic(int[] A) {
    int N = A.length;
    if (N < 2)
      return true;
    boolean inc = A[0] < A[1];
    boolean dec = A[0] > A[1];
    for (int i = 1; i < N - 1; i++) {
      if (A[i] < A[i + 1] && !inc)
        inc = true;
      else if (A[i] > A[i + 1] && !dec)
        dec = true;

      if (inc && dec)
        return false;
    }

    return true;
  }
}
