package stack;

import java.util.Stack;

/**
 * 402. Remove K Digits
 * Medium
 *
 * Given a non-negative integer num represented as a string, remove k digits from the number so
 * that the new number is the smallest possible.
 *
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * Example 1:
 *
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the
 * smallest.
 * Example 2:
 *
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain
 * leading zeroes.
 * Example 3:
 *
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
public class RemoveKDigits {

  /**
   * Start from left to right, each time we meet digit that is smaller than the previous one -
   * pop out the previous one, for that put digits to stack. Each time when we pop digit decrement
   * k.
   * After pops stack will have digits but reversed. Need to reverse it back and remove 0-s. For
   * that remove from the end while it's still reversed, then reverse.
   *
   * O(N) space for stack, O(N) time
   * @param num
   * @param k
   * @return
   */
  public String removeKdigits(String num, int k) {
    //edge case
    if (num.length() <= k)
      return "0";

    Stack<Character> s = new Stack();
    int p = 0;

    while (p < num.length()) {
      char ch = num.charAt(p++);
      while(!s.isEmpty() && k > 0 && ch < s.peek()) {
        s.pop();
        --k;
      }
      s.push(ch);
    }

    //edge case
    while (k-- > 0) {
      s.pop();
    }

    StringBuilder sb = new StringBuilder();

    while(!s.isEmpty())
      sb.append(s.pop());

    while (sb.length() > 1 && sb.charAt(sb.length() - 1) == '0')
      sb.deleteCharAt(sb.length() - 1);

    return sb.reverse().toString();
  }
}
