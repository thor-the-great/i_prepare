package bits;

/**
 * 476. Number Complement
 * Easy
 *
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary
 * representation.
 *
 *
 *
 * Example 1:
 *
 * Input: 5
 * Output: 2
 * Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
 *
 *
 * Example 2:
 *
 * Input: 1
 * Output: 0
 * Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 *
 *
 * Note:
 *
 * The given integer is guaranteed to fit within the range of a 32-bit signed integer.
 * You could assume no leading zero bit in the integer’s binary representation.
 * This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/
 */
public class NumberCompliment {

  /**
   * do it bit by bit - shifting number bits to the right, on every step keep mask bits shifting to the left
   * @param num
   * @return
   */
  public int findComplement(int num) {
    int res = 0;
    int mask = 1;
    while(num != 0) {
      if ((num&1) == 0)
        res |= mask;

      mask<<=1;
      num>>=1;
    }
    return res;
  }
}
