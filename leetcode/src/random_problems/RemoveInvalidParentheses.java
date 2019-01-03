package random_problems;

import java.util.*;

/**
 * 301. Remove Invalid Parentheses
 * Hard
 *
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible
 * results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 *
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2:
 *
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * Example 3:
 *
 * Input: ")("
 * Output: [""]
 */
public class RemoveInvalidParentheses {
    Set<String> uniqueStrings;

    /**
     * Idea: - first count the number of misplaced parentheses (using easy stack based approach). The number of
     * parentheses we can remove is equals to that number exactly.
     *
     * Then we iterate and try every possible parenthesis + backtracking for invalid variants
     * catch: - need to track number of wrong parentheses and number of used pareths. In one case we add ")" (right) to
     * the expression only in case count of used right is < than count of used lefts
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        int N = s.length();
        List<String> res = new ArrayList<>();
        if (N == 0) {
            res.add("");
            return res;
        }
        uniqueStrings = new HashSet();
        //check number of invalid pars
        int l = 0, r = 0;
        Stack<Integer> stack = new Stack();
        for (char ch : s.toCharArray()) {
            int code = charToCode(ch);
            if (code == 0) continue;
            if (code > 0) {
                stack.push(code);
                continue;
            } else {
                if (stack.isEmpty()) {
                    r++;
                    continue;
                }
                int prev = stack.pop();
                if (prev != 1) {
                    r++;
                }
            }
        }
        l += stack.size();
        if (l == 0 && r == 0) {
            res.add(s);
            return res;
        }
        helper(s, 0,0, l, r, 0, new StringBuilder());
        res.addAll(uniqueStrings);
        return res;
    }

    void helper(String s, int leftCount, int rightCount, int left, int right, int idx, StringBuilder expr) {
        if (idx >= s.length() ) {
            if (left == 0 && right == 0) {
                uniqueStrings.add(expr.toString());
            }
            return;
        }

        char ch = s.charAt(idx);
        //try remove character if possible
        if (ch == '(' && left > 0) {
            helper(s, leftCount, rightCount, left - 1, right, idx + 1, expr);
        } else if (ch == ')' && right > 0) {
            helper(s, leftCount, rightCount, left, right - 1, idx + 1, expr);
        }
        expr.append(ch);
        //don't remove case ( means we need to add char to sb)
        if (ch != '(' && ch != ')') {
            helper(s, leftCount, rightCount, left, right, idx + 1, expr);
        } else if (ch == '(') {
            helper(s, leftCount + 1, rightCount, left, right, idx + 1, expr);
        } else if ( ch == ')' && rightCount < leftCount) {
            helper(s, leftCount, rightCount + 1, left, right, idx + 1, expr);
        }
        expr.deleteCharAt(expr.length() - 1);
    }

    int charToCode(char ch) {
        int res = 0;
        if (ch == '(')
            res = 1;
        else if (ch == ')')
            res = -1;
        return res;
    }

    public static void main(String[] args) {
        RemoveInvalidParentheses obj = new RemoveInvalidParentheses();
        //System.out.println(obj.removeInvalidParentheses("(()("));//1
        System.out.println(obj.removeInvalidParentheses("()())()"));//1
        System.out.println(obj.removeInvalidParentheses("(a)())()"));//1
        System.out.println(obj.removeInvalidParentheses(")("));//2
    }
}
