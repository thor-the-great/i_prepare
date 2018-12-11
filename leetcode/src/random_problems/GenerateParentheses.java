package random_problems;

import java.util.LinkedList;
import java.util.List;

/**
 * 22. Generate Parentheses
 * Medium
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 */
public class GenerateParentheses {

    List<String> res;
    int n;

    /**
     * Idea - run on every combination and count every open and close paren.
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        res = new LinkedList();
        this.n = n;
        helper("", 0, 0);
        return res;
    }

    void helper(String s, int open, int close) {
        if (s.length() == 2 * n) {
            res.add(s);
            return;
        }

        if (open < n)
            helper(s + "(", open + 1, close);
        if (close < open)
            helper(s + ")", open, close + 1);
    }

}
