package cracking.recursion_dp;

import java.util.HashMap;
import java.util.Map;

/**
 * How many ways of putting parentheses possible for the binary expression to achieve passed result
 *
 */
public class BooleanEvaluation {
    //use recursion with dp. evaluate expression for each part, then combine

    long evalExpr(String s, boolean result) {
        return evalExpr(s, result, new HashMap<>());
    }

    long evalExpr(String s, boolean result, Map<String, Long> cache) {
        if (s.length() == 0) return 0;
        if (s.length() == 1) return stringToBool(s) == result ? 1 : 0;

        if (cache.containsKey(s + result))
            return cache.get(s + result);

        long ways = 0;
        for (int i = 1; i < s.length(); i+=2) {
            char c = s.charAt(i);
            String left = s.substring(0, i);
            String right = s.substring(i + 1);

            long leftTrue = evalExpr(left, true, cache);
            long leftFalse = evalExpr(left, false, cache);
            long rightTrue = evalExpr(right, true, cache);
            long rightFalse = evalExpr(right, false, cache);

            long total = (leftTrue + leftFalse) * (rightTrue + rightFalse);

            long totalTrue = 0;
            if (c == '^')
                totalTrue = leftTrue*rightFalse + rightTrue*leftFalse;
            else if (c == '&')
                totalTrue = leftTrue*rightTrue;
            else if (c == '|')
                totalTrue = leftTrue*rightFalse + rightTrue*leftFalse + rightTrue*leftTrue;

            ways += result ? totalTrue : total - totalTrue;
        }
        cache.put(s + result, ways);
        return ways;
    }

    boolean stringToBool(String s) {
        return s.equals("1");
    }


    public static void main(String[] args) {
        BooleanEvaluation obj = new BooleanEvaluation();

        System.out.println(obj.evalExpr("1^0|0|1", false));

        System.out.println(obj.evalExpr("0&0&0&1^1|0", true));

        System.out.println(obj.evalExpr("0&0&0&1^1|0&0&0&1^1|0&0&0&1^1|0&0&0&1^1|0&0&0&1^1", true));
    }
}
