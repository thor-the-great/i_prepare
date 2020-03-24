package arrays;

/**
 * 717. 1-bit and 2-bit Characters
 * Easy
 *
 * We have two special characters. The first character can be represented by one bit 0. The
 * second character can be represented by two bits (10 or 11).
 *
 * Now given a string represented by several bits. Return whether the last character must be a
 * one-bit character or not. The given string will always end with a zero.
 *
 * Example 1:
 * Input:
 * bits = [1, 0, 0]
 * Output: True
 * Explanation:
 * The only way to decode it is two-bit character and one-bit character. So the last character is
 * one-bit character.
 * Example 2:
 * Input:
 * bits = [1, 1, 1, 0]
 * Output: False
 * Explanation:
 * The only way to decode it is two-bit character and two-bit character. So the last character is
 * NOT one-bit character.
 * Note:
 *
 * 1 <= len(bits) <= 1000.
 * bits[i] is always 0 or 1.
 */
public class IsOneBitCharacter {

  /**
   * Check all bit from the start
   * @param bits
   * @return
   */
  public boolean isOneBitCharacter(int[] bits) {
    if (bits == null || bits.length < 1)
      return false;

    int cur = -1;
    for (int i = 0; i < bits.length; i++) {
      int nextBit = bits[i];
      if (cur == -1 || cur == 0 || cur == 10 || cur == 11) {
        cur = nextBit;
      } else {
        cur = cur*10 + nextBit;
      }
    }
    return cur == 0;
  }
}
