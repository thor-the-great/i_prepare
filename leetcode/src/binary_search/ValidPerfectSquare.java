package binary_search;

/**
 * 367. Valid Perfect Square
 * Easy
 *
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 *
 * Note: Do not use any built-in library function such as sqrt.
 *
 * Example 1:
 *
 * Input: 16
 * Output: true
 * Example 2:
 *
 * Input: 14
 * Output: false
 */
public class ValidPerfectSquare {

  /**
   * Do binary search, from 1 to num/2. Get middle elements, check it's product and go to left or right
   * part depending on result
   * @param num
   * @return
   */
  public boolean isPerfectSquare(int num) {
    if (num == 1)
      return true;

    int l = 1, r = num/2;

    while (l <= r) {
      int m = l + (r - l)/2;
      if ((long)m*m == num)
        return true;
      if ((long)m*m > num) {
        r = m - 1;
      } else {
        l = m + 1;
      }
    }

    return false;
  }
}
