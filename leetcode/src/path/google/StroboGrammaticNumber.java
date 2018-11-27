package path.google;

import java.util.HashSet;
import java.util.Set;

/**
 * 246. Strobogrammatic Number
 *
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 *
 * Example 1:
 *
 * Input:  "69"
 * Output: true
 * Example 2:
 *
 * Input:  "88"
 * Output: true
 * Example 3:
 *
 * Input:  "962"
 * Output: false
 */
public class StroboGrammaticNumber {
    Set<Character> symm1;

    public boolean isStrobogrammatic(String num) {
        symm1 = new HashSet();
        symm1.add('8');
        symm1.add('0');
        symm1.add('1');

        if (num.isEmpty())
            return false;
        int i = num.length() / 2;
        if (num.length() % 2 == 1) {
            if (!symm1.contains(num.charAt(i)))
                return false;
            return checkStrobo(num, i - 1, i + 1);
        }
        else {
            return checkStrobo(num, i - 1, i);
        }
    }

    boolean checkStrobo(String num, int l, int r) {
        while(l >= 0) {
            char lChar = num.charAt(l);
            char rChar = num.charAt(r);
            if (!(lChar == rChar && symm1.contains(lChar))
                    && !((lChar == '6' && rChar == '9') ||  (lChar == '9' && rChar == '6'))) {
                return false;
            }
            l--;
            r++;
        }
        return true;
    }
}
