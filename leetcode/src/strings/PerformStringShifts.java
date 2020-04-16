package strings;
/**
 * Perform String Shifts
 * Solution
 * You are given a string s containing lowercase English letters, and a matrix shift, where
 * shift[i] = [direction, amount]:
 *
 * direction can be 0 (for left shift) or 1 (for right shift).
 * amount is the amount by which string s is to be shifted.
 * A left shift by 1 means remove the first character of s and append it to the end.
 * Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
 * Return the final string after all operations.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", shift = [[0,1],[1,2]]
 * Output: "cab"
 * Explanation:
 * [0,1] means shift to left by 1. "abc" -> "bca"
 * [1,2] means shift to right by 2. "bca" -> "cab"
 * Example 2:
 *
 * Input: s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]]
 * Output: "efgabcd"
 * Explanation:
 * [1,1] means shift to right by 1. "abcdefg" -> "gabcdef"
 * [1,1] means shift to right by 1. "gabcdef" -> "fgabcde"
 * [0,2] means shift to left by 2. "fgabcde" -> "abcdefg"
 * [1,3] means shift to right by 3. "abcdefg" -> "efgabcd"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s only contains lower case English letters.
 * 1 <= shift.length <= 100
 * shift[i].length == 2
 * 0 <= shift[i][0] <= 1
 * 0 <= shift[i][1] <= 100
 */
public class PerformStringShifts {

  /**
   * If we shift right and then left then result string will combine the shift as we did the
   * shift to right - left positions. This leads to an algorithm:
   * start summing up shifts, right one are positive, lefts are negative.
   * At the end the resulting number is final number of shifts we need to perform. Do it modulo
   * str_len. If it's negative we can do N - shifts - same result.
   *
   * O(len(shifts)) + O(len(s)) time
   * O(len(s)) space for string builder
   * @param s
   * @param shift
   * @return
   */
  public String stringShift(String s, int[][] shift) {
    if (s == null || s.isEmpty())
      return s;

    int shifts = 0, N = s.length();
    for (int[] oneShift : shift) {
      shifts += (oneShift[0] == 1) ? oneShift[1] : -oneShift[1];
    }

    if (shifts % N == 0) return s;
    shifts%=N;

    if (shifts < 0)
      shifts += N;

    StringBuilder sb = new StringBuilder();

    for (int i = N - shifts; i < N; i++)
      sb.append(s.charAt(i));

    for (int i = 0; i < N - shifts; i++)
      sb.append(s.charAt(i));

    return sb.toString();
  }
}
