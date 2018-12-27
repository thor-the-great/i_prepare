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
