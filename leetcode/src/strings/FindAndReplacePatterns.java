package strings;

import java.util.*;

/**
 * 890. Find and Replace Pattern
 * Medium
 *
 * You have a list of words and a pattern, and you want to know which words in words matches the
 * pattern.
 *
 * A word matches the pattern if there exists a permutation of letters p so that after replacing
 * every letter x in the pattern with p(x), we get the desired word.
 *
 * (Recall that a permutation of letters is a bijection from letters to letters: every letter
 * maps to another letter, and no two letters map to the same letter.)
 *
 * Return a list of the words in words that match the given pattern.
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["mee","aqq"]
 * Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
 * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
 * since a and b map to the same letter.
 *
 *
 * Note:
 *
 * 1 <= words.length <= 50
 * 1 <= pattern.length = words[i].length <= 20
 */
public class FindAndReplacePatterns {

  public List<String> findAndReplacePattern(String[] words, String pattern) {
    List<String> res = new ArrayList();
    int pLen = pattern.length();
    for (String word : words) {
      int[] w = new int[26];
      int[] p = new int[26];
      boolean matches = true;
      for (int i = 0; i < pLen; i++) {
        int wCh = word.charAt(i) - 'a';
        int pCh = pattern.charAt(i) - 'a';
        if (w[wCh] != p[pCh]) {
          matches = false;
          break;
        }
        w[wCh] = p[pCh] = i + 1;
      }
      if (matches)
        res.add(word);
    }
    return res;
  }

}
