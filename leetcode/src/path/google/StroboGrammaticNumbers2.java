package path.google;

import java.util.LinkedList;
import java.util.List;

/**
 * Strobogrammatic Number II
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Find all strobogrammatic numbers that are of length = n.
 *
 * Example:
 *
 * Input:  n = 2
 * Output: ["11","69","88","96"]
 *
 */
public class StroboGrammaticNumbers2 {
    int N;

    public List<String> findStrobogrammatic(int n) {
        N = n;
        return helper(n);
    }

    List<String> helper(int n) {
        List<String> res = new LinkedList();
        if (n == 0) {
            res.add("");
            return res;
        }
        if ( n == 1) {
            res.add("1");
            res.add("0");
            res.add("8");
            return res;
        }

        List<String> nestedList = helper(n - 2);
        StringBuilder sb = new StringBuilder();
        for (String nested : nestedList) {
            sb.setLength(0);

            sb.append('1').append(nested).append('1');
            res.add(sb.toString());

            sb.setCharAt(0, '8');
            sb.setCharAt(sb.length() - 1, '8');
            res.add(sb.toString());

            sb.setCharAt(0, '6');
            sb.setCharAt(sb.length() - 1, '9');
            res.add(sb.toString());

            sb.setCharAt(0, '9');
            sb.setCharAt(sb.length() - 1, '6');
            res.add(sb.toString());
            if (n != N) {
                sb.setCharAt(0, '0');
                sb.setCharAt(sb.length() - 1, '0');
                res.add(sb.toString());
            }
        }
        return res;
    }
}
