package stack;

import java.util.Stack;

/**
 * 224. Basic Calculator
 * Hard
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign
 * -, non-negative integers and empty spaces .
 *
 * Example 1:
 *
 * Input: "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class BasicCalculator {

  /**
   * Use int to accumulate result of the one pair of (), then when ")" met - push it to the stack,
   * continue do it for every new nested pair of (). The sign can be represented as 1/-1, so stack
   * of ints are ok. Because we are parsing num from the left bu digits start from the right - do
   * res = 10*res + cur to get the real num
   * @param s
   * @return
   */
  public int calculate(String s) {
    if (s == null || s.length() < 1)
      return 0;

    int res = 0, cur = 0, sign = 1;
    Stack<Integer> stack = new Stack();
    for (char ch : s.toCharArray()){
      if (ch >= '0' && ch <= '9') {
        cur = cur*10 + (ch - '0');
      } else if (ch =='+' || ch =='-') {
        res += sign*cur;
        sign = ch == '+' ? 1 : -1;
        cur = 0;
      } else if (ch == '(') {
        stack.push(res);
        stack.push(sign);
        res = 0;
        sign = 1;
      } else if (ch == ')') {
        res += sign*cur;
        int prevSign = stack.pop();
        res *= prevSign;
        int prevRes = stack.pop();
        res += prevRes;
        cur = 0;
      }
    }
    return res + (sign*cur);
  }
}
