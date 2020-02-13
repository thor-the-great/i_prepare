package strings;

import java.util.HashMap;
import java.util.Map;

/**
 * 166. Fraction to Recurring Decimal
 * Medium
 *
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * Example 1:
 *
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * Example 2:
 *
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * Example 3:
 *
 * Input: numerator = 2, denominator = 3
 * Output: "0.(6)"
 */
public class FractionToRecurringDecimal {

    /**
     * Idea for the decimal part = we take dec, *10 and divide by denominator, if the remainder
     * is 0 - we're done. Keep remainders that we had, if they start repeating - this is recurring part
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0)
            return "0";

        StringBuilder sb = new StringBuilder();
        if ((numerator < 0 && denominator > 0)
                || (numerator > 0 && denominator < 0))
            sb.append('-');

        long num = Math.abs(Long.valueOf(numerator));
        long den = Math.abs(Long.valueOf(denominator));

        long left = num % den;
        if (left == 0) {
            sb.append(Long.toString(num / den));
            return sb.toString();
        }

        sb.append(num / den).append('.');

        Map<Long, Integer> map = new HashMap();
        String decStr = "";
        while (left != 0) {
            if (map.containsKey(left)) {
                int pos = map.get(left);
                sb.append(decStr.substring(0, pos));
                sb.append('(');
                sb.append(decStr.substring(pos, decStr.length()));
                sb.append(')');
                return sb.toString();
            }
            map.put(left, decStr.length());
            left*=10;
            decStr += String.valueOf(left / den);
            left = left % den;
        }
        sb.append(decStr);
        return sb.toString();
    }
}
