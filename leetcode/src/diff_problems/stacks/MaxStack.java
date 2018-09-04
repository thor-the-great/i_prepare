package diff_problems.stacks;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * This problem was asked by Amazon.
 *
 * Implement a stack that has the following methods:
 *
 * push(val), which pushes an element onto the stack
 * pop(), which pops off and returns the topmost element of the stack. If there are no elements in the stack, then
 * it should throw an error or return null.
 * max(), which returns the maximum value in the stack currently. If there are no elements in the stack, then it
 * should throw an error or return null.
 * Each method should run in constant time.
 *
 */
class MaxStack {

    List<Integer> stack = new LinkedList<>();
    List<Integer> maxes = new LinkedList<>();

    // which pushes an element onto the stack
    void push(int val) {
        stack.add(val);
        int currentMax = Integer.MIN_VALUE;
        if (!maxes.isEmpty()) {
            currentMax = maxes.get(maxes.size() - 1);
        }
        if (currentMax < val)
            maxes.add(val);
        else
            maxes.add(currentMax);
    }

    //which pops off and returns the topmost element of the stack. If there are no elements
    //in the stack, then it should throw an error or return null.
    int pop() {
        if (!maxes.isEmpty())
            maxes.remove(maxes.size() - 1);
        if (stack.isEmpty())
            throw new RuntimeException("no elements in stack");
        else {
            int val = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            return val;
        }
    }
    //which returns the maximum value in the stack currently. If there are no elements in
    //the stack, then it should throw an error or return null.
    int max() {
        if (maxes.isEmpty())
            throw new RuntimeException("no max element in stack");
        else {
            return maxes.get(maxes.size() - 1);
        }
    }

    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        Random rand = new Random();
        for (int i =0; i < 20; i++) {
            int nextNum = rand.nextInt(20);
            System.out.println("Adding " + nextNum);
            maxStack.push(nextNum);
        }
        System.out.println("-----------------------------");
        for (int i =0; i < 20; i++) {
            int nextMax = maxStack.max();
            System.out.println("Max element " + nextMax);
            int nextNum = maxStack.pop();
            System.out.println("Next pop element " + nextNum);
        }
    }
}