package recursion;

/**
 * 680. Valid Palindrome II
 * Easy
 *
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make
 * it a palindrome.
 *
 * Example 1:
 * Input: "aba"
 * Output: True
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class ValidPalindrome2 {

  /**
   * Going from sides to the center. Keep flag that allows us to do delete. If characters are equal
   * - move pointers toward center. keep the flag. If not - we can either remove char on left or
   * right. For that we skip left or right pointer and set remove flag to false. If we not allowed
   * to remove chars - return false
   * @param s
   * @return
   */
  public boolean validPalindrome(String s) {
    return helper(s, 0, s.length() - 1, true);
  }

  private boolean helper(String s, int l, int r, boolean okRemove) {
    if (l >= r)
      return true;

    if (s.charAt(l) == s.charAt(r))
      return helper(s, l + 1, r - 1, okRemove);
    else
      return okRemove && (helper(s, l + 1, r, false) || helper(s, l, r - 1, false));
  }
}
