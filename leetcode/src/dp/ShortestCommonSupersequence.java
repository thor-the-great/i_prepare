package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 1092. Shortest Common Supersequence
 * Hard
 *
 * Given two strings str1 and str2, return the shortest string that has both str1 and str2 as
 * subsequences.  If multiple answers exist, you may return any of them.
 *
 * (A string S is a subsequence of string T if deleting some number of characters from T
 * (possibly 0, and the characters are chosen anywhere from T) results in the string S.)
 *
 *
 *
 * Example 1:
 *
 * Input: str1 = "abac", str2 = "cab"
 * Output: "cabac"
 * Explanation:
 * str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
 * str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
 * The answer provided is the shortest such string that satisfies these properties.
 *
 *
 * Note:
 *
 * 1 <= str1.length, str2.length <= 1000
 * str1 and str2 consist of lowercase English letters.
 */
public class ShortestCommonSupersequence {

  /**
   * Doing lowest common subsequence but keeping the string as we go. Finally in dp array at
   * dp[str1.len][str2.len] will be the common string itself
   *
   * Then some chars from str1 and/or str2 will be not in that sequence. To find those start from
   * left to right, add those are before the last char in common to the string builder. Those that
   * left (from both strings) can be added directly at the end.
   * @param str1
   * @param str2
   * @return
   */
  public String shortestCommonSupersequence(String str1, String str2) {
    //get the lcs
    List<Character>[][] dp = new ArrayList[str1.length() + 1][str2.length() + 1];
    for (int i = 0; i <= str1.length(); i++) {
      dp[i][0] = new ArrayList();
    }
    for (int i = 0; i <= str2.length(); i++) {
      dp[0][i] = new ArrayList();
    }

    for (int i1 = 1; i1 <= str1.length(); i1++) {
      for (int i2 = 1; i2 <= str2.length(); i2++) {
        if (str1.charAt(i1 - 1) == str2.charAt(i2 - 1)) {
          dp[i1][i2] = new ArrayList(dp[i1 - 1][i2 - 1]);
          dp[i1][i2].add(str1.charAt(i1 - 1));
        } else {
          dp[i1][i2] = (dp[i1 - 1][i2].size() > dp[i1][i2 - 1].size())
              ? dp[i1 - 1][i2]
              : dp[i1][i2 - 1];
        }
      }
    }

    //having lcs add all chars that are different before the last one in lcs
    StringBuilder sb = new StringBuilder();
    int i1 = 0, i2 = 0;
    for (char ch : dp[str1.length()][str2.length()]) {
      while (str1.charAt(i1) != ch) {
        sb.append(str1.charAt(i1));
        i1++;
      }
      while (str2.charAt(i2) != ch) {
        sb.append(str2.charAt(i2));
        i2++;
      }

      sb.append(ch);
      ++i1; ++i2;
    }
    //add those that are after the last ones
    if (i1 < str1.length()) sb.append(str1.substring(i1));
    if (i2 < str2.length()) sb.append(str2.substring(i2));

    return sb.toString();
  }
}
