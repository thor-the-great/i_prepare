/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package random_problems;

/**
 * 1189. Maximum Number of Balloons
 * Easy
 *
 * Given a string text, you want to use the characters of text to form as many instances of the
 * word "balloon" as possible.
 *
 * You can use each character in text at most once. Return the maximum number of instances that
 * can be formed.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: text = "nlaebolko"
 * Output: 1
 * Example 2:
 *
 *
 *
 * Input: text = "loonbalxballpoon"
 * Output: 2
 * Example 3:
 *
 * Input: text = "leetcode"
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= text.length <= 10^4
 * text consists of lower case English letters only.
 */
public class MaxNumberOfBalloons {

  /**
   *
   * @param text
   * @return
   */
  public int maxNumberOfBalloons(String text) {
    if (text == null || text.length() < 7)
      return 0;
    int[] c = new int[26];
    for (char ch : text.toCharArray())
      c[ch - 'a']++;
    int res = Integer.MAX_VALUE;
    res = Math.min(res, c['b' - 'a']);
    res = Math.min(res, c['a' - 'a']);
    res = Math.min(res, c['l' - 'a'] / 2);
    res = Math.min(res, c['o' - 'a'] / 2);
    res = Math.min(res, c['n' - 'a']);
    return res;
  }
}
