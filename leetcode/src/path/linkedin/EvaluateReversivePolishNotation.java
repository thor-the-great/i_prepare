package path.linkedin;

import java.util.Stack;

public class EvaluateReversivePolishNotation {

    public int evalRPN(String[] tokens) {
        int N = tokens.length;
        Stack<Integer> s = new Stack();
        String nextElement = "";
        for (int i = 0; i < N; i++) {
            nextElement = tokens[i];
            switch (nextElement) {
                case "+" :
                    s.push(s.pop() + s.pop());
                    break;
                case "-" :
                    int op2 = s.pop();
                    s.push(s.pop() - op2);
                    break;
                case "*" :
                    s.push(s.pop() * s.pop());
                    break;
                case "/" :
                    int op1 = s.pop();
                    s.push(s.pop() / op1);
                    break;
                default:
                    s.push(Integer.parseInt(nextElement));
            }
        }
        return s.pop();
    }

    public int evalRPNSecondAttempt(String[] tokens) {
        Stack<String> stack = new Stack();
        
        for (String t : tokens) {
            if (isAction(t)) {
                computeOneAction(stack, t);
            } else {
                stack.push(t);
            }
        }
        
        while(stack.size() > 1) {
            computeOneAction(stack, stack.pop());
        }
        
        return Integer.valueOf(stack.peek());
    }
    
    void computeOneAction(Stack<String> stack, String actionStr) {
            char action = actionStr.charAt(0);
            int op2 = Integer.valueOf(stack.pop());
            int op1 = Integer.valueOf(stack.pop());
            int res = 0; 
            if (action == '+') {
                res = op1 + op2;
            } else if (action == '-') {
                res = op1 - op2;
            } else if (action == '/') {
                res = op1 / op2;
            } else {
                res = op1 * op2;
            }
            stack.push(Integer.toString(res));
    }
    
    boolean isAction(String s) {
        if (s.length() == 1 
            && (s.charAt(0) < '0' || s.charAt(0) > '9')) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        EvaluateReversivePolishNotation obj = new EvaluateReversivePolishNotation();
        String[] arr;
        arr = new String[] {"2", "1", "+", "3", "*"};
        System.out.println(obj.evalRPN(arr));//9

        arr = new String[] {"4", "13", "5", "/", "+"};
        System.out.println(obj.evalRPN(arr));//6

        arr = new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(obj.evalRPN(arr));//6
    }
}
