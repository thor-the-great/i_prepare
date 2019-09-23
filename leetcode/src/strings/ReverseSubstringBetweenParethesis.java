package strings;

/**
 * 1190. Reverse Substrings Between Each Pair of Parentheses
 * Medium
 *
 * Share
 * Given a string s that consists of lower case English letters and brackets.
 *
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 *
 * Your result should not contain any bracket.
 *
 *
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(abcd)"
 * Output: "dcba"
 * Example 2:
 *
 * Input: s = "(u(love)i)"
 * Output: "iloveu"
 * Example 3:
 *
 * Input: s = "(ed(et(oc))el)"
 * Output: "leetcode"
 * Example 4:
 *
 * Input: s = "a(bcdefghijkl(mno)p)q"
 * Output: "apmnolkjihgfedcbq"
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 2000
 * s only contains lower case English characters and parentheses.
 * It's guaranteed that all parentheses are balanced.
 */
public class ReverseSubstringBetweenParethesis {

  /**
   * Recursive solution, go till more internal substring, then reverse it and return part before and after it
   * plus this reversed piece. Continue doing this in recursion
   * @param s
   * @return
   */
  public String reverseParentheses(String s) {
    int N = s.length();
    if (N == 0)
      return s;
    int i = 0, start = 0;
    while ( i < N) {
      char ch = s.charAt(i);
      if (ch == '(') {
        start = i;
      }
      if (ch == ')') {
        return reverseParentheses(
            s.substring(0, start)
                + reverse(s.substring(start + 1, i))
                + s.substring(i + 1, N));
      }
      i++;
    }
    return s;
  }

  String reverse(String s) {
    char[] arr = s.toCharArray();
    int l =0, r = arr.length - 1;
    while (l < r) {
      char t = arr[l];
      arr[l] = arr[r];
      arr[r] = t;
      l++; r--;
    }
    return new String(arr);
  }
}
