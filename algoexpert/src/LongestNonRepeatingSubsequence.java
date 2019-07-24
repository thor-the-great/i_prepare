/**
 * Longest Non-Repeating Substring New
 *     Strings Hash-Tables Arrays
 * Given a String input, find the length of the longest substring that is made up of
 * non-repeating characters. For ex, the longest substrings without repeated characters in
 * “BCEFGHBCFG” are “CEFGHB” and “EFGHBC”, with length = 6. In the case of "FFFFF", the longest
 * substring is "F" with a length of 1.
 * Example:
 *
 * Input : aaabbbabcde
 * Output: 5
 */
public class LongestNonRepeatingSubsequence {

  /**
   * Sliding window technique - count number of times we met each character. If number of times > 1
   * count the length of the sequence
   * @param input
   * @return
   */
  public static int longestNRSubstringLen(String input) {

    if (input == null || input.length() == 0)
      return 0;

    int[] counts = new int[128];
    int res = 1;
    int l = 0;
    counts[input.charAt(0)]++;

    for (int r = 1; r < input.length(); r++) {
      char ch = input.charAt(r);
      if (counts[ch] == 0) {
        counts[ch]++;
      } else {
        res = Math.max(res, r - l);
        while(input.charAt(l) != ch) {
          counts[input.charAt(l)]--;
          l++;
        }
        l++;
      }
    }
    return Math.max(res, input.length() - l);
  }
}
