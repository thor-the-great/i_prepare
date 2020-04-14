package strings;

/**
 * 1405. Longest Happy String
 * Medium
 *
 * A string is called happy if it does not have any of the strings 'aaa', 'bbb' or 'ccc'
 * as a substring.
 *
 * Given three integers a, b and c, return any string s, which satisfies following conditions:
 *
 * s is happy and longest possible.
 * s contains at most a occurrences of the letter 'a', at most b occurrences of the
 * letter 'b' and at most c occurrences of the letter 'c'.
 * s will only contain 'a', 'b' and 'c' letters.
 * If there is no such string s return the empty string "".
 *
 *
 *
 * Example 1:
 *
 * Input: a = 1, b = 1, c = 7
 * Output: "ccaccbcc"
 * Explanation: "ccbccacc" would also be a correct answer.
 * Example 2:
 *
 * Input: a = 2, b = 2, c = 1
 * Output: "aabbc"
 * Example 3:
 *
 * Input: a = 7, b = 1, c = 0
 * Output: "aabaa"
 * Explanation: It's the only correct answer in this case.
 *
 *
 * Constraints:
 *
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 */
public class LongestHappyString {

  /**
   * Keep count of every character. Iterate from 0-th index, keep searching for the
   * max qty, also keep track which char we used on 2 previous steps
   * @param a
   * @param b
   * @param c
   * @return
   */
  public String longestDiverseString(int a, int b, int c) {
    int[] counts = new int[] {a, b, c};
    StringBuilder sb = new StringBuilder();
    //init chars for two previous steps
    int prev = -1, prevPrev = -1;
    while (true) {
      int max = 0, idx = -1;
      //searching for char with max qty left and one that haven't used in two last steps
      for (int i = 0; i < 3; i++) {
        if (counts[i] > max && !(prev == i && prevPrev == i) ) {
          max = counts[i]; idx = i;
        }
      }
      //if max left is 0 - we can't add chars anymore, exit
      if (max == 0) break;
      //append chars, set previous and one before previous chars
      sb.append((char)('a' + idx));
      --counts[idx];
      prevPrev = prev; prev = idx;
    }
    return sb.toString();
  }
}
