package path.amazon;

import java.util.Stack;

/**
 * Min Stack
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 *
 * Example:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 */
public class MinStackAlternativeImpl {

  /**
   * Idea - keep both current min and element in the stack. Before push compute new min
   * when pop don't care cause every element has the min attached
   */
  class MinStack {
    Stack<Integer> stack = new Stack();
    /** initialize your data structure here. */
    public MinStack() {
    }

    public void push(int x) {
      int min = x;
      if (!stack.isEmpty()) {
        int element = stack.pop();
        min = Math.min(x, stack.peek());
        stack.push(element);
      }
      stack.push(min);
      stack.push(x);
    }

    public void pop() {
      stack.pop(); stack.pop();
    }

    public int top() {
      return stack.peek();
    }

    public int getMin() {
      int element = stack.pop();
      int curMin = stack.peek();
      stack.push(element);
      return curMin;
    }
  }
}
