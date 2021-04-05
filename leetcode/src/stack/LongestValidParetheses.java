package stack;

import java.util.Stack;

/**
 * 32. Longest Valid Parentheses
 * Hard
 *
 * Given a string containing just the characters '(' and ')', find the length of the longest
 * valid (well-formed) parentheses substring.
 *
 * Example 1:
 *
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 *
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 *
 */
public class LongestValidParetheses {
    /**
     * Keep the index of previous unclosed pair of parethesis in stack.
     * Every opening one - we just add index to stack.
     * If this is closing one - pop element (close the pair) and then calculate
     * length of sequence as i - stack.peek. If stack is empty we push current index to the 
     * stack first, so in case of invalid pair length is 0.
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int res = 0;
        Stack<Integer> stack = new Stack();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.push(i);
            else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }
}
