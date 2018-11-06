package mock_sessions.amazon;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 */
public class MinStack {
    // idea is to use stack and keep previous minimum before pushing new minimum. Current minimum never is in stack two
    // times
    int min;
    Stack<Integer> s;

    public MinStack() {
        min = Integer.MAX_VALUE;
        s = new Stack();
    }

    public void push(int x) {
        //keep previous minimum in stack, push new num as regular number, keep new minimum in min
        if (x <= min) {
            s.push(min);
            min = x;
        }
        s.push(x);
    }

    public int pop() {
        //pop element and check if it's a min, if it is - pop next num - it must be a previous minimum
        int popElement = s.pop();
        if (min == popElement) {
            min = s.pop();
        }
        return popElement;
    }

    public int getMin() {
        return min;
    }

    public int top() {
        return s.peek();
    }
}
