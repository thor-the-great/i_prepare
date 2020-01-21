import java.util.Stack;

/**
 * Given a stack, reverse the items without creating any additional data structures.
 *
 * eg.
 *
 * reverse(1->2->3) = 3->2->1
 *
 * Once you think that you’ve solved the problem, click below to see the solution.
 */
public class ReverseStack {

    Stack<Integer> reverse(Stack<Integer> stack) {
        if (stack.isEmpty())
            return stack;
        int el = stack.pop();
        reverse(stack);
        helper(stack, el);
        return stack;
    }

    void helper(Stack<Integer> s, int n) {
        if (s.isEmpty()) {
            s.push(n);
            return;
        }
        int el = s.pop();
        helper(s, n);
        s.push(el);
    }

    public static void main(String[] args) {
        ReverseStack obj = new ReverseStack();
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(3);
        s.push(2);
        //1->3->2

        Stack<Integer> rev = obj.reverse(s);
        /*Stack<Integer> rr = new Stack<>();
        while(!rev.isEmpty())
            rr.push(rev.pop());
*/
        for(int el : rev)
            System.out.print(el + "->");
    }
}
