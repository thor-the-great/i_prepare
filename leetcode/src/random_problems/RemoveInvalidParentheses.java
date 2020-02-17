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
    Set<String> setRes;
    String s;
    public List<String> removeInvalidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            List<String> res = new ArrayList();
            res.add("");
            return res;
        }

        this.s = s;
        int c = 0, invalidOpen = 0, invalidClose = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') ++c;
            else if (ch == ')') {
                if (c == 0) ++invalidClose;
                else --c;
            }
        }

        c = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == ')') ++c;
            else if (ch == '(') {
                if (c == 0) ++invalidOpen;
                else --c;
            }
        }

        if (invalidOpen == 0 && invalidClose == 0) {
            List<String> res = new ArrayList();
            res.add(s);
            return res;
        }

        setRes = new HashSet();

        dfs(new StringBuilder(), 0, invalidOpen, invalidClose, 0, 0);

        List<String> res = new ArrayList(setRes);
        return res;
    }

    void dfs(StringBuilder cur, int idx, int open, int close, int openCount, int closeCount) {
        if (idx >= s.length()) {
            if (open == 0 && close == 0)
                setRes.add(cur.toString());
            return;
        }

        char ch = s.charAt(idx);

        //case 1 : remove char if possible - it has to be only paretheses
        if (ch == '(' && open > 0) {
            dfs(cur, idx + 1, open - 1, close, openCount, closeCount);
        } else if (ch == ')' && close > 0) {
            dfs(cur, idx + 1, open, close - 1, openCount, closeCount);
        }

        //case 2 : don't remove
        cur.append(ch);
        if (ch == ')') {
            if (closeCount < openCount) {
                dfs(cur, idx + 1, open, close, openCount, closeCount + 1);
            }
        } else if (ch == '(') {
            dfs(cur, idx + 1, open, close, openCount + 1, closeCount);
        } else  {
            dfs(cur, idx + 1, open, close, openCount, closeCount);
        }
        cur.setLength(cur.length() - 1);
    }

    public static void main(String[] args) {
        RemoveInvalidParentheses obj = new RemoveInvalidParentheses();
        //System.out.println(obj.removeInvalidParentheses("(()("));//1
        System.out.println(obj.removeInvalidParentheses("()())()"));//1
        System.out.println(obj.removeInvalidParentheses("(a)())()"));//1
        System.out.println(obj.removeInvalidParentheses(")("));//2
    }
}
