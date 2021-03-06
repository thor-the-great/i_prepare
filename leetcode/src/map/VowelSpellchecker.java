package map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 966. Vowel Spellchecker
 * Medium
 *
 * Given a wordlist, we want to implement a spellchecker that converts a query word into a
 * correct word.
 *
 * For a given query word, the spell checker handles two categories of spelling mistakes:
 *
 * Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query
 * word is returned with the same case as the case in the wordlist.
 * Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
 * Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
 * Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
 * Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with
 * any vowel individually, it matches a word in the wordlist (case-insensitive), then the query
 * word is returned with the same case as the match in the wordlist.
 * Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
 * Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
 * Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
 * In addition, the spell checker operates under the following precedence rules:
 *
 * When the query exactly matches a word in the wordlist (case-sensitive), you should return the
 * same word back.
 * When the query matches a word up to capitlization, you should return the first such match in
 * the wordlist.
 * When the query matches a word up to vowel errors, you should return the first such match in
 * the wordlist.
 * If the query has no matches in the wordlist, you should return the empty string.
 * Given some queries, return a list of words answer, where answer[i] is the correct word for
 * query = queries[i].
 *
 *
 *
 * Example 1:
 *
 * Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare",
 * "HARE","Hear","hear","keti","keet","keto"]
 * Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
 *
 *
 * Note:
 *
 * 1 <= wordlist.length <= 5000
 * 1 <= queries.length <= 5000
 * 1 <= wordlist[i].length <= 7
 * 1 <= queries[i].length <= 7
 * All strings in wordlist and queries consist only of english letters.
 */
public class VowelSpellchecker {

  /**
   * We can put words to the form that we can check with hash map. For that keep 3 structures:
   * set of exact matches
   * map of word_to_lower_case - word
   * map of vowels_replaces(word_to_lower_case) - word
   *
   * Iterate over queries and check in this sequence:
   * exact match, to lower case match, devoweled of lowercased
   * if nothing matches - return ""
   * @param wordlist
   * @param queries
   * @return
   */
  public String[] spellchecker(String[] wordlist, String[] queries) {
    Set<String> perfectMatch = new HashSet();
    Map<String, String> decapMatch = new HashMap();
    Map<String, String> devowMatch = new HashMap();

    for (String word : wordlist) {
      perfectMatch.add(word);
      String wordLowerCase = word.toLowerCase();

      if (!decapMatch.containsKey(wordLowerCase))
        decapMatch.put(wordLowerCase, word);

      String devowStr = devowel(wordLowerCase);
      if (!devowMatch.containsKey(devowStr))
        devowMatch.put(devowStr, word);
    }

    String[] res = new String[queries.length];

    for (int i = 0; i < queries.length; i++) {
      String query = queries[i];

      if (perfectMatch.contains(query)) {
        res[i] = query;
        continue;
      }
      String lowerStr = query.toLowerCase();
      if (decapMatch.containsKey(lowerStr)) {
        res[i] = decapMatch.get(lowerStr);
        continue;
      }
      String devowelStr = devowel(lowerStr);
      if (devowMatch.containsKey(devowelStr)) {
        res[i] = devowMatch.get(devowelStr);
        continue;
      }
      res[i] = "";
    }

    return res;
  }

  String devowel(String str) {
    StringBuilder sb = new StringBuilder();
    for (char ch : str.toCharArray())
      sb.append(!isVowel(ch) ? ch : '*');

    return sb.toString();
  }

  boolean isVowel(char c) {
    return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
  }
}
