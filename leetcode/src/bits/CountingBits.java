package bits;

/**
 * 338. Counting Bits
 * Medium
 *
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num
 * calculate the number of 1's in their binary representation and return them as an array.
 *
 * Example 1:
 *
 * Input: 2
 * Output: [0,1,1]
 * Example 2:
 *
 * Input: 5
 * Output: [0,1,1,2,1,2]
 * Follow up:
 *
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do
 * it in linear time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in
 * c++ or in any other language.
 */
public class CountingBits {
  /**
   * There is a patterns for number of bits:
   * 0  1  [2  3] [4  5  6  7]  [8  9  10 11 12 13 14 15] [16, 17, 18, 19, 20, 21, 22, 23, 24,
   * 25, 26
   * 0, 1, [1, 2] [1, 2, 2, 3]  [1, 2, 2, 3, 2,  3, 3, 4] [1
   * if divide into groups from 2^n to 2^(n+1) then first half of the group is the same as previous
   * group and the next half is the same numbers but +1 each
   * this leads to a solution. know the length of the group by *2. Then copy values from the
   * previous group by p - len_of_group, then do the same copy again but + 1.
   * O(n) time, O(1) space
   * @param num
   * @return
   */
  public int[] countBits(int num) {
    int[] res = new int[num + 1];
    if (num > 0) res[1] = 1;
    int p = 2, len = 1;
    while (p <= num) {
      //first copy the previous part
      int lim = p + len;
      for (; p < Math.min(res.length, lim); p++) {
        res[p] = res[p - len];
      }
      //then copy it again and do +1
      lim = p + len;
      for (; p < Math.min(res.length, lim); p++) {
        res[p] = res[p - len] + 1;
      }
      len*=2;
    }
    return res;
  }
}
