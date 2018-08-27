package cracking.stack_queue;

import java.util.Stack;

/**
 * Created by Martin G on 1/5/2017.
 */
public class MyQueueOnTwoStacks<T> {

    Stack<T> stack1, stack2;
    boolean needToRefresh = true;

    MyQueueOnTwoStacks() {
        stack1 = new Stack<T>();
        stack2 = new Stack<T>();
    }

    void enqueue(T item) {
        stack1.push(item);
    }

    T dequeue() {
        shiftStacks();
        return stack2.pop();
    }

    T peek() {
        shiftStacks();
        return stack2.peek();
    }

    private void shiftStacks() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }

    public static  void main(String[] args) {
        MyQueueOnTwoStacks<Integer> queue = new MyQueueOnTwoStacks<>();
        for (int i = 0; i < 6; i++) {
            queue.enqueue(i);
        }

        System.out.println("Peek is " + queue.peek());

        System.out.println("Dequeue is " + queue.dequeue());
        System.out.println("Dequeue is " + queue.dequeue());
        System.out.println("Dequeue is " + queue.dequeue());
        System.out.println("Dequeue is " + queue.dequeue());

        queue.enqueue(20);
        queue.enqueue(21);

        System.out.println("Peek is " + queue.peek());
        System.out.println("Dequeue is " + queue.dequeue());
        System.out.println("Dequeue is " + queue.dequeue());
        System.out.println("Dequeue is " + queue.dequeue());
        System.out.println("Peek is " + queue.peek());
    }
}
