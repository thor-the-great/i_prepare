package random_problems;

import java.util.HashSet;
import java.util.Set;

/**
 * 1100. Find K-Length Substrings With No Repeated Characters Medium
 *
 * Given a string S, return the number of substrings of length K with no repeated characters.
 *
 *
 *
 * Example 1:
 *
 * Input: S = "havefunonleetcode", K = 5 Output: 6 Explanation: There are 6 substrings they are :
 * 'havef','avefu','vefun','efuno','etcod','tcode'. Example 2:
 *
 * Input: S = "home", K = 5 Output: 0 Explanation: Notice K can be larger than the length of S. In
 * this case is not possible to find any substring.
 *
 *
 * Note:
 *
 * 1 <= S.length <= 10^4 All characters of S are lowercase English letters. 1 <= K <= 10^4
 */
public class KLengthSubstringNoRepeated {

  /**
   * Sliding window, use set cause we only interested in fact that we met this char before, not in
   * actual quantity
   */
  public int numKLenSubstrNoRepeats(String S, int K) {
    Set<Character> set = new HashSet();
    int res = 0;
    int l = 0;
    int N = S.length();
    for (int p = 0; p < N; p++) {
      char ch = S.charAt(p);
      while (set.contains(ch)) {
        set.remove(S.charAt(l++));
      }
      set.add(ch);
      if (p - l >= K - 1) {
        res++;
      }
    }
    return res;
  }
}
