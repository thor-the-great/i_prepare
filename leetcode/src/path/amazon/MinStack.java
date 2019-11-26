package path.amazon;

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
    Stack<Integer> s;

    public MinStack() {
        s = new Stack();
    }

    public void push(int x) {
        int min = s.isEmpty() ? x : s.peek();
        s.push(x);
        s.push(Math.min(min, x));
    }

    public void pop() {
        s.pop();
        s.pop();
    }

    public int top() {
        return s.get(s.size() - 2);
    }

    public int getMin() {
        return s.peek();
    }
}
