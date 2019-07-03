package random_problems;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 227. Basic Calculator II Medium
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces
 * . The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: "3+2*2" Output: 7 Example 2:
 *
 * Input: " 3/2 " Output: 1 Example 3:
 *
 * Input: " 3+5 / 2 " Output: 5
 *
 * Input: " 3/2+5-5/2" Output: 1 + 5 - 2 = 4
 *
 * Input: " 3/2+5-5/2" Output: 1 + 5 - 2 = 4
 *
 * You may assume that the given expression is always valid. Do not use the eval built-in library
 * function.
 */
public class BasicCalculator2 {

  /**
   * Idea - we count as we go over the string. essentially all operations can be done via add. Initially
   * we can do it via Stack, accumulate all operands there. For / and * we need a previous operand so
   * pop() it from the Stack.
   * Eventually we don't need a Stack for this, we only read one step back state. We can use a variable
   * for this, kind of memoization technique.
   * @param s
   * @return
   */
  public int calculate(String s) {
    int N = s.length();
    char sign = '+';
    int pre = 0;
    int res = 0;
    int num = 0;
    for (int i = 0; i < N; i++) {
      char ch = s.charAt(i);
      boolean isDigit = Character.isDigit(ch);
      if (isDigit) {
        num = num * 10 + (ch - '0');
      }

      if ((!isDigit && ch != ' ') || i == N - 1) {
        switch (sign) {
          case '+': {
            res += pre;
            pre = num;
            break;
          }
          case '-': {
            res += pre;
            pre = -num;
            break;
          }
          case '/': {
            pre = pre / num;
            break;
          }
          case '*': {
            pre = pre * num;
            break;
          }
        }
        num = 0;
        sign = ch;
      }
    }
    return pre + res;
  }

  public static void main(String[] args) {
    BasicCalculator2 obj = new BasicCalculator2();
    assertThat(obj.calculate("3+2*2")).isEqualTo(7);
    assertThat(obj.calculate(" 3/2 ")).isEqualTo(1);
    assertThat(obj.calculate(" 3+5 / 2 ")).isEqualTo(5);
  }
}
