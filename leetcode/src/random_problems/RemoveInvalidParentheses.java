package random_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {
        int N = s.length();
        List<String> res = new ArrayList<>();
        if (N == 0) {
            res.add("");
            return res;
        }
        //check number of invalid pars
        int c = 0;
        Stack<Integer> stack = new Stack();
        for (char ch : s.toCharArray()) {
            int code = charToCode(ch);
            if (code == 0) continue;
            if (code > 0) {
                stack.push(code);
                continue;
            } else {
                if (stack.isEmpty()) {
                    c++;
                    continue;
                }
                int prev = stack.pop();
                if (prev != 1) {
                    c++;
                }
            }
        }
        c += stack.size();
        System.out.println("count of invalid " + c);
        return res;
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
        System.out.println(obj.removeInvalidParentheses("()())()"));//1
        System.out.println(obj.removeInvalidParentheses("(a)())()"));//1
        System.out.println(obj.removeInvalidParentheses(")("));//2
    }
}
