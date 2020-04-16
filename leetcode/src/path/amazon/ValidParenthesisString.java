package path.amazon;

/**
 * 678. Valid Parenthesis String
 * Medium
 *
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this
 * string is valid. We define the validity of a string by these rules:
 *
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 * An empty string is also valid.
 * Example 1:
 * Input: "()"
 * Output: True
 * Example 2:
 * Input: "(*)"
 * Output: True
 * Example 3:
 * Input: "(*))"
 * Output: True
 * Note:
 * The string size will be in the range [1, 100].
 *
 */
public class ValidParenthesisString {

    public boolean checkValidString(String s) {
        return checkValidStringGreedy(s);
    }

    /**
     * Idea is - count max and min number of left parents we can have (for min we do +1 for every ( and -1 for anything
     * else, for max we do +1 for everything not ) and -1 for (. Max for every steps can't be < 0. In the end min must
     * be 0
     * Catch - for every step we need to keep l >= 0 - we can go < 0 if we count too many * as )
     * @param s
     * @return
     */
    public boolean checkValidStringGreedy(String s) {
        if (s == null) return false;
        if (s.isEmpty()) return true;

        int lowest = 0, highest = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(')
                lowest++;
            else
                lowest--;

            if (ch == ')')
                highest--;
            else
                highest++;

            if (highest < 0)
                return false;

            lowest = Math.max(0, lowest);
        }

        return lowest == 0;
    }

    /**
     * Idea - iterate over every character and replace '*' sequencialy by '(', ')' or ' ' and then try that new string.
     * if any of these strings are valid - return true;
     * @param s
     * @return
     */
    public boolean checkValidStringRecursive(String s) {
        StringBuilder sb = new StringBuilder(s);
        check(sb, 0);
        return res;
    }

    boolean res = false;
    char[] replacements = new char[]{'(', ')', ' '};

    void check(StringBuilder sb, int idx) {
        if (idx == sb.length()) {
            res = res | valid(sb);
            return;
        }

        char next = sb.charAt(idx);
        if (next == '*') {
            for (int j = 0; j < replacements.length; j++) {
                sb.setCharAt(idx, replacements[j]);
                check(sb, idx + 1);
                if (res)
                    return;
            }
            sb.setCharAt(idx, '*');
        }
        else
            check(sb, idx + 1);
    }

    boolean valid(StringBuilder sb) {
        int balanced = 0;
        for (int i = 0; i < sb.length(); i++) {
            char next = sb.charAt(i);
            if (next == '(')
                balanced++;
            else if (next == ')')
                balanced--;
            if (balanced < 0)
                return false;
        }
        return balanced == 0;
    }

    public static void main(String[] args) {

    }
}
