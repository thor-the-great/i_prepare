package strings;

import java.util.Arrays;

/**
 * 451. Sort Characters By Frequency
 * Medium
 *
 * Given a string, sort it in decreasing order based on the frequency of characters.
 *
 * Example 1:
 *
 * Input:
 * "tree"
 *
 * Output:
 * "eert"
 *
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * Example 2:
 *
 * Input:
 * "cccaaa"
 *
 * Output:
 * "cccaaa"
 *
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * Example 3:
 *
 * Input:
 * "Aabb"
 *
 * Output:
 * "bbAa"
 *
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 */
public class SortCharactersByFrequency {

  /**
   * Count frequencies of every char, then sort and put to stringbuilder
   * @param s
   * @return
   */
  public String frequencySort(String s) {
    if (s == null || s.isEmpty())
      return s;

    StringBuilder sb = new StringBuilder();
    int[][] counts = new int[128][2];
    for (char ch : s.toCharArray()) {
      counts[ch][0] = ch;
      ++counts[ch][1];
    }

    Arrays.sort(counts,
            (a1, a2) -> (a1[1] != a2[1] ? a2[1] - a1[1] : a1[0] - a2[0]));
    for (int[] c : counts) {
      if (c[1] == 0)
        break;
      char nextChar = (char)c[0];
      for (int i = 0; i < c[1]; i++) {
        sb.append(nextChar);
      }
    }
    return sb.toString();
  }
}
