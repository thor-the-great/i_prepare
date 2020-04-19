package strings;

/**
 * 1415. The k-th Lexicographical String of All Happy Strings of Length n
 *
 * Difficulty: Medium
 *
 * A happy string is a string that:
 *
 * consists only of letters of the set ['a', 'b', 'c'].
 * s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
 * For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa",
 * "baa" and "ababbc" are not happy strings.
 *
 * Given two integers n and k, consider a list of all happy strings of length n sorted in
 * lexicographical order.
 *
 * Return the kth string of this list or return an empty string if there are less than k happy
 * strings of length n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1, k = 3
 * Output: "c"
 * Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string
 * is "c".
 * Example 2:
 *
 * Input: n = 1, k = 4
 * Output: ""
 * Explanation: There are only 3 happy strings of length 1.
 * Example 3:
 *
 * Input: n = 3, k = 9
 * Output: "cab"
 * Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb",
 * "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab"
 * Example 4:
 *
 * Input: n = 2, k = 7
 * Output: ""
 * Example 5:
 *
 * Input: n = 10, k = 100
 * Output: "abacbabacb"
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10
 * 1 <= k <= 100
 */
public class KthLexicographicalStringAllHappyStringsLengthN {

  /**
   * Look at the examples - first char can be anything out of 3, every next one can be one of 2
   * So overall it's 3 * (2^(n - 1)).
   * If start dividing k into  part we can build the result string by parts starting from left most
   *
   * O(n) - build string of size n
   * O(1) - space
   * @param n
   * @param k
   * @return
   */
  public String getHappyString(int n, int k) {
    int max = 1<<(n - 1);
    if (k > 3*max)
      return "";

    StringBuilder sb = new StringBuilder();
    char prev;
    if (k > 2*max ) {
      prev = 'c';
    } else if (k > max) {
      prev = 'b';
    } else {
      prev = 'a';
    }
    sb.append(prev);
    while (max > 1) {
      k -= max * ((k - 1) / max);
      max>>=1;
      char next;
      if ((k - 1)/max == 0) {
        next = (prev == 'a') ? 'b' : 'a';
      } else {
        next = (prev == 'c') ? 'b' :  'c';
      }
      sb.append(next);
      prev = next;
    }
    return sb.toString();
  }
}
