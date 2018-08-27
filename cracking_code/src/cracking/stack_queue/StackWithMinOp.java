package cracking.stack_queue;

import java.util.Stack;

/**
 * Created on 1/4/2017.
 */
public class StackWithMinOp {
    Stack<Integer> stack;
    int minElement = Integer.MAX_VALUE;

    StackWithMinOp() {
        stack = new Stack<>();
        minElement = Integer.MAX_VALUE;
    }

    void push(int value) {
        if (stack.isEmpty()) {
           stack.push(value);
           minElement = value;
        }
        else {
            if (value > minElement) {
                stack.push(value);
            }
            else {
                int elementToPush = 2 * value - minElement;
                stack.push(elementToPush);
                minElement = value;
            }
        }
    }

    Integer pop() {
        if (stack.isEmpty())
            throw new RuntimeException("Stack is empty");
        int stackTop = stack.pop();
        if (stackTop >= minElement)
            return stackTop;
        else {
            int returnElement = minElement;
            minElement = 2 * minElement - stackTop;
            return returnElement;
        }
    }

    Integer minElement() {
        if (stack.isEmpty())
            throw new RuntimeException("No elements in stack");
        return minElement;
    }

    public static void main(String[] args) {
        StackWithMinOp stackWithMinOp = new StackWithMinOp();
        stackWithMinOp.push(5);
        stackWithMinOp.push(7);
        stackWithMinOp.push(3);
        stackWithMinOp.push(4);
        stackWithMinOp.push(2);
        stackWithMinOp.push(8);
        stackWithMinOp.push(1);
        stackWithMinOp.push(10);

        System.out.println("Min element is " + stackWithMinOp.minElement());
        System.out.println("Pop is " + stackWithMinOp.pop());

        System.out.println("Min element is " + stackWithMinOp.minElement());
        System.out.println("Pop is " + stackWithMinOp.pop());

        System.out.println("Min element is " + stackWithMinOp.minElement());
        System.out.println("Pop is " + stackWithMinOp.pop());

        System.out.println("Min element is " + stackWithMinOp.minElement());
        System.out.println("Pop is " + stackWithMinOp.pop());

        System.out.println("Min element is " + stackWithMinOp.minElement());
        System.out.println("Pop is " + stackWithMinOp.pop());

        System.out.println("Min element is " + stackWithMinOp.minElement());
        System.out.println("Pop is " + stackWithMinOp.pop());

        System.out.println("Min element is " + stackWithMinOp.minElement());
        System.out.println("Pop is " + stackWithMinOp.pop());

        System.out.println("Min element is " + stackWithMinOp.minElement());
        System.out.println("Pop is " + stackWithMinOp.pop());

        //-------
        stackWithMinOp = new StackWithMinOp();
        stackWithMinOp.push(3);
        stackWithMinOp.push(5);
        stackWithMinOp.push(2);
        stackWithMinOp.push(1);
        stackWithMinOp.push(1);
        stackWithMinOp.push(-1);

        System.out.println("Min element is " + stackWithMinOp.minElement());
        System.out.println("Pop is " + stackWithMinOp.pop());

        System.out.println("Min element is " + stackWithMinOp.minElement());
        System.out.println("Pop is " + stackWithMinOp.pop());

        System.out.println("Min element is " + stackWithMinOp.minElement());
        System.out.println("Pop is " + stackWithMinOp.pop());

        System.out.println("Min element is " + stackWithMinOp.minElement());
        System.out.println("Pop is " + stackWithMinOp.pop());

        System.out.println("Min element is " + stackWithMinOp.minElement());
        System.out.println("Pop is " + stackWithMinOp.pop());

        System.out.println("Min element is " + stackWithMinOp.minElement());
        System.out.println("Pop is " + stackWithMinOp.pop());
    }
}
