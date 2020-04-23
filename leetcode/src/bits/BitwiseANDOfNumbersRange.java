package bits;

/**
 * 201. Bitwise AND of Numbers Range
 * Medium
 *
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in
 * this range, inclusive.
 *
 * Example 1:
 *
 * Input: [5,7]
 * Output: 4
 * Example 2:
 *
 * Input: [0,1]
 * Output: 0
 */
public class BitwiseANDOfNumbersRange {
  /**
   * For example, for number 26 to 30
   * Their binary form are:
   * 11010
   * 11011
   * 11100　　
   * 11101　　
   * 11110
   *
   * Because we are trying to find bitwise AND, so if any bit there are at least one 0 and one 1,
   * it always 0. In this case, it is 11000.
   *
   * We're shifting n and m to the right until they became equal. Each shift means the answer
   * will be shifted same bits but to the left.
   * Because m is <= n we're shifting answer based on m
   * @param m
   * @param n
   * @return
   */
  public int rangeBitwiseAnd(int m, int n) {
    if (m == 0)
      return 0;

    int shifts = 1;

    while (m != n) {
      m>>=1;
      n>>=1;
      shifts<<=1;
    }
    return m * shifts;
  }
}
