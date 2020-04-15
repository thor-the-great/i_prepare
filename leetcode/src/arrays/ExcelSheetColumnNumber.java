package arrays;

/**
 * 171. Excel Sheet Column Number
 * Easy
 *
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 *
 * For example:
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 * Example 1:
 *
 * Input: "A"
 * Output: 1
 * Example 2:
 *
 * Input: "AB"
 * Output: 28
 * Example 3:
 *
 * Input: "ZY"
 * Output: 701
 */
public class ExcelSheetColumnNumber {

  /**
   * Starting from the right to left parsing chars and threat it as a 26-th base number
   * @param s
   * @return
   */
  public int titleToNumber(String s) {
    int res = 0;
    int power = 1;
    for (int i = s.length() - 1; i >=0; i--) {
      int dig = (s.charAt(i) - 'A') + 1;
      res+= (dig*power);
      power*=26;
    }
    return res;
  }
}
