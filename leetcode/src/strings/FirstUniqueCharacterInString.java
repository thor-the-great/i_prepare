package strings;

/**
 * 387. First Unique Character in a String
 * Easy
 *
 * Given a string, find the first non-repeating character in it and return it's index. If it
 * doesn't exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 * Note: You may assume the string contain only lowercase letters.
 */
public class FirstUniqueCharacterInString {

  /**
   * Count all chars first, then on second pass check freq of the char and first with freq == 1
   * is the answer
   * @param s
   * @return
   */
  public int firstUniqChar(String s) {
    int[] counts = new int[26];
    for (char ch : s.toCharArray()) {
      ++counts[ch - 'a'];
    }

    for (int i = 0; i < s.length(); i++) {
      if (counts[s.charAt(i) - 'a'] == 1)
        return i;
    }
    return -1;
  }
}
